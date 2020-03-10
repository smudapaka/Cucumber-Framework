/*package extentReports;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {

	ExtentTest logger;
	ExtentReports extent;
	static ExtentReports extentxReporter;

	public static void resultsPass(String Report1, String TCSName, String Desc) throws IOException

	{

		String text = "./Reports/" + Report1 + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(text);
		htmlReporter.setAppendExisting(true);
		extentxReporter = new ExtentReports();
		extentxReporter.attachReporter(htmlReporter);
		ExtentTest logger = extentxReporter.createTest(TCSName);
		logger.log(Status.PASS, "" + Desc);
		//logger.log(Status.PASS, TestCaseName + "" + Desc);
		// logger.fail("details",
		// MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		extentxReporter.flush();

	}

	public static void resultsFail(String Report1, String TCSName,
			String TestCaseName, String Desc) throws IOException

	{

		String text = "./Reports/" + Report1 + ".html";

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(text);
		htmlReporter.setAppendExisting(true);
		ExtentReports extentxReporter = new ExtentReports();
		extentxReporter.attachReporter(htmlReporter);
		ExtentTest logger = extentxReporter.createTest(TCSName);
		// logger.log(Status.FAIL, TestCaseName+"" +Desc);
		// logger.log(Status.FAIL, TestCaseName+"" +Desc+
		// MediaEntityBuilder.createScreenCaptureFromPath("E:\\screenshot.png").build());
		logger.log(Status.FAIL, TestCaseName + "" + Desc).addScreenCaptureFromPath("E:\\sc.png");
		extentxReporter.flush();

	}

}
*/