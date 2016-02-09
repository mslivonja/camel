package org.apache.camel.example.util;

import org.apache.camel.Exchange;
import org.apache.camel.example.model.TestCase;
import org.apache.camel.example.model.TestStep;

/**
 * Created by eslimat on 29.1.2016..
 */
public interface TestCaseManager {

    static final String TEST_CASE_CODE = "TestCaseCode";

    /**
     * Resolve currently active test case.
     *
     * @param exchange Current Apache camel exchange
     * @return Test case object or <code>null</code> if test case cannot be resolved.
     */
    TestCase getActiveTestCase(Exchange exchange);

    /**
     * Get next test step for current test case.
     *
     * @param exchange Current Apache camel exchange
     * @return  Next test step or <code>null</code> if test case cannot be resolved.
     */
    TestStep getNextTestStep(Exchange exchange);
}
