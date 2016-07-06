import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Respose_Code {

	private static int statusCode;
	public static void main(String... args) throws IOException{
		System.setProperty("webdriver.chrome.driver", "E:\\Jars\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.google.com/");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    List<WebElement> links = driver.findElements(By.tagName("a"));
	    for(int i = 0; i < links.size(); i++){
	        if(!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))){
	            if(links.get(i).getAttribute("href").contains("http")){
	                statusCode= getResponseCode(links.get(i).getAttribute("href").trim());
	                if(statusCode == 403){
	                    System.out.println("HTTP 403 Forbidden # " + i + " " + links.get(i).getAttribute("href"));
	                }
	            }
	        }   
	    }   
	}
	
	public static int getResponseCode(String urlString) throws MalformedURLException, IOException{
	    URL url = new URL(urlString);
	    HttpURLConnection huc = (HttpURLConnection)url.openConnection();
	    huc.setRequestMethod("GET");
	    huc.connect();
	    return huc.getResponseCode();
	}

}