package myntra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.common.io.Files;

public class MyntraExcel {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Downloads\\chromeDriver\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		File fil = new File("C:\\\\Users\\\\Dell\\\\Desktop\\\\hai.xlsx");
		FileInputStream fis  = new FileInputStream(fil);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		
		
		
		driver.get("https://www.flipkart.com/");
		
		TakesScreenshot scr =((TakesScreenshot)driver);
		File s =scr.getScreenshotAs(OutputType.FILE);
		File path = new File("E:\\ScreenShot\\image.jpg");
		Files.copy(s, path);
		

		
		
		Actions act = new Actions(driver);
		
		act.moveToElement(driver.findElement(By.xpath("(//a[@class='desktop-main'])[1]"))).build().perform();
		
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"desktop-header-cnt\"]/div[2]/nav/div/div[1]/div/div/div/div/li[1]/ul/li[12]/a"))).click().perform();
		
		act.moveToElement(driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[8]"))).click().perform();
		
		String preantwindow = driver.getWindowHandle();
		
		for(int i=0;i<=5;i++) {
			driver.findElement(By.xpath("(//span[@class='product-discountPercentage'])["+i+"]")).click();
			Set<String> child = driver.getWindowHandles();
			for(String n:child) {
				if(!n.equals(preantwindow)) {
					driver.switchTo().window(n);
					String size=driver.findElement(By.xpath("(//p[@class='pdp-sizeFitDescContent pdp-product-description-content'])[1]")).getText();
					System.out.println(size);
					driver.close();
				}
			}driver.switchTo().window(preantwindow);
 		}
		
		
		
		
		
		

	}

}
