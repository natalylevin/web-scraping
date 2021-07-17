import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mako extends BaseRobot {

    public static void main(String[] args) throws IOException {
        new Mako("https://www.mako.co.il").getWordsStatistics();

    }

    static ArrayList<String> siteURL = new ArrayList<>();
    static HashMap<String, Integer> map = new HashMap<>();


    @Override
    public Map<String, Integer> getWordsStatistics() throws IOException {
        for (String url : siteURL) {
            String words = "";
            Document web = Jsoup.connect(url).get();
            words += (web.getElementsByTag("h1").text());
            words += (web.getElementsByTag("h2").text());
            words += (web.getElementsByTag("p").text());

            String[] wordsArray = words.split(" ");
            for (String word : wordsArray) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }

        }

        return map;
    }

    @Override
    public int countInArticlesTitles(String text) throws IOException {
        int count = 0;
        Document Mako = Jsoup.connect(getRootWebsiteUrl()).get();
        String contains = Mako.getElementsByClass("slider_image_inside").text();
        Element webText = Mako.getElementsByClass("neo_ordering scale_image horizontal news").first();
        contains += webText.getElementsByTag("span").text();
        if (contains.contains(text)){
            count++;
        }
        return count;
    }

    @Override
    public String getLongestArticleTitle() throws IOException {
        return null;
    }

    public Mako(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        Document web = Jsoup.connect(getRootWebsiteUrl()).get();
        for (Element myElement : web.getElementsByClass("slider_image_inside")) {
            siteURL.add(getRootWebsiteUrl() + myElement.child(0).attr("href"));
        }
        Element newsSection = (web.getElementsByClass("neo_ordering scale_image horizontal news").first());
        for (Element element : newsSection.getElementsByClass("element")) {
            siteURL.add(getRootWebsiteUrl() + element.child(0).child(0).attr("href"));
        }

    }


}
