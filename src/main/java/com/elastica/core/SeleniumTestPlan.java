/*
 * Copyright 2015 www.seleniumtests.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elastica.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.elastica.dataobject.User;
import com.elastica.driver.BrowserType;
import com.elastica.driver.WebUIDriver;

/**
 * This class initializes context, sets up and tears down and clean up drivers An STF test should extend this class.
 */
public abstract class SeleniumTestPlan {
    private static final Logger logger = TestLogging.getLogger(SeleniumTestPlan.class);
    private Date start;
    public  BrowserType browserType;
    public Map<String, String> testConfig= new HashMap<String, String>();
    public  User user = new User();
    
    
    /**
     * @param   testContext
     *
     * @throws  IOException
     */
    @BeforeSuite(alwaysRun = true)
    public void beforeTestSuite(final ITestContext testContext) throws IOException {
    	System.out.println(testContext);
        System.out.println("Inside before Suite");
        start = new Date();
        //SeleniumTestsContextManager.initGlobalContext(testContext);
        //SeleniumTestsContextManager.initThreadContext(testContext, null);
    }

    /**
     * Configure Test Params setting.
     *
     * @param  xmlTest
     */
    @BeforeTest(alwaysRun = true)
    public void beforeTest(final ITestContext testContext, final XmlTest xmlTest) {
    	System.out.println("Inside before test");
       // SeleniumTestsContextManager.initTestLevelContext(testContext, xmlTest);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethod(final Object[] parameters, final Method method, final ITestContext testContex,
            final XmlTest xmlTest) {
    	browserType=BrowserType.getBrowserType(testContex.getCurrentXmlTest().getParameter("browser"));
    	
    	System.out.println(testContex.getCurrentXmlTest().getTestParameters());
    	
    	testConfig.put("browser",testContex.getCurrentXmlTest().getParameter("browser") );
      //	testConfig.put("browser",testContex.getCurrentXmlTest().getParameter("browser") );
      	
    	/*System.out.println("Map: "+testConfig.get("browser"));
    	System.out.println(testContex.getCurrentXmlTest().getAllParameters());
    	System.out.println(testContex.getCurrentXmlTest().getParameter("browser"));*/
    	user.setUserID("elastictest@hotmail.com");
    	user.setPassword("Test12!@");
    	System.out.println("Insdie before method");
        logger.info(Thread.currentThread() + " Start method " + method.getName());
        SeleniumTestsContextManager.initThreadContext(testContex, xmlTest);
       /* if (method != null) {
            SeleniumTestsContextManager.getThreadContext().setAttribute(SeleniumTestsContext.TEST_METHOD_SIGNATURE,
                buildMethodSignature(method, parameters));
        }*/
    }

    @AfterSuite(alwaysRun = true)
    public void afterTestSuite() {
    	//WebUIDriver.cleanUp();
        logger.info("Test Suite Execution Time: " + (new Date().getTime() - start.getTime()) / 1000 / 60 + " minutes.");
    }
    

    /**
     * clean up.
     *
     * @param  parameters
     * @param  method
     * @param  testContex
     * @param  xmlTest
     */
    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(final Object[] parameters, final Method method, final ITestContext testContex,
            final XmlTest xmlTest) {
    	System.out.println("Inside After method");
        List<TearDownService> serviceList = SeleniumTestsContextManager.getThreadContext().getTearDownServices();
        if (serviceList != null && !serviceList.isEmpty()) {
            for (TearDownService service : serviceList) {
                service.tearDown();
            }
        }

       WebUIDriver.cleanUp();
        logger.info(Thread.currentThread() + " Finished method " + method.getName());
    }

    
    @AfterTest(alwaysRun = true)
    public void afterTest() {
    	System.out.println("Inside After Test");
      

       // WebUIDriver.cleanUp();
       // logger.info(Thread.currentThread() + " Finished method " + method.getName());
    }
    
    

  
    
    private String buildMethodSignature(final Method method, final Object[] parameters) {
        return method.getDeclaringClass().getCanonicalName() + "." + method.getName() + "("
                + buildParameterString(parameters) + ")";
    }

    /**
     * Remove name space from parameters.
     *
     * @param   parameters
     *
     * @return
     */
    private String buildParameterString(final Object[] parameters) {
        StringBuffer parameter = new StringBuffer();

        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] == null) {
                    parameter.append("null, ");
                } else if (parameters[i] instanceof java.lang.String) {
                    parameter.append("\"").append(parameters[i]).append("\", ");
                } else {
                    parameter.append(parameters[i]).append(", ");
                }
            }
        }

        if (parameter.length() > 0) {
            parameter.delete(parameter.length() - 2, parameter.length() - 1);
        }

        return parameter.toString();
    }
}
