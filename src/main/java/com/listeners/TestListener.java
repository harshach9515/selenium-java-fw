package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.DriverManager;
import com.reports.ExtentManager;
import com.reports.Report;
import com.utils.ScreenShotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Report.startTest(result.getMethod().getMethodName());
        Report.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Report.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenShotUtils.captureScreenshot(DriverManager.getDriver(),
                result.getMethod().getMethodName());
        Report.fail(result.getThrowable().toString());
        Report.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Report.skip("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        // can be used for global setup
    }

    @Override
    public void onFinish(ITestContext context) {
        Report.getTest(); // optional flush handled in ExtentManager
        ExtentManager.getInstance().flush();
    }
}
