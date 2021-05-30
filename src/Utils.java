import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Utils {
    public static Document getDocument(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
}
