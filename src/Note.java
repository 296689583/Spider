import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Note implements Runnable{
    String capName;
    String capUrl;
    String content;
    Thread t =null;
    public String getCapName() {
        return capName;
    }
    public void setCapName(String capName) {
        this.capName = capName;
    }
    public String getCapUrl() {
        return capUrl;
    }
    public void setCapUrl(String capUrl) {
        this.capUrl = capUrl;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            t = new Thread(this);
            Thread.sleep(100);
            Document doc = Utils.getDocument(capUrl);
            Element ele = doc.getElementById("content1");
            String [] s = ele.text().split("\r\n");
            String content1="";
            for(String t : s){
                content1+=t+"\r\n";
            }
            this.content = content1;
            System.out.println(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("exception 1");
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("exception 2");
        }
    }
}
