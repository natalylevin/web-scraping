import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class Ynet extends BaseRobot {
    static ArrayList<String> siteURL = new ArrayList<>();
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new Ynet("https://www.ynet.co.il/home/0,7340,L-8,00.html").getWordsStatistics();
    }

    public Ynet(String rootWebsiteUrl) throws IOException {
        super(rootWebsiteUrl);
        Document web = Jsoup.connect(getRootWebsiteUrl()).get();
        siteURL.add(web.getElementsByClass("slotTitle").get(0).child(0).attr("href"));
        Element teasers = web.getElementsByClass("YnetMultiStripComponenta oneRow multiRows").first();
        for (Element mediaItems : teasers.getElementsByClass("mediaItems")) {
            siteURL.add(mediaItems.child(0).child(0).attr("href"));
        }
        Element news = (web.getElementsByClass("MultiArticleComponenta1280").first().child(1));
        for (Element slotTitle_medium : news.getElementsByClass("slotTitle medium")) {
            siteURL.add(slotTitle_medium.child(0).attr("href"));
        }
        for (Element slotTitle_small : news.getElementsByClass("slotTitle small")) {
            siteURL.add(slotTitle_small.child(0).attr("href"));
        }
    }

    @Override
    public Map<String, Integer> getWordsStatistics() throws IOException {
        for (String url : siteURL) {
            String words = "";
            Document web = Jsoup.connect(url).get();
            System.out.println(url);
            for (Element h1 : web.getElementsByTag("h1")) {
                words += (h1.text());
            }
            for (Element h2 : web.getElementsByTag("h2")) {
                words += (h2.text());
            }
            for (Element text_editor_paragraph_rtl : web.getElementsByClass("text_editor_paragraph rtl")) {
                words += (text_editor_paragraph_rtl.text());
            }
            String[] wordsArray = words.split(" ");
            for (String word : wordsArray) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                }else{
                    map.put(word, 1);
                }
            }

        }
        return map;
    }

    @Override
    public int countInArticlesTitles(String text) {

        return 0;
    }

    @Override
    public String getLongestArticleTitle() {
        return null;
    }
}
