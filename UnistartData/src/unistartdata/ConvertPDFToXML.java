/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unistartdata;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.*;
import java.util.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.xml.sax.helpers.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import function.mbti;
import org.apache.commons.logging.LogFactory;

public class ConvertPDFToXML {
	

	public static void main(String[] args) throws IOException {
   WebClient client= new WebClient(BrowserVersion.CHROME_16);
       LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", 
               "org.apache.commons.logging.impl.NoOpLog");
       client.getOptions().setJavaScriptEnabled(false);
       HtmlPage page= client.getPage("https://hoc24.vn/ly-thuyet/chuong-4-bat-dang-thuc-bat-phuong-trinh.229/");
          //  System.out.println(page.asText());
         String xpath="//h4/a";
        String url="D:\\Wisky\\Unistart0.2\\UnistartFE\\src\\assets\\Knowledge\\1\\1\\"
                + "Đại số Chương 3 PHƯƠNG TRÌNH, HỆ PHƯƠNG TRÌNH";
       java.util.List<HtmlElement> listcode=(java.util.List<HtmlElement>) page.getByXPath(xpath);
       for (HtmlElement e: listcode){
           mbti m= new mbti();
           m.getknowlist(e, url);
            System.out.println(e.getAttribute("href"));
       }     
        }}