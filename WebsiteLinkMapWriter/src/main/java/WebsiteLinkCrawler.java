import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;

public class WebsiteLinkCrawler extends RecursiveAction {
    private final String baseUrl;
    private final String currentUrl;
    private static final Set<String> visitedLinks = ConcurrentHashMap.newKeySet();

    private WebsiteLinkCrawler(String baseUrl, String currentUrl) {
        this.baseUrl = baseUrl;
        this.currentUrl = currentUrl;
    }

    public static Set<String> getVisitedLinks() {
        return visitedLinks;
    }

    public static WebsiteLinkCrawler start(String url) {
        visitedLinks.add(url);
        return new WebsiteLinkCrawler(url, url);
    }

    @Override
    protected void compute() {
        try {
            Thread.sleep(150);
            Document document = Jsoup.connect(currentUrl).get();
            List<WebsiteLinkCrawler> taskList = new ArrayList<>();

            Elements links = document.select("a[href]");
            for (Element l : links) {
                String href = l.absUrl("href");
                boolean isInternalLink = href.startsWith(baseUrl);
                if (isInternalLink && !l.attr("href").contains(".") && !href.contains("?") && !href.contains("#")) {
                    boolean isNewLink = visitedLinks.add(href);
                    if (isNewLink) {
                        WebsiteLinkCrawler task = new WebsiteLinkCrawler(baseUrl, href);
                        task.fork();
                        taskList.add(task);
                    }
                }
            }
            for (WebsiteLinkCrawler task : taskList) {
                task.join();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка при обработке: " + currentUrl + " — " + e.getMessage());
        }
    }
}
