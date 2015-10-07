package Example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class LAB01 {
	public static void main(String[] args) throws Exception {
		// Read file
		File file = new File("D:/data.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        // Write file
        FileOutputStream fos= new FileOutputStream("D:/data_final",false);
        PrintWriter pw= new PrintWriter(fos);
        // Write file with http error
        FileOutputStream fos_error= new FileOutputStream("D:/data_error",false);
        PrintWriter pw_error = new PrintWriter(fos_error);
        
        while ((line = br.readLine()) != null) 
        {
            // process the line
            System.out.println(line);
            String link = "https://www.sendo.vn/" + line; 
            System.out.println(link);
            try
            {
	            Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").cookie("auth", "token").timeout(30000).get();		
	            Elements elements = doc.select("div[class=box_img_attr]");
	    		//Link product
	    		pw.println(link);
	            for (Element element : elements) 
	            {	
	    			// Link ảnh			
	    			Element imageNode = element.select("div.box_img_attr > div.box_img_product > div.bg-l  > img").first();
	    			String image = imageNode.attr("src");
	    			pw.println(image);
	    			
	    			// Tiêu đề
	    			Element titleNode = element.select("div.box_img_attr > div.box_attr_product > div.name_product.item > h1").first();
	    			String title = titleNode.text();
	    			pw.println(title);
	    		
	    			// Giá
	    			Element summaryNode = element.select("div.box_img_attr > div.box_attr_product > div.price_wirehouse_shopinfo > div.price > div.current_price").first();
	    			String summary = summaryNode.text();
	    			pw.println(summary);
	            }
    		}
    		catch(Exception ex)
            {
    			pw_error.println(link);
    			continue;
    			
            }
        }
        br.close();
		pw.close();
		pw_error.close();		
	}
}
