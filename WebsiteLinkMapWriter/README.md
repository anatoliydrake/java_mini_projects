# 🌐 Website Crawler using ForkJoinPool

## 📖 Description
This Java application recursively crawls pages of a specified website using `ForkJoinPool` and generates a sitemap saved in a text file. Child page URLs are indented with tabs to represent the hierarchy.

## 🛠 Technologies Used
- Java 20+
- ForkJoinPool
- JSoup
- Maven

## 🚀 Features
- Recursive and parallel crawling with `ForkJoinPool`
- Filters:
    - Only links on the same domain
    - Excludes anchor links (`#...`) and external URLs
- Prevents cyclic crawling
- Respects web server by adding a 100–150 ms delay between requests
- Outputs sitemap to a file with readable indentation

## 🏁 How to Run
1. Clone the repo
2. Open the project in your IDE
3. Run `Main.java`
4. View the result in `websiteMap.txt`

## 📄 Output File
The sitemap is saved as `websiteMap.txt` in the root directory.

## 📎 Example Output
```
https://example.com
    https://example.com/about
        https://example.com/about/team
    https://example.com/contact
```

