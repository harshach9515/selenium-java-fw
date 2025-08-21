package com.reports;

import com.aventstack.extentreports.ExtentTest;

public class Report {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void startTest(String testName) {
        test.set(ExtentManager.getInstance().createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void info(String message) {
        getTest().info(message);
    }

    public static void pass(String message) {
        getTest().pass(message);
    }

    public static void fail(String message) {
        getTest().fail(message);
    }

    public static void skip(String message) {
        getTest().skip(message);
    }
}
