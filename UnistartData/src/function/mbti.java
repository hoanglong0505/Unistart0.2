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
import java.util.List;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Admin
 */
public class mbti {
    public void getquestion() throws IOException{
           WebClient client= new WebClient(BrowserVersion.CHROME_16);
       LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", 
               "org.apache.commons.logging.impl.NoOpLog");
       client.getOptions().setJavaScriptEnabled(false);
       HtmlPage page= client.getPage("file:///D:/Wisky/Unistart0.2/UnistartData/mbti.html");
         String xpath="//*";
         List<HtmlElement> listlink=(List<HtmlElement>) page.getByXPath(xpath);
      for(HtmlElement e: listlink){
          System.out.println(e.asText());
      }

    }  
}
