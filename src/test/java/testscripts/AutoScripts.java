package testscripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utility.Modules;
import utility.ReUsableMethod;

public class AutoScripts extends ReUsableMethod {
	public static WebDriver driver;
	SoftAssert softAssert=new SoftAssert();
	
	public static void LoginErrorMessage_01(String browserName) throws InterruptedException{
		String expString="Please enter your password.";
		driver=Modules.launchBrowser(browserName);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("salesforce page is not verified",ExtentColor.RED));
			DriverFile.status=false;
			
		}
		//
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("User@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		String actualText=driver.findElement(By.xpath("//*[@id='error']")).getText();
		if(actualText.equalsIgnoreCase(expString)){
			DriverFile.logger.log(Status.INFO,"error message verified");
		}
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("error message is not verified",ExtentColor.RED));
			
		}
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
		
		
	}
	
	public static void LoginToSalesForce_02(String browserName) throws InterruptedException, IOException{
		String expString="Please enter your password.";
		driver=Modules.launchBrowser(browserName);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("salesforce page is not verified",ExtentColor.RED));
		}
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("nura@google.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("xionnumo123");
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		Thread.sleep(6000);
		
		String homeTiltle=driver.getTitle();
		Thread.sleep(6000);
		if(homeTiltle.contains("Home Page"))
			DriverFile.logger.log(Status.INFO,"home page verified");
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("home page is not verified",ExtentColor.RED));
			String destination=Modules.CaptureScreen(driver,Thread.currentThread().getStackTrace()[1].getMethodName());
			DriverFile.logger.log(Status.FAIL,"screen shots at:"+DriverFile.logger.addScreenCaptureFromPath(destination));
		}
			Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}
	public static void  CheckRemeberMe_3(String browserNamee) throws InterruptedException{
		driver=Modules.launchBrowser(browserNamee);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			System.out.println("salesforce page is verified");
		else
			System.out.println("SalesForce not verified");
		Modules.Login_toSalesforce1(driver,"nura@google.com","xionnumo123",true);
		Modules.selectItemFromUserMenu(driver,"Logout");
		Thread.sleep(6000);
		String actID=driver.findElement(By.xpath("//*[@id='idcard-identity']")).getText();
		if(actID.equalsIgnoreCase("nura@google.com"))
			System.out.println("email id verified");
		else
			System.out.println("email id not verified");
		Modules.closeBrowser(driver);
		
		
	}
	
	public static void ForgotPassword_4(String browserNamee) throws InterruptedException{
		String expString="We’ve sent you an email with a link to finish resetting your password.";
		driver=Modules.launchBrowser(browserNamee);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			System.out.println("salesforce page is verified");
		else
			System.out.println("salesforce page is not verified");
		driver.findElement(By.xpath("//a[text()='Forgot Your Password?']")).click();
		Thread.sleep(6000);
		if(driver.getTitle().contains("Forgot Your Password"))
			System.out.println("forgot password page is verified");
		else
			System.out.println("forgot password page is not verified");
		
		
		driver.findElement(By.xpath(".//*[@id='un']")).sendKeys("nura.tabassum@gmail.com");
		driver.findElement(By.xpath(".//*[@id='continue']")).click();
		Thread.sleep(2000);
		String actText=driver.findElement(By.xpath("//*[@id='forgotPassForm']/div/p[1]")).getText();
		if(actText.equalsIgnoreCase(expString))
			System.out.println("reset message verified");
		else
			System.out.println("reset message is not verified");
		Modules.closeBrowser(driver);
	}
	public static void ValidateLoginErrorMessage_5(String browserNamee) throws InterruptedException{
		//10sec
		String expText="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		driver=Modules.launchBrowser(browserNamee);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			System.out.println("salesforce page is verified");
		else
			System.out.println("salesforce page is not verified");
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("123");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("3456");
		driver.findElement(By.xpath("//*[@id='Login']")).click();
		Thread.sleep(6000);
		String actText=driver.findElement(By.xpath(".//*[@id='error']")).getText();
		if(actText.equalsIgnoreCase(expText))
			System.out.println("wrong id and password message is verified");
		else
			System.out.println("wrong id and password message is not verified");
		Modules.closeBrowser(driver);
	}
	public static void userMenu_DropDown_6(String browserNamee) throws InterruptedException{
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));
		driver=Modules.launchBrowser(browserNamee);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		System.out.println(actData);
		if(actData.containsAll(expList))
			System.out.println("usermenu verified ");
		else
			System.out.println("user menu not verified");
		Modules.closeBrowser(driver);
		
	}
	public static void EditPostFileImage_7(String browserName) throws InterruptedException{
		//WebDriver driver;
		
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNavButton']"));
		usermenu.click();
		
		//click on My profile
		WebElement myProf= driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[1]"));
		clickObject(myProf,"MyProfileOption");
		//edit profile
		WebElement edit= driver.findElement(By.xpath("//*[@id='chatterTab']/div[2]/div[2]/div[1]/h3/div/div/a/img"));
		clickObject(edit,"EditProfileButton");
		Thread.sleep(5000);
		//System.out.println(driver.getCurrentUrl());
		
		//switch frame since About is in diff frame
		driver.switchTo().frame("contactInfoContentId");
		System.out.println("I am inside a frame id=contactInfoContentId");		
		//Validate
		String crrctTab="About";
		String crrctCntct="Contact";
		
		   //validate "About tab" displayed
				 WebElement dispAbout=driver.findElement(By.xpath("//*[@id='aboutTab']/a"));
				 validateMessages(dispAbout, crrctTab, "About Tab");
				 
				//validate "Contact tab" displayed
		          	WebElement dispContact=driver.findElement(By.xpath("//*[@id='contactTab']/a"));
		            validateMessages(dispContact, crrctCntct, "Contact Tab");
		
		//click About tab
        WebElement aboutTab=driver.findElement(By.xpath("//*[@id='aboutTab']/a"));
		clickObject(aboutTab,"About Tab Button");
		//Enter lastName
		WebElement lastName=driver.findElement(By.xpath("//input[@id='lastName']"));
		lastName.clear();
		enterText(lastName,"Taba", "LastName field");
		
		//click Save alltab
        WebElement saveAllTab=driver.findElement(By.cssSelector(".zen-btn.zen-primaryBtn.zen-pas"));
		clickObject(saveAllTab,"Save All Tab Button");
		Thread.sleep(5000);
		
		
		
		
		            //swith back to default page
		            driver.switchTo().defaultContent();
		            System.out.println("Clicked on saveAll  and back to default window");
		    		Thread.sleep(4000);
		    		
		    		
		           
		            //click post Button
		            WebElement postbutton=driver.findElement(By.xpath("//*[@id='publisherAttachTextPost']/span[1]"));
		    	clickObject(postbutton,"Post Button");
		    	Thread.sleep(4000);
				//identifying the frame and wait until frame visible
				WebElement frame=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		
				//switch to frame
				driver.switchTo().frame(frame);
				System.out.println("switched");
				Thread.sleep(10000);
				//identify postTextArea locator and wait until its visible
				System.out.println("tag html?  "+driver.findElements(By.xpath("//html/body")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body")));
				
				//post message in the box
				WebElement postMessage=driver.findElement(By.xpath("/html/body"));
				enterText(postMessage,"Hi Automation team","Post Message");
				//swithch to default page as share is in default
				driver.switchTo().defaultContent();
				//click share
				WebElement shareButton=driver.findElement(By.xpath(".//*[@id='publishersharebutton']"));
				clickObject(shareButton,"Share tab Button");
				
				Thread.sleep(2000);
				
				//Click File upload
					WebElement file=driver.findElement(By.xpath("//*[@id='publisherAttachContentPost']/span[1]")); 
					clickObject(file,"File link");
					
					WebElement fileUpload=driver.findElement(By.xpath("//*[@id='chatterUploadFileAction']")); 
					clickObject(fileUpload,"File Upload button");
					//enter the absolut file path to the browser
					WebElement browse=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterFile']")));
					browse.sendKeys("C:\\Users\\Ash\\Desktop\\MyContacts.txt");
					
					//share file
					WebElement shareFile=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='publishersharebutton']")));
					clickObject(shareFile,"Share Button");
					Thread.sleep(5000);
					//upload Image
					WebElement image=driver.findElement(By.id("uploadLink" ));
					WebElement uploadImage=driver.findElement(By.xpath("//*[@id='uploadLink']"));
					hoverAndClick(driver, image, uploadImage);
					//Switch to frame
					WebElement myFrame1=driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));
					driver.switchTo().frame(myFrame1);
					System.out.println("I am inside a frame now");
					Thread.sleep(4000);
					//enter the image path int he browser
					WebElement browsePhoto=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadInputFile']")));
					                                                                                   
					browsePhoto.sendKeys("C:\\Users\\Ash\\Desktop\\mssg.PNG");
					Thread.sleep(3000);
					//save image
					WebElement save=driver.findElement(By.xpath("//*[@id='j_id0:uploadFileForm:uploadBtn']"));
					clickObject(save, "Save Button");
					Thread.sleep(5000);
					//crop image
					/*Actions action=new Actions(driver); 
					action.dragAndDropBy(driver.findElement(By.xpath(".//*[@id='j_id0:outer']/div[1]/div/div/div/div[6]")), 100, 20);*/
					driver.findElement(By.xpath(".//*[@id='j_id0:j_id7:save']")).click(); 
					
					driver.switchTo().defaultContent();
					//driver.quit();
				driver.close();
		
	}
	public static void mySettings_8(String browserName) throws InterruptedException{
//WebDriver driver;
		
driver=Modules.launchBrowser(browserName);
driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
WebDriverWait wait = new WebDriverWait(driver, 40);
driver=Modules.Login_toSalesforce(driver);
Thread.sleep(6000);
					
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNavButton']"));
		usermenu.click();
		
		Thread.sleep(3000);
		WebElement settings= driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[2]"));
		clickObject(settings,"MySettings");
		
		
		WebElement personal= driver.findElement(By.xpath("//*[@id='PersonalInfo']/a"));
		clickObject(personal,"Personal link");
		
		WebElement loginhistory= driver.findElement(By.xpath("//*[@id='LoginHistory_font']"));
		clickObject(loginhistory,"Login History");
		
		WebElement dispLayout= driver.findElement(By.xpath("//*[@id='DisplayAndLayout_font']"));
		clickObject(dispLayout,"Display and layout");
		WebElement customize= driver.findElement(By.xpath("//*[@id='CustomizeTabs_font']"));
		clickObject(customize,"Customize my tab");
		WebElement schatter= driver.findElement(By.xpath("//*[@id='p4']"));
		Select select=new Select(schatter);
		select.selectByVisibleText("Salesforce Chatter");
		
		WebElement report= driver.findElement(By.xpath("//*[@id='duel_select_0']/option[31]"));
		clickObject(report, "Reports");
		
		WebElement add= driver.findElement(By.xpath("//*[@id='duel_select_0_right']/img"));
		clickObject(add,"Add Button");
		WebElement email= driver.findElement(By.xpath("//*[@id='EmailSetup']/a"));
		clickObject(email,"Email Link");
		WebElement myemail= driver.findElement(By.xpath("//*[@id='EmailSettings_font']"));
		clickObject(myemail,"My Email Link");
		WebElement ename= driver.findElement(By.xpath("//*[@id='sender_name']"));
		validateTextBoxContent(ename, "Nu Taba", "Email name");;
		
		WebElement eadd= driver.findElement(By.xpath("//*[@id='sender_email']"));
		eadd.clear();
enterText(eadd,"nura.tabassum@gmail.com","Email Field");
		validateTextBoxContent(eadd,"nura.tabassum@gmail.com","Email Address");
		
		List<WebElement> radio_button=driver.findElements(By.name(("auto_bcc")));
		boolean bvalue;
		bvalue=radio_button.get(0).isSelected();
		
		if(bvalue== false){
			radio_button.get(0).click();
		}else{
			System.out.println("It is already selected choose other option");
		
		}
		
		
		WebElement save= driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]"));
		clickObject(save,"Save Button");
		Thread.sleep(1000);
		WebElement remainder= driver.findElement(By.xpath("//*[@id='CalendarAndReminders']/a"));
		clickObject(remainder,"Calenders & Remainders");
		
		WebElement remainders= driver.findElement(By.xpath("//*[@id='Reminders_font']"));
		clickObject(remainders,"Activity Remainders");
		
		driver.close();
		

}		
	
	
	public static void developerConsole_TC09(String browserName) throws InterruptedException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		driver=Modules.selectItemFromUserMenu(driver,"Developer Console");	
		
		String[] a=new String[3];
		int i=0;
		for(String window:driver.getWindowHandles()){
			a[i]=window.toString();
			i++;
		}
		Thread.sleep(3000);
		
		driver.switchTo().window(a[1]);
		String title=driver.getTitle();
		if(title.contains("Developer Console"))
			System.out.println("devloper window verified");
		else
			System.out.println("failed to verify");
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(a[0]);
		Modules.closeBrowser(driver);
	}
	
	public static void SelectLogoutFromUserMenu_TC10(String browserName) throws InterruptedException {

			driver=Modules.launchBrowser(browserName);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			driver=Modules.Login_toSalesforce(driver);
			driver=Modules.selectItemFromUserMenu(driver,"Logout");
			String url=driver.getCurrentUrl();
			if(url.equals("https://login.salesforce.com/"))
				System.out.println("passed");
			else
				System.out.println("not verified login url");
			Modules.closeBrowser(driver);

	}
	
	
	public static void CreateAccount_TC11(String browserName) throws InterruptedException {

			driver=Modules.launchBrowser(browserName);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			driver=Modules.Login_toSalesforce(driver);
			Thread.sleep(6000);
				
				WebElement accountTab=driver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));		
				accountTab.click();
				driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
				String accountText=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText();
				if(accountText.equalsIgnoreCase("Accounts"))
				System.out.println("account page is verified");
				else
					System.out.println("account page is not present");
				WebElement newAccount=driver.findElement(By.xpath(".//*[@id='hotlist']/table/tbody/tr/td[2]/input"));		
				newAccount.click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id='acc2']")).sendKeys("wellsFeb");
				driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
				String newAccountText=driver.findElement(By.xpath(".//*[@id='contactHeaderRow']/div[2]/h2")).getText();
				if(newAccountText.equalsIgnoreCase("wellsFeb"))
					System.out.println("new account verified");
				else
					System.out.println("couldnt verify new account");
				
				Modules.closeBrowser(driver);
				
				
		}
	public static void accountsNewView_12(String browserName)throws InterruptedException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		
		//click Accounts tab
		WebElement accountsTab=driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickObject(accountsTab,"Accounts Link ");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		
		
		//click create New view link
			WebElement createNewView=driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a[2]"));
			clickObject(createNewView," Create New View link ");
			
			//Enter view Name
			String Name="Win";
			WebElement viewName=driver.findElement(By.xpath("//input[@id='fname']"));
			viewName.clear();
			enterText(viewName,Name, " View name" );
			
			//click view Unique Name
			WebElement viewUniqueName=driver.findElement(By.xpath("//input[@id='devname']"));
			enterText(viewUniqueName,Name," view Unique Name " );
			
			//clickObject(viewUniqueName," view Unique Name ");
			
			//click save button
			WebElement saveNewView=driver.findElement(By.xpath("//input[@data-uidsfdc='3']"));
			clickObject(saveNewView," Save Button ");
			
			//click accnte view list 
			WebElement accntviewList=driver.findElement(By.xpath("//select[@title='View:']"));
			clickObject(accntviewList," Account view List");
			Thread.sleep(4000);
			//validate newely added view name in the current accnt list
			WebElement checkNewName=driver.findElement(By.xpath("//option[text()='Win']"));
			validateMessages(checkNewName, Name, " Correct newely added view name in the current account list");
			
}
	public static void editView_13(String browserName)throws InterruptedException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		
		//click Accounts tab
		WebElement accountsTab=driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickObject(accountsTab,"Accounts Link ");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		WebElement viewDropDown=driver.findElement(By.xpath("//*[@id='fcf']"));
		Select select=new Select(viewDropDown);
		select.selectByVisibleText("Facebook");
		Thread.sleep(4000);
		//click edit
		WebElement edit=driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a[1]"));
		clickObject(edit,"Edit view link");
		
		WebElement editname=driver.findElement(By.xpath("//*[@id='fname']"));
		enterText(editname,"Facebook","Edit view name");
		/*WebElement editUnique=driver.findElement(By.xpath("//*[@id='devname']"));
		clickObject(editUnique,"Edit Unique View");*/
		
		Thread.sleep(5000);
		
		WebElement filter=driver.findElement(By.xpath("//*[@id='fcol1']"));
		clickObject(filter,"Filter ");
		/*Select select1=new Select(filter);
		select.selectByVisibleText("Account Name");
		*/
		
		WebElement accName=driver.findElement(By.xpath("//*[@id='fcol1']/option[2]"));
		clickObject(accName,"Account name ");
		
		WebElement operator=driver.findElement(By.xpath("//*[@id='fop1']"));
		clickObject(operator,"Operator");
		Thread.sleep(3000);
		/*Select select2=new Select(operator);
		select.selectByVisibleText("contains");*/
		WebElement contains=driver.findElement(By.xpath("//*[@id='fop1']/option[5]"));
		clickObject(contains,"Contains");
		WebElement value=driver.findElement(By.xpath("//*[@id='fval1']"));
		enterText(value,"a","Value");//*[@id='colselector_select_0']/option[30]
		
		WebElement lastactivity=driver.findElement(By.xpath("//*[@id='colselector_select_0']/option[30]"));
		clickObject(lastactivity,"Last Activity");
		WebElement add1=driver.findElement(By.xpath("//*[@id='colselector_select_0_right']/img"));
		clickObject(add1,"Add Button");
		WebElement saveView=driver.findElement(By.xpath("//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]"));
		clickObject(saveView,"Save View");
		
		Thread.sleep(10000);
		
		driver.close();
		
		
	}
	public static void mergeAccount_14(String browserName)throws InterruptedException{
		String textData="Googleg";
		String mergeAccnt="Merge My Accounts";
		String col1Name="Googleg [Select All]";
		String col2Name="Googleg [Select All]";
		String recentName="Recent Accounts";
	
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		
		//click Accounts tab
		WebElement accountsTab=driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickObject(accountsTab,"Accounts Link ");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		//click Merge Accounts tab
		WebElement mergeAccounts=driver.findElement(By.xpath("//a[text()='Merge Accounts']"));
		clickObject(mergeAccounts,"Merge Accounts Links");
		
		//Enter text in merge box
		WebElement text=driver.findElement(By.xpath("//input[@id='srch']"));
		enterText(text, textData, "Text in Merge Accounts");
				
		//click findAccounts tab
		WebElement findAccountsTab=driver.findElement(By.xpath("//input[@name='srchbutton']"));
		clickObject(findAccountsTab,"Find Accounts Tab ");
		Thread.sleep(5000);
		//click checkbox
		WebElement chckBox1=driver.findElement(By.xpath("//*[@id='cid0']"));
		deSelectCheckBox(chckBox1,"first Checkbox");
		WebElement chckBox2=driver.findElement(By.xpath("//*[@id='cid1']"));
		deSelectCheckBox(chckBox2,"2nd Checkbox");
		clickObject(chckBox1,"Select Check Box 1 ");
	clickObject(chckBox2,"Select Check Box 2 ");
				
		//click Next Button
		WebElement nextButton=driver.findElement(By.xpath("//div[@class='pbTopButtons']/input[@name='goNext']"));
		clickObject(nextButton,"NEXT Button");	
		
		//validate My Merge Accounts in next page
		WebElement myMergeAccnt=driver.findElement(By.xpath("//div[@class='content']/h1[1]"));
		validateMessages(myMergeAccnt,mergeAccnt ," My Merge Accnt page ");
		
		//validate selected account details in My Merge Accounts page
		WebElement col1=driver.findElement(By.xpath("//th[@scope='col'][1]"));
		validateMessages(col1,col1Name ," 1st column in My Merge Accnt page ");
		
		//validate selected account details in My Merge Accounts page
		WebElement col2=driver.findElement(By.xpath("//th[@scope='col'][2]"));
		validateMessages(col2,col2Name ,"2nd column in  My Merge Accnt page ");
		
		//click Merge button in accounts page
		WebElement mergeButton=driver.findElement(By.xpath("//div[@class='pbTopButtons']/input[@title='Merge']"));
		clickObject(mergeButton,"Merge Button in Accnts page");
		
		// select ok in popup window
		Alert alert = (Alert) driver.switchTo().alert();
		driver.switchTo().alert().accept();
		Modules.closeBrowser(driver);
	
	}
	public static void AccountLastActivity_15(String browserName)throws InterruptedException, AWTException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		
		//click Accounts tab
		WebElement accountsTab=driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
		clickObject(accountsTab,"Accounts Link ");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		WebElement report=driver.findElement(By.xpath("//*[@id='toolsContent']/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a"));
		clickObject(report," Accounts with last activity --> 30 days link");
		//click date field
		WebElement date=driver.findElement(By.xpath("//*[@id='ext-gen148']"));
		clickObject(date,"Date Field");
		Thread.sleep(5000);
		//click create date
		Robot ro=new Robot();
		ro.keyPress(KeyEvent.VK_DOWN);
		ro.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		
	/*	WebElement created_date=driver.findElement(By.xpath("//*[@id='ext-gen265']/div[3]"));
		clickObject(created_date,"Created Date");*/
		WebElement from_date=driver.findElement(By.xpath("//*[@id='ext-gen152']"));
		clickObject(from_date,"From Date");
		WebElement fromDate=driver.findElement(By.name("startDate"));
		enterText(fromDate,LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")),"From Date");
		validateTextBoxContent(fromDate,LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")), "FromData text");
	/*	WebElement today=driver.findElement(By.xpath("//*[@id='ext-gen280']"));
		clickObject(today,"Today");*/
		
		WebElement to_date=driver.findElement(By.xpath("//*[@id='ext-gen154']"));
		clickObject(to_date,"From Date");
		WebElement toDate=driver.findElement(By.name("endDate"));
		enterText(toDate,LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")),"To Date");
		validateTextBoxContent(toDate,LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")), "ToData text");	
		
		
		
		
		
		/*WebElement today1=driver.findElement(By.xpath("//*[@id='ext-gen295']"));
		clickObject(today1,"Today");*/
		WebElement save=driver.findElement(By.xpath("//*[@id='ext-gen49']"));
		clickObject(save,"Save Button");
		Thread.sleep(3000);
		//String parentwindow=driver.getWindowHandle();
		//for(String currentwindow:driver.getWindowHandles())
			//driver.switchTo().window(currentwindow);
		WebElement rpt_name=driver.findElement(By.xpath("//*[@name='reportName']"));
		enterText(rpt_name,"Report10","Report Name field");
		WebElement rpt_Uname=driver.findElement(By.xpath("//*[@name='reportDevName']"));
		enterText(rpt_Uname,"UReport10","Report unique Name field");

		//Thread.sleep(7000);
		WebElement saveRun=driver.findElement(By.xpath("//*[@id='dlgSaveReport']/tbody/tr[2]/td[2]"));
		clickObject(saveRun,"Save Run Button");
		WebElement cancel=driver.findElement(By.xpath("//*[@id='ext-gen313']"));
		cancel.click();
		//driver.switchTo().window(parentwindow);
	Modules.closeBrowser(driver);
}
	public static void opportunityDropDown_16(String browserName) throws InterruptedException{
		String[] options=new String[]{"All Opportunities","Closing Next Month","Closing This Month","My Opportunities","New Last Week","New This Week","Opportunity Pipeline","Private","Recently Viewed Opportunities","Won"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		
		//click Opportunity tab
		WebElement opportunityTab=driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a"));
		clickObject(opportunityTab,"Opportunity Link ");
		Thread.sleep(3000);
		
		
		//click New button
		
		//Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		
		Thread.sleep(5000);
		//validate opportunities Home page
	    WebElement chckPage=driver.findElement(By.xpath("//h1[@class='pageType']"));
		validateMessages(chckPage,"Opportunities" , " Opportunities Home Page ");
		
/*		String oppText=driver.getTitle();//findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText();
		if(oppText.contains("Oppotunities"))
			System.out.println("account page is verified");
		else
			System.out.println("account page is not present");*/
		
		
		driver.findElement(By.xpath("//*[@id='fcf']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='fcf']/option"));
		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
		
			actData.add(ele.getText().trim());
		}
		System.out.println(actData);
		if(actData.equals(expList))
			System.out.println("usermenu verified ");
		else
			System.out.println("user menu not verified");
		Modules.closeBrowser(driver);
	}
	public static void NewOpportunity_17(String browserName)throws InterruptedException, AWTException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		WebElement opportunityTab=driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a"));
		clickObject(opportunityTab,"Opportunity Link ");
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		
		Thread.sleep(5000);	
	//click New Button
	WebElement newButton=driver.findElement(By.xpath("//input[@title='New']"));
	clickObject(newButton," New ButtonTab");
	
	//Enter Opportunity Name
	WebElement ON=driver.findElement(By.xpath("//input[@id='opp3']"));
	String opporName="innu";
	enterText(ON, opporName, "Opportunity Name field");
	
	//Enter Account Name
	WebElement AN=driver.findElement(By.xpath("//input[@id='opp4']"));
	String accountName="Apple";
	enterText(AN, accountName, "Account Name field");
	
	//Enter Close Date
	WebElement CD=driver.findElement(By.xpath("//input[@id='opp9']"));
	String mdy="3/18/2018";
	enterText(CD ,mdy, "Close Date field");
	driver.findElement(By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/div")).click();
	
	//click Stage Obj
	 WebElement stageObj=driver.findElement(By.xpath(("//select[@id='opp11']")));
	 clickObject(stageObj,"Stage field drop down");
	 
	 
	//click Perception Analysis menu from drop down
	 WebElement menu6=driver.findElement(By.xpath(("//option[@value='Perception Analysis']")));
	 clickObject(menu6,"Perception Analysis menu from drop down");
	 
	//click Probability field
	 WebElement probabilityField=driver.findElement(By.xpath(("//input[@id='opp12']")));
	 clickObject(probabilityField,"Probability field");
	 
	//click Lead Source
	 WebElement lead=driver.findElement(By.xpath(("//select[@id='opp6']")));
	 clickObject(lead,"Lead Source field drop down");
	 
	//click name in Lead Source drop down
	 WebElement leadName=driver.findElement(By.xpath(("//option[@value='Partner Referral']")));
	 clickObject(leadName,"Name in Lead Source field drop down");
	 
	//Enter Primary Campaign
	 WebElement primaryTab=driver.findElement(By.xpath(("//input[@id='opp17']")));
	 String pc=" ";
	 enterText(primaryTab ,pc, "Primary Campaign field");
	 
	//click Save Button
	 WebElement saveButton=driver.findElement(By.xpath(("//td[@id='bottomButtonRow']/input[1]")));
	 clickObject(saveButton,"Save Button");
	 
		//validate new opportunity page
		WebElement newPage=driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		String newOpp="innu";
		validateMessages(newPage,newOpp, " New opportunity page");
		//Thread.sleep(15000);
		Modules.closeBrowser(driver);
}
	public static void OpportunityPipeline_18(String browserName)throws InterruptedException, AWTException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		WebElement opportunityTab=driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a"));
		clickObject(opportunityTab,"Opportunity Link ");
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		
		Thread.sleep(5000);	
		//validate opportunities Home page
	    WebElement chckOppage=driver.findElement(By.xpath("//h1[@class='pageType']"));
		validateMessages(chckOppage,"Opportunities" , " Opportunities Home Page ");
		
		//Click on Opportunity Pipeline link  
		WebElement oppPipeLink=driver.findElement(By.xpath("//a[text()='Opportunity Pipeline']"));
		clickObject(oppPipeLink,"Opportunity Pipeline link");
		
		//validate opportunity PipelIne report page
	    WebElement chckOppPipelinePage=driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
	    String crrctOppPipePage="Opportunity Pipeline";
		validateMessages(chckOppPipelinePage,crrctOppPipePage , " opportunity PipelIne Home Page ");
		
		Thread.sleep(5000);
		//click Opportunity tab
		WebElement opportunityTab2=driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a"));
		clickObject(opportunityTab2,"Opportunity Link ");
		
		//validate opportunities Home page
	    WebElement checkOpPage1=driver.findElement(By.xpath("//h1[@class='pageType']"));
		validateMessages(checkOpPage1,"Opportunities" , " Opportunities Home Page ");
		
    	//Click on Stuck Opportunities link  
    	WebElement stuckOpp=driver.findElement(By.xpath("//a[text()='Stuck Opportunities']"));
    	clickObject(stuckOpp,"Stuck Opportunities link");
    	Thread.sleep(2000);
    	
    	//validate Stuck Opportunities report page
	    WebElement chckOppStuckpage=driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
	    String crrctOppStuckPage="Stuck Opportunities";
		validateMessages(chckOppStuckpage,crrctOppStuckPage , " Stuck Opportunities report page ");
		Thread.sleep(5000);
		Modules.closeBrowser(driver);
}
	public static void QuarterlySummary_19(String browserName)throws InterruptedException, AWTException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		WebElement opportunityTab=driver.findElement(By.xpath("//*[@id='Opportunity_Tab']/a"));
		clickObject(opportunityTab,"Opportunity Link ");
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		
		Thread.sleep(5000);	
		//validate opportunities Home page
	    WebElement checkOpPage2=driver.findElement(By.xpath("//h1[@class='pageType']"));
		validateMessages(checkOpPage2,"Opportunities" , " Opportunities Home Page ");
		
		//validate Quarterly Summary link  
		WebElement quarterlyLink=driver.findElement(By.xpath("//h3[text()='Quarterly Summary']"));
		String validQuartelyName="Quarterly Summary";
		validateMessages(quarterlyLink,validQuartelyName, "Quarterly Summary link ");
		
		String[] Intervaloptions=new String[]{"Current FQ", "Next FQ","Previous FQ","Current and Next 3 FQ","Current FY","Current and Next FY","Current and Previous FY","Previous FY","Next FY"};
		List<String> expIntervalList=new ArrayList<String>(Arrays.asList(Intervaloptions));
		//click interval DropDown
		WebElement interval=driver.findElement(By.xpath("//select[@id='quarter_q']"));
		clickObject(interval,"interval DropDown");
		
		Thread.sleep(3000);
		
		//validate dropdown list menu
		
		List<WebElement> intervallist=driver.findElements(By.xpath("//*[@id='quarter_q']/option"));
		System.out.println(intervallist);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele1:intervallist){
		
			actData.add(ele1.getText().trim());
		}
		System.out.println(actData);
		if(actData.equals(expIntervalList))
			System.out.println("intervalmenu verified ");
		else
			System.out.println("interval menu not verified");
		Thread.sleep(4000);
		 //click current and Next FQ
		  WebElement current=driver.findElement(By.xpath("//option[text()='Current and Next FQ']"));
		  clickObject(current,"current and Next FQ in DropDown");
		
		Thread.sleep(5000);
		//click include list
		WebElement include=driver.findElement(By.xpath("//*[@id='open']"));
		clickObject(interval,"include DropDown");
		
		//validate includes list menu
		String[] Includeoptions=new String[] {"All Opportunities","Open Opportunities","Closed Opportunities","Closed/Won Opportunities"};
		List<String> expIncudelList=new ArrayList<String>(Arrays.asList(Includeoptions));
		List<WebElement> includelist=driver.findElements(By.xpath("//*[@id='open']/option"));
		System.out.println(includelist);
		ArrayList<String> actData1=new ArrayList<String>();
		for(WebElement ele2:includelist){
		
			actData1.add(ele2.getText().trim());
		}
		System.out.println(actData1);
		if(actData1.equals(expIncudelList))
			System.out.println("include menu verified ");
		else
			System.out.println("include menu not verified");
		Thread.sleep(3000);
		 //click Open Opportunities
		  WebElement openOpp=driver.findElement(By.xpath("//option[text()='Open Opportunities']"));
		  clickObject(openOpp,"Open Opportunities in DropDown");
		  
		//click Run Report
		  WebElement runReport=driver.findElement(By.xpath("//input[@value='Run Report']"));
		  clickObject(runReport,"Run Report Button");
		  
		//validate opportunities Report in results page
		    WebElement chckOppReport=driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		    String reportmsg="Opportunity Report";
			validateMessages(chckOppReport,reportmsg ," Opportunities Home Page ");
		
		
	driver.close();
	
	


	}
	public void leadsDropDown_20(String browserName) throws InterruptedException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		logger=extent.createTest("Leads");
		WebElement leadsTab=driver.findElement(By.xpath("//a[@title='Leads Tab']"));
		clickObject(leadsTab,"Leads Tab");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		
		SoftAssert softAssert=new SoftAssert();
		//validate leads Home page
				WebElement chckLead=(driver.findElement(By.xpath("//h1[@class='pageType']")));
				String expectedTitle="Leads";
				
			//String expectedTitle="xxxxx";
			String actualTitle=chckLead.getText().trim();
			
			
			softAssert.assertEquals(actualTitle, expectedTitle);
			softAssert.assertAll();
			Thread.sleep(3000);
			
			String[] options=new String[]{"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads","View - Custom 1","View - Custom 2"};
			List<String> expList=new ArrayList<String>(Arrays.asList(options));
			/*driver=ReusableMethods.launchBrowser("firefox");
			driver=ReusableMethods.Login_toSalesforce(driver);
			Thread.sleep(6000);*/
			//click View Drop down in leads home page
			WebElement leadsDropDown=driver.findElement(By.xpath("//select[@id='fcf']"));
			clickObject(leadsDropDown,"Drop down in leads home page");
		
			List<WebElement> list=driver.findElements(By.xpath("//*[@id='fcf']/option"));
			System.out.println(list);
			ArrayList<String> actData=new ArrayList<String>();
			for(WebElement ele:list){
			
				actData.add(ele.getText().trim());
			}
			System.out.println(actData);
			if(actData.equals(expList)){
System.out.println("user menu verified");
				logger.log(Status.PASS,"LeadsDropdown verified ");
			}	
			else{
System.out.println("not verified");
				logger.log(Status.FAIL,"LeadsDropdown not verified");
	}
			Modules.closeBrowser(driver);
	}
	public void Todaysleads_21(String browserName) throws InterruptedException{
	driver=Modules.launchBrowser(browserName);
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	WebDriverWait wait = new WebDriverWait(driver, 40);
	driver=Modules.Login_toSalesforce(driver);
	Thread.sleep(6000);
	logger=extent.createTest("Leads");
	WebElement leadsTab=driver.findElement(By.xpath("//a[@title='Leads Tab']"));
	clickObject(leadsTab,"Leads Tab");
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
	
	 //click Go Button
	   WebElement goButton=driver.findElement(By.xpath("//*[@id='filter_element']/div/span/span[1]/input"));
	   clickObject(goButton,"Go Button");   ////*[@id='filter_element']/div/span/span[1]/input
	   
	   Thread.sleep(3000);
	   
	   //Validate Todays Leads in default view
	   WebElement chckTodaysLead=driver.findElement(By.xpath("//select[@name='fcf']"));
	   Select lead=new Select(chckTodaysLead);
	   WebElement option = lead.getFirstSelectedOption();
	   String actual=option.getText();
	   String selectedLead="Today's Leads";
	   if(actual.equals(selectedLead)){
		   logger.log(Status.PASS,"Todays Leads Is displayed");
	   }else{
		   logger.log(Status.FAIL,"Not Displayed");
	   }
	   Thread.sleep(5000);
	   
	   WebElement leadsTab1=driver.findElement(By.xpath("//*[@id='Lead_Tab']/a"));
		clickObject(leadsTab1,"Leads Tab");
		Thread.sleep(3000);
		Modules.closeBrowser(driver);
}
	public void Newleads_22(String browserName) throws InterruptedException{
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		logger=extent.createTest("Leads");
		WebElement leadsTab=driver.findElement(By.xpath("//a[@title='Leads Tab']"));
		clickObject(leadsTab,"Leads Tab");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
	 //Click New Button
	   WebElement newBtn=driver.findElement(By.name("new"));
	   clickObject(newBtn,"new button displayed in the Recent Leads frame");
	   
	   
	   //Enter LastName
	   WebElement lName=driver.findElement(By.xpath("//input[@name='name_lastlea2']"));
	   String lastName="ABCD";
	   enterText(lName,lastName, "Last Name");
	   
	   //Enter Company Name
	   WebElement cName=driver.findElement(By.id("lea3"));
	   String cmpnyName="ABCD";
	   enterText(cName,cmpnyName, "Last Name");
	   
	 //Click Save Button
	   WebElement saveBtn=driver.findElement(By.xpath("//td[@id='topButtonRow']/input[1]"));
	   clickObject(saveBtn,"Save Button in the New Lead");
	   
	   Thread.sleep(5000);
	   
	// Validate newly created lead view page should be opened
	   WebElement chckNewlyCreated=driver.findElement(By.xpath("//*[@id='contactHeaderRow']/div[2]/h2"));
	   String actualCreated= chckNewlyCreated.getText().trim();
	   String expCreated="ABCD";
	   softAssert.assertEquals(actualCreated, expCreated);
	   
	   softAssert.assertAll();
	   Modules.closeBrowser(driver);

}
	public void Newcontact_23(String browserName) throws InterruptedException{
		
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
	
		   //click Contacts tab
		logger=extent.createTest("Contacts");
		
		//SoftAssert softAssert=new SoftAssert();
		   WebElement cntctTab=driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		   clickObject(cntctTab,"Contact Tab");
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		   //validate Contacts Home page
		   WebElement chckCntct=driver.findElement(By.xpath("//h1[@class='pageType']"));
		   String actcontct=chckCntct.getText().trim();
		   String cntctName="contacts";
		   validateMessages(chckCntct, actcontct, " Contacts Home Page ");
		   
		   //Click New Button
		   WebElement newBtn=driver.findElement(By.name("new"));
		   clickObject(newBtn,"new button displayed in Contacts");
		   
		   Thread.sleep(3000);
		   
		  // Validate New Lead creation page should open
		 /*  WebElement chckNewcontact=driver.findElement(By.xpath("//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		   String actNewcontct=chckCntct.getText();
		   String chckPage="New Contact";
		   //validateMessages(chckNewcontact, chckPage, " New Contact creation page");
		   softAssert.assertEquals(actNewcontct,chckPage);
		   
		   softAssert.assertAll();
		   
		   Thread.sleep(5000);
		   */
		   //Enter LastName
		   WebElement lName=driver.findElement(By.xpath("//*[@id='name_lastcon2']"));
		   String lastName="ABCD";
		   enterText(lName,lastName, "Last Name");
		   
		   //Enter Company Name
		   WebElement AcName=driver.findElement(By.xpath("//*[@id='con4']"));
		   String accName="ABCD";
		   enterText(AcName,accName, " Account Name");
		   
		 //Click Save Button
		   WebElement saveBtn=driver.findElement(By.xpath("//*[@id='topButtonRow']/input[1]"));
		   clickObject(saveBtn,"Save Button in the New Contact");
	Modules.closeBrowser(driver);	   
}
public void NewcontactView_24(String browserName) throws InterruptedException{
		
		driver=Modules.launchBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
	
		   //click Contacts tab
		logger=extent.createTest("Contacts");
		
		//SoftAssert softAssert=new SoftAssert();
		   WebElement cntctTab=driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
		   clickObject(cntctTab,"Contact Tab");
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
	 //Click create new view hyperlink
	   WebElement createnewView=driver.findElement(By.linkText("Create New View"));
	   clickObject(createnewView,"create new view hyperlink in contacts page");
	   
	 //validate create new view Home page
	   WebElement chckCrtNew=driver.findElement(By.xpath("//h2[@class='pageDescription']"));
	   String crtViewName="Create New View";
	   validateMessages(chckCrtNew, crtViewName, " Create New View ");
	   
	 //Enter view Name
	   WebElement viewName=driver.findElement(By.id("fname"));
	   String viewname="aiii";
	   enterText(viewName, viewname, "View Name");
	   
	   //Click unique name
	   WebElement uniqueName=driver.findElement(By.id("devname"));
	   enterText(uniqueName, viewname, "Unique View Name");
	  // clickObject(uniqueName,"Unique name hyperlink");
	   
	 //Click Save Button
	   WebElement saveBtn=driver.findElement(By.xpath("//input[@data-uidsfdc='3']"));
	   clickObject(saveBtn,"Save Button in the view Unique name");
	   
	   //validate view name in contact page dropdown menu
	    WebElement chckViewName=driver.findElement(By.xpath("//select[@name='fcf']"));
	    String actViewName=chckViewName.getText().trim();
	    softAssert.assertEquals(actViewName, "aiii");
		   
		   //softAssert.assertAll();
		   Modules.closeBrowser(driver);
}
}
