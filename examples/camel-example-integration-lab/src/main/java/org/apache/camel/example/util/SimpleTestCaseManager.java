package org.apache.camel.example.util;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.jms.JmsEndpoint;
import org.apache.camel.component.servlet.ServletEndpoint;
import org.apache.camel.example.model.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
/**
 * Created by eslimat on 29.1.2016..
 */
public class SimpleTestCaseManager implements TestCaseManager {

    private static final Logger log = Logger.getLogger(SimpleTestCaseManager.class);

    private TestCase currentTestCase;

    private TestStep currentTestStep = START_TEST_STEP;

    public static final TestStep START_TEST_STEP = new TestStep(0, "Test step [000] :: Start", true, false);

    public static final TestStep END_TEST_STEP = new TestStep(-1, "Test step [Inf.] :: End", false, true);

    private int sequence = 0;

    private File rootDir;

    public SimpleTestCaseManager(){

        String code = System.getProperty(TEST_CASE_CODE);
        if(StringUtils.isEmpty(code))
            throw new RuntimeException("Unable to resolve system property '" + TEST_CASE_CODE + "'");

        createTestCase(code);
    }

    private void createTestCase(String code){
        this.currentTestCase = new TestCase();
        currentTestCase.setCode(code);
        currentTestCase.setDescription("Simple test case '" + currentTestCase.getCode() + "'");

        // tc.getTestSteps will not return anything
        // number of test steps is not bound
        log.info("Active Test Case SET to '" + code + "'");
    }

    public File getRootDir() {
        return rootDir;
    }

    public void setRootDir(File rootDir) {
        this.rootDir = rootDir;
    }

    public void setActiveTestCase(String code){
        createTestCase(code);
        // reset sequence
        sequence = 0;
        log.info("Test Case manager - RESET");
    }

    public TestCase getActiveTestCase(Exchange exchange) {
        return currentTestCase;
    }

    public Integer getCurrentTestStepId(){
        return this.sequence;
    }

    public void setCurrentTestStepId(int sequenceId){
        this.sequence = sequenceId;
        log.info("SET Test step to " + sequenceId + " for Test Case '" + currentTestCase.getCode() + "'");
    }

    public TestStep getNextTestStep(Exchange exchange) {

        TestStep ts = createTestStep(exchange, sequence);

        String reqPath = ts.getRequest().getPath();
        if(!FilenameUtils.getName(reqPath).startsWith("default"))
            // increment sequence id only for non default
            this.sequence++;

        return ts;
    }

    private TestStep createTestStep(Exchange exchange, final int sequenceId){
        TestStep ts = new TestStep();

        ts.setId(sequenceId);
        ts.setDescription("Test step [" + String.format("%03d", sequenceId) + "]");
        ts.setStart(sequenceId==0);

        String testCaseCode = getActiveTestCase(exchange).getCode();
        String testStepSubDir = getRootDir().getPath() + "/" + testCaseCode + "/" + getTestStepSubdirectory(exchange);

        Request req = new Request();
        req.setDirection(DirectionType.INBOUND);
        String reqPath = resolveMessageFile(testStepSubDir, sequenceId, MessageType.REQ);
        req.setPath(reqPath);
        ts.setRequest(req);

        Response rsp = new Response();
        rsp.setDirection(DirectionType.OUTBOUND);
        rsp.setPath(resolveMessageFile(testStepSubDir, sequenceId, MessageType.RSP));
        ts.setResponse(rsp);

        return ts;
    }

    private String resolveMessageFile(String dirNm, final int id, final MessageType msgType){
        File fDir = new File(dirNm);
        final String filePrefix = String.format("%03d", id) + "-" + msgType.name();

        String[] fNms= fDir.list(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.startsWith(filePrefix);
            }
        });

        if(fNms==null || fNms.length==0){
            // did not find specific test step message,
            // try to resolve default
            fNms = fDir.list(new FilenameFilter() {

                public boolean accept(File dir, String name) {
                    return name.startsWith("default-" + msgType.name());
                }
            });

            if(fNms==null || fNms.length==0)
                // could not find default message
                throw new RuntimeException("Unable to resolve test step for '" + dirNm + "' and '" + filePrefix + "'");
        }

        if(fNms.length > 1){
            // more then one message found
            throw new RuntimeException("Ambitious message file name '" + filePrefix + "', found " + fNms.length + " files");
        }

        return dirNm + "/" + fNms[0];
    }

    private String getTestStepSubdirectory(Exchange exchange){

        String componentType = getComponentType(exchange);

        if(componentType==null)
            // could not resolve component type
            return null;

        StringBuilder sb = new StringBuilder();
        sb.append(componentType);

        if(componentType.equalsIgnoreCase("WS")){
            CxfEndpoint cxfEndpoint = (CxfEndpoint) exchange.getFromEndpoint();

            Class serviceClass = cxfEndpoint.getServiceClass();
            sb.append("/");
            sb.append(serviceClass.getSimpleName());

            String operationName = (String) exchange.getIn().getHeader("operationName");
            sb.append("/");
            sb.append(operationName);
        }

        return sb.toString();
    }

    private String getComponentType(Exchange exchange){
        Endpoint fromEndpoint = exchange.getFromEndpoint();
        if(fromEndpoint instanceof JmsEndpoint)
            return "JMS";
        else if(fromEndpoint instanceof CxfEndpoint)
            return "WS";
        else if(fromEndpoint instanceof ServletEndpoint){
            StringBuilder sb = new StringBuilder();
            sb.append("REST");

            String method = (String)exchange.getIn().getHeader("CamelHttpMethod");
            if(!StringUtils.isEmpty(method)) {
                sb.append("/");
                sb.append(method);
            }

            String httpUri = (String)exchange.getIn().getHeader("CamelHttpUri");
            if(!StringUtils.isEmpty(httpUri)){
                httpUri = (httpUri.startsWith("/"))?httpUri.substring(1):httpUri;
                if(httpUri.startsWith("services/REST/"))
                    // remove context path 'services/REST'
                    httpUri = httpUri.substring(14);
                sb.append("/");
                sb.append(httpUri);
            }
            return sb.toString();
        }

        return null;
    }
}
