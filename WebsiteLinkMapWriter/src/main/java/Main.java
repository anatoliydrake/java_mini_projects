import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static String website = "https://italiani.rest/";
    private static final int baseDepth = website.split("/").length;
    public static String dst = "websiteMap.txt";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBuilder siteMapBuilder = new StringBuilder();
        try (ForkJoinPool pool = new ForkJoinPool()) {
            pool.invoke(WebsiteLinkCrawler.start(website));
            Set<String> visitedLinks = WebsiteLinkCrawler.getVisitedLinks();
            visitedLinks.stream()
                    .sorted()
                    .forEach(url -> siteMapBuilder.append("\n").append(getIndent(url)).append(url));
        }
        try {
            Files.write(Paths.get(dst), siteMapBuilder.toString().trim().getBytes());
            System.out.println("Duration: " + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static String getIndent(String url) {
        int depth = url.split("/").length - baseDepth;
        return "\t".repeat(depth);
    }
}
