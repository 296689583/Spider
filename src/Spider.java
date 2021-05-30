import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider{
   public static void main(String[] args) throws IOException, InterruptedException {
    String beginUrl="https://www.xiashu99.com/read/31766/";
    String baseUrl="https://www.xiashu99.com";
    List<Note> chapList = Collections.synchronizedList(new ArrayList<>());
    File f = new File("/home/e_toch/Documents/a.txt");
    if(!f.exists()){
        f.delete();
        f.createNewFile();
    }
    BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
    Document doc = Utils.getDocument(beginUrl);
    Element ele =  doc.getElementsByClass("pc_list").get(1);
    Elements eles = ele.select("a[href]");
    for(Element e:eles){
        Note noteTemp = new Note();
        noteTemp.setCapName(e.text());
        noteTemp.setCapUrl(baseUrl+e.attr("href"));
        chapList.add(noteTemp);
       // 测试获取是否正确 
       // System.out.println(e.text()+e.attr("href"));
    }
    ExecutorService pool = Executors.newFixedThreadPool(12);
    Iterator<Note> it = chapList.iterator();
    while(it.hasNext()){
        pool.submit(it.next());
    }
    pool.shutdown();
    System.out.println("-------------thread is shut down -----------");
    while(true){
        if(pool.isTerminated()){
            System.out.println("-------------end------------");
            break;
        }
    }
    Thread.sleep(1000);
    for(Note n :chapList){
        bw.write(n.getCapName()+"\r\n"+n.getContent());
    }
    bw.close();
   }
}
   
   