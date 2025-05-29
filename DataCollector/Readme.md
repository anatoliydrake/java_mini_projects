# Data Collector Project

## 📚 Description

This Java project is designed to collect and integrate data from multiple sources, including:
- HTML web pages (Moscow Metro stations and lines),
- CSV and JSON files from a nested folder structure.

The collected data is parsed and compiled into two final JSON files:
- `metro.json` – a structured representation of metro lines and their stations.
- `stations.json` – a detailed list of stations with additional metadata like opening date and depth.

### Components:

- **HTMLParser** – uses [jsoup](https://jsoup.org/) to retrieve and parse the Moscow Metro station list.
- **FileScanner** – recursively scans folders to find `.csv` and `.json` files.
- **CSVParser** – parses found CSV files into Java objects.
- **JSONParser** – parses found JSON files into Java objects.
- **JSONWriter** – merges and transforms data into the desired JSON format and writes output files.

## 🧰 Technologies Used

- Java 20
- Jsoup (HTML parsing)
- Jackson (JSON parsing/serialization)
- Maven

## 🚀 How to Run

1. Clone this repository or download the source code.

2. Open the project in your IDE (e.g., IntelliJ IDEA).

3. Make sure you have Java 20 installed and Maven configured.

4. Run the `main()` method from the entry point class.

5. After execution, two JSON files will be generated in the specified output directory:
    - `metro.json`
    - `stations.json`


## 📝 Output JSON Format

### stations.json

```json
{
  "stations": [
    {
      "name": "Бульвар Рокоссовского",
      "line": "Сокольническая линия",
      "date": "01.08.1990",
      "depth": "-8,0"
    },
    ...
  ]
}
