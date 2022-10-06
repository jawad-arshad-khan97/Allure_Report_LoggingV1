import io.qameta.allure.Allure;
import org.testng.*;

import java.io.File;
import java.util.List;

public class AutomationListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

    public AutomationListener() {
    }

    @Override
    public void onStart(ISuite iSuite) {
        // Check the existence of ExtentReports folder in Project if not exist then Create a folder
        //deleting files present under allure-reports
        try {
            File allureResults = new File("./allure-results");

            for (File f : allureResults.listFiles()) {
                if (f.getName().contains(".txt")) {
                    f.delete();
                }

                if (f.getName().contains("container.json")) {
                    f.delete();
                }

                if (f.getName().contains("result.json")) {
                    f.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testName = iTestResult.getName();

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String testName = iTestResult.getName();

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = iTestResult.getName();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String testName = iTestResult.getName();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE || iTestResult.getStatus() == ITestResult.SKIP) {
            List<String> testLogs = Reporter.getOutput(iTestResult);
            attachLogsInAllureReport(testLogs);
            Reporter.clear();
        }
    }

    private void attachLogsInAllureReport(List testLogs) {
        for (Object testLog : testLogs) {
            Allure.addAttachment("Logs", testLog.toString());
        }
    }
}