/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Admin
 */
public class school_Content {
    
    public  void getlink(String link) throws IOException{
        WebClient client= new WebClient(BrowserVersion.CHROME_16);
       LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", 
               "org.apache.commons.logging.impl.NoOpLog");
       client.getOptions().setJavaScriptEnabled(false);
       HtmlPage page= client.getPage(link);
         String xpath="//table[@class=\"MsoNormalTable\"]/tbody/tr[position()>2]/td/p/b";
       List<HtmlElement> listcode=(List<HtmlElement>) page.getByXPath(xpath);
          xpath="//table[@class=\"MsoNormalTable\"]/tbody/tr[position()>2]/td/p//a";
         List<HtmlElement> listlink=(List<HtmlElement>) page.getByXPath(xpath);
     
//       for (HtmlElement e:listcode){   
//        
//           System.out.println(e.asText().trim());     
//       }
//        System.out.println(listcode.size());
//     
//         for (HtmlElement e:listlink){   
//        
//           System.out.println(e.getAttribute("href"));     
//       }
//        System.out.println(listlink.size());  

for (int i=0;i<listlink.size();i++){
    getContent(listlink.get(i).getAttribute("href"), listcode.get(i).asText().trim());

}
    }
     public  void getContent(String link,String code) throws IOException{
         if (!(code.length()>0)){
             code=link.substring(30, link.length()-5);
         }
        WebClient client= new WebClient(BrowserVersion.CHROME_16);
       LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", 
               "org.apache.commons.logging.impl.NoOpLog");
       client.getOptions().setJavaScriptEnabled(false);
       HtmlPage page= client.getPage(link);
         String xpath="//div[@class=\"boxtabcontent\" ][1]/div/*[not(self::script) and not(self::ins)]";
       List<HtmlElement> listcode=(List<HtmlElement>) page.getByXPath(xpath);
         System.out.println(listcode.size());
        PrintWriter writer = new PrintWriter("save\\content\\MienBac\\"+code+".html", "UTF-8");
        for (HtmlElement e: listcode){
             writer.println(e.asXml()); 
        }
         
                writer.close();  
       }
    
     public  void getContent2(String link) throws IOException{
        WebClient client= new WebClient(BrowserVersion.CHROME_16);
       LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", 
               "org.apache.commons.logging.impl.NoOpLog");
       client.getOptions().setJavaScriptEnabled(false);
       HtmlPage page= client.getPage(link);   
       String code="";
         String xpath="//*[contains(text(),\"Mã trường\")]/b";
       List<HtmlElement> listcode=(List<HtmlElement>) page.getByXPath(xpath);
       if (listcode.size()>0)
       code= listcode.get(0).asText().trim();
       else{
           xpath="//*[contains(text(),\"Ký hiệu\")]/b";
            listcode=(List<HtmlElement>) page.getByXPath(xpath);
           if (listcode.size()>0)
       code= listcode.get(0).asText().trim();
       else{
           
           xpath="//div[@class=\"boxtabcontent\" ][1]/div/*[not(self::script) and not(self::ins)]";
            listcode=(List<HtmlElement>) page.getByXPath(xpath);
            code=listcode.get(0).asText();}
       }      
          xpath="//div[@class=\"boxtabcontent\" ][1]/div/*[not(self::script) and not(self::ins)]";
        listcode=(List<HtmlElement>) page.getByXPath(xpath);
         System.out.println(listcode.size());
        PrintWriter writer = new PrintWriter("save\\content\\MienBac\\"+code+".html", "UTF-8");
        for (HtmlElement e: listcode){
             writer.println(e.asXml()); 
        }
         
                writer.close();  
       }
         public  void getlink2(String link) throws IOException{
        WebClient client= new WebClient(BrowserVersion.CHROME_16);
       LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", 
               "org.apache.commons.logging.impl.NoOpLog");
       client.getOptions().setJavaScriptEnabled(false);
       HtmlPage page= client.getPage(link);
         String xpath="//table[@class=\"MsoNormalTable\"]/tbody/tr/td/p//a";
         List<HtmlElement> listlink=(List<HtmlElement>) page.getByXPath(xpath);
      

for (int i=0;i<listlink.size();i++){
      System.out.println(listlink.get(i).getAttribute("href"));
    getContent2(listlink.get(i).getAttribute("href"));
  

}}
    
}
