package testscripts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utility.Modules;
import utility.ReUsableMethod;

public class DriverFile {
	public static boolean  status=true;
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public static void main(String[] args) throws Exception {
		
		String cur_dir = System.getProperty("user.dir");
		String suitePath = cur_dir + "/src/test/java/utility/TestSuite.xls";
		String[][] recData = Modules.readXlSheet(suitePath, "Sheet1");
		
		extent=ReUsableMethod.startReport(cur_dir+"/ExtentReport/ExtentReport.html");
		
		String testCase,flag,firefoxStatus,chromeStatus,ieStatus;
		
		for (int i = 1; i < recData.length; i++) {
			testCase = recData[i][0];
			flag = recData[i][1];
			if(flag.equalsIgnoreCase("y")){
				
				firefoxStatus=recData[i][2];
				chromeStatus=recData[i][4];
				ieStatus=recData[i][6];
				
				if(firefoxStatus.equalsIgnoreCase("y")){

					logger=ReUsableMethod.createTestReport(testCase+" in firefox",extent);
					Method tc = AutoScripts.class.getMethod(testCase,String.class);
					tc.invoke(new AutoScripts(),"firefox");
					if(status==true){
						Modules.writeXlSheet(suitePath,"Sheet1","pass",i,3);
						Modules.setXlColorStyle(suitePath,"Sheet1",i,3,"pass");
					}
					else{
						Modules.writeXlSheet(suitePath,"Sheet1","fail",i,3);
						Modules.setXlColorStyle(suitePath,"Sheet1",i,3,"fail");
					}
				}
				if(chromeStatus.equalsIgnoreCase("y")){
					Thread.sleep(5000);
					logger=ReUsableMethod.createTestReport(testCase+" in chrome",extent);
					Method tc = AutoScripts.class.getMethod(testCase,String.class);
					tc.invoke(new AutoScripts(),"chrome");
					if(status==true){
						Modules.writeXlSheet(suitePath,"Sheet1","pass",i,5);
						Modules.setXlColorStyle(suitePath,"Sheet1",i,5,"pass");
					}
					else{
						Modules.writeXlSheet(suitePath,"Sheet1","fail",i,5);
						Modules.setXlColorStyle(suitePath,"Sheet1",i,5,"fail");
					}
				}
				
				if(ieStatus.equalsIgnoreCase("y")){
					logger=ReUsableMethod.createTestReport(testCase+" in internet explorer",extent);
					Method tc = AutoScripts.class.getMethod(testCase,String.class);
					tc.invoke(new AutoScripts(),"ie");
					if(status==true){
						Modules.writeXlSheet(suitePath,"Sheet1","pass",i,7);
						Modules.setXlColorStyle(suitePath,"Sheet1",i,7,"pass");
					}
					else{
						Modules.writeXlSheet(suitePath,"Sheet1","fail",i,7);
						Modules.setXlColorStyle(suitePath,"Sheet1",i,7,"fail");
					}
				}
			}
		}
		ReUsableMethod.endReport(extent);
	//	extent.flush();

	}

}
