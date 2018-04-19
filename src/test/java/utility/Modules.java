package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Modules {
	public static HSSFWorkbook wb;
	public static WebDriver driver;
	
	public static WebDriver launchBrowser(String name){
		//System.setProperty("webdriver.firefox.bin","C:/Program Files/Mozilla Firefox53/firefox.exe");
		if(name.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		}
		else if(name.equalsIgnoreCase("chrome")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.chrome.driver","./src/test/java/utility/chromedriver.exe");
			driver=new ChromeDriver();
			//driver.manage().window().maximize();
		}
		else if(name.equalsIgnoreCase("ie")){
			System.out.println("Internet Explorer entered");
			System.setProperty("webdriver.ie.driver","./src/test/java/utility/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		return driver;
	}

	public static void closeBrowser(WebDriver driver){
		try{
			driver.quit();
		}
		catch(Exception e){
			
		}
	}
	public static WebDriver Login_toSalesforce(WebDriver driver) throws InterruptedException{
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("nura@google.com");
		driver.findElement(By.id("password")).sendKeys("xionnumo123");
		Thread.sleep(5000);//due to my antivirus secure tab
		driver.findElement(By.id("Login")).click();
		Thread.sleep(6000);
		return driver;
	}
	
	public static WebDriver Login_toSalesforce1(WebDriver driver,String id,String password,boolean remember) throws InterruptedException{
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(id);
		driver.findElement(By.id("password")).sendKeys(password);
		if(remember)
			driver.findElement(By.xpath("//*[@id='rememberUn']")).click();
		driver.findElement(By.id("Login")).click();
		Thread.sleep(6000);
		return driver;
	}
	
	public static String[][] readXlSheet(String link,String sheetName) throws IOException{
		FileInputStream fio=new FileInputStream(new File(link));
		wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		int trow=sheet.getLastRowNum()+1;
		int tcol=sheet.getRow(0).getLastCellNum();
		String[][] s = new String[trow][tcol];
		System.out.println("total rows="+trow+" and total column="+tcol);
		for(int i=0;i<trow;i++){
			for(int j=0;j<tcol;j++){
				int cellType=sheet.getRow(i).getCell(j).getCellType();
				if(cellType==HSSFCell.CELL_TYPE_NUMERIC)
					s[i][j]=String.valueOf((int)sheet.getRow(i).getCell(j).getNumericCellValue());
				else
				s[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();	
		
			}
		}
		
		return s;
	}
	
	public static void writeXlSheet(String link, String sheetName,String text,int row,int col) throws IOException {
		File f=new File(link);
		FileInputStream fio=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		sheet.getRow(row).getCell(col).setCellValue(text);
		FileOutputStream fop=new FileOutputStream(f);
		wb.write(fop);
		fop.flush();
		fop.close();
		}
	
	
	public static void setXlColorStyle(String link,String sheetName,int iRow,int iCol,String status) throws IOException{
		File f=new File(link);
		FileInputStream fio=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		
		
		HSSFRow row = sheet.getRow(iRow);
		HSSFCell cell = row.getCell(iCol);
		
		if(status.equalsIgnoreCase("pass"))
			fillBackgroundColor(wb, "green", cell);
		else
			fillBackgroundColor(wb, "red", cell);
		
		FileOutputStream fop=new FileOutputStream(f);
		wb.write(fop);
		fop.flush();
		fop.close();
		}
	public static void fillBackgroundColor(HSSFWorkbook wb,String color,HSSFCell cell){
		HSSFCellStyle style=(HSSFCellStyle) wb.createCellStyle();
		if(color.equalsIgnoreCase("green")){
			style.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
			}
		else if(color.equalsIgnoreCase("red")){
			style.setFillForegroundColor(new HSSFColor.RED().getIndex());
			}
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
	}
	
	
	public static WebDriver selectItemFromUserMenu(WebDriver driver,String name){
		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		for(WebElement ele:list){
			if(ele.getText().equalsIgnoreCase(name))
				ele.click();
		}
		return driver;
	}
	public static String CaptureScreen(WebDriver driver,String name) throws IOException{
		File src= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest="./Screenshots/"+name+".png";
		FileUtils.copyFile(src, new File(dest));
		return dest;
	}
}
