package com.data;

import com.utils.LogManager;

public class TestDataManager {
    private static org.apache.log4j.Logger log = LogManager.getLogger(TestDataManager.class);

    /**
     * Fetches test data based on the test name (method name).
     * 
     * @param testName name of the test method
     * @return TestData object with values loaded
     */
    public static TestData getDataForTest(String testName) {
        try {
            log.info("Fetching test data for: " + testName);
            return ExcelUtils.getTestData(testName);
        } catch (Exception e) {
            log.error("Failed to fetch test data for: " + testName, e);
            return null;
        }
    }
}
