## Web Robots for News Analysis

This project implements three robots: Mako, Walla, and Ynet that analyze news websites using Jsoup library.

* **BaseRobot:**
    * Stores and provides access to the root website URL.
    * Declares abstract methods for specific functionalities implemented by subclasses.
* **Mako, Walla, Ynet robots:**
    * Analyzes Mako news website.
    * Extracts words from titles, headings, and paragraphs.
    * Creates a map to store word frequencies.
    * Counts the occurrences of a specific word in article titles.
    * Finds the longest article title based on the length of its content.
