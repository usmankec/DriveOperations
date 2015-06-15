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

package com.elastica.browserfactory;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.elastica.core.TestLogging;
import com.elastica.driver.BrowserType;
import com.elastica.driver.DriverConfig;
import com.elastica.driver.DriverMode;
import com.elastica.driver.ScreenShotRemoteWebDriver;
import com.elastica.helper.WaitHelper;
import com.saucelabs.common.SauceOnDemandAuthentication;

public class RemoteDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory {
	public static SauceOnDemandAuthentication authentication;
	
    public RemoteDriverFactory(final DriverConfig cfg) {
        super(cfg);
    }

    @Override
    public WebDriver createWebDriver() throws MalformedURLException, IllegalArgumentException, SecurityException,
        InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
        ClassNotFoundException {
        DriverConfig webDriverConfig = this.getWebDriverConfig();
        DesiredCapabilities capability = null;
        URL url;

        url = new URL(webDriverConfig.getHubUrl());
        
        if (webDriverConfig.getMode()==DriverMode.SauceLab){
        	String username ="usman";String key = "04128b85-c2d9-4d5e-bf2a-2d223cc56c38";
        	   if (username.isEmpty() && key.isEmpty()) {
        	    authentication = new SauceOnDemandAuthentication();
        	         } else {
        	             authentication = new SauceOnDemandAuthentication(username, key);
        	         }
        	  // url = new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");
        	  url = new URL("http://usmankec:04128b85-c2d9-4d5e-bf2a-2d223cc56c38@ondemand.saucelabs.com:80/wd/hub");
        	   
        	   
        	   
        	   System.out.println(url);
        }
        	

        switch (webDriverConfig.getBrowser()) {

            case FireFox :
                capability = new FirefoxCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case InternetExplore :
                capability = new IECapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case Chrome :
                capability = new ChromeCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case HtmlUnit :
                capability = new HtmlUnitCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case Safari :
                capability = new SafariCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case Android :
                capability = new AndroidCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case IPhone :
                capability =
                    ((ICapabilitiesFactory) Class.forName("com.elastica.browserfactory.IPhoneCapabilitiesFactory")
                            .getConstructor().newInstance()).createCapabilities(webDriverConfig);
                break;

            case IPad :
                capability =
                    ((ICapabilitiesFactory) Class.forName("com.elastica.browserfactory.IPadCapabilitiesFactory")
                            .getConstructor().newInstance()).createCapabilities(webDriverConfig);
                break;

            case Opera :
                capability = new OperaCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            case PhantomJS :
                capability = new PhantomJSCapabilitiesFactory().createCapabilities(webDriverConfig);
                break;

            default :
                break;
        }

        switch (webDriverConfig.getBrowser()) {

            case IPhone :
            case IPad :
                driver = (WebDriver) Class.forName("com.elastica.browserfactory.RemoteIOSBaseDriver")
                                          .getConstructor(URL.class, DesiredCapabilities.class).newInstance(url,
                                              capability);
                break;

            case FireFox :
                try {
                    driver = new ScreenShotRemoteWebDriver(url, capability);
                } catch (RuntimeException e) {
                    if (e.getMessage().contains(
                                "Unable to connect to host 127.0.0.1 on port 7062 after 45000 ms. Firefox console output")) {
                        TestLogging.log("Firefox Driver creation got port customexception, retry after 5 seconds");
                        WaitHelper.waitForSeconds(5);
                        driver = new ScreenShotRemoteWebDriver(url, capability);
                    } else {
                        throw e;
                    }
                }

                break;

            default :
                driver = new ScreenShotRemoteWebDriver(url, capability);
        }

        setImplicitWaitTimeout(webDriverConfig.getImplicitWaitTimeout());
        if (webDriverConfig.getPageLoadTimeout() >= 0) {
            setPageLoadTimeout(webDriverConfig.getPageLoadTimeout(), webDriverConfig.getBrowser());
        }

        this.setWebDriver(driver);

        String hub = url.getHost();
        int port = url.getPort();

        // logging node ip address:
        try {
            HttpHost host = new HttpHost(hub, port);
            DefaultHttpClient client = new DefaultHttpClient();
            String sessionUrl = "http://" + hub + ":" + port + "/grid/api/testsession?session=";
            URL session = new URL(sessionUrl + ((RemoteWebDriver) driver).getSessionId());
            BasicHttpEntityEnclosingRequest req;
            req = new BasicHttpEntityEnclosingRequest("POST", session.toExternalForm());

            org.apache.http.HttpResponse response = client.execute(host, req);
            String responseContent = EntityUtils.toString(response.getEntity());
            try {
                JSONObject object = new JSONObject(responseContent);
                String proxyId = (String) object.get("proxyId");
                String node = (proxyId.split("//")[1].split(":")[0]);
                String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
                String version = ((RemoteWebDriver) driver).getCapabilities().getVersion();
                System.out.println("WebDriver is running on node " + node + ", " + browserName + version + ", session "
                        + ((RemoteWebDriver) driver).getSessionId());
                TestLogging.log("WebDriver is running on node " + node + ", " + browserName + version + ", session "
                        + ((RemoteWebDriver) driver).getSessionId());
            } catch (org.json.JSONException e) { }
        } catch (Exception ex) { }

        return driver;
    }

    protected void setPageLoadTimeout(final long timeout, final BrowserType type) {
        switch (type) {

            case Chrome :
                try {
                    driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
                } catch (UnsupportedCommandException e) {
                    e.printStackTrace();
                }

                break;

            case FireFox :
            case InternetExplore :
                driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
                break;

            default :
        }
    }
}
