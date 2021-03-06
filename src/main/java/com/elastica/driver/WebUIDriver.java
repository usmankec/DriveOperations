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

package com.elastica.driver;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.elastica.browserfactory.AndroidDriverFactory;
import com.elastica.browserfactory.ChromeDriverFactory;
import com.elastica.browserfactory.FirefoxDriverFactory;
import com.elastica.browserfactory.HtmlUnitDriverFactory;
import com.elastica.browserfactory.IEDriverFactory;
import com.elastica.browserfactory.IWebDriverFactory;
import com.elastica.browserfactory.OperaDriverFactory;
import com.elastica.browserfactory.PhantomJSDriverFactory;
import com.elastica.browserfactory.RemoteDriverFactory;
import com.elastica.browserfactory.SafariDriverFactory;
import com.elastica.core.SeleniumTestsContext;
import com.elastica.core.SeleniumTestsContextManager;

/**
 * This class provides factory to create webDriver session.
 */
public class WebUIDriver {
	private DriverConfig config =new DriverConfig();
	private WebDriver driver;
	private IWebDriverFactory webDriverBuilder;
    private static ThreadLocal<WebDriver> driverSession = new ThreadLocal<WebDriver>();
    private static ThreadLocal<WebUIDriver> uxDriverSession = new ThreadLocal<WebUIDriver>();
    private String node;

    public String getNode() {
        return node;
    }

    public void setNode(final String node) {
        this.node = node;
    }

    public static void cleanUp() {
        IWebDriverFactory b = getWebUXDriver().webDriverBuilder;
        if (b != null) {
            b.cleanUp();
        } else {
        	
            WebDriver driver = driverSession.get();
         //  WebUIDriver webUiDriver= uxDriverSession.get();
          // driver=webUiDriver.getWebDriver();
            if (driver != null) {
                try {
                    driver.quit();
                } catch (WebDriverException ex) {
                    ex.printStackTrace();
                }

                driver = null;
            }
        }

        driverSession.remove();
        uxDriverSession.remove();
    }

    /**
     * Get native WebDriver which can be converted to RemoteWebDriver.
     *
     * @return  webDriver
     */
    public static WebDriver getNativeWebDriver() {
        return ((CustomEventFiringWebDriver) getWebDriver(false)).getWebDriver();
    }

    /**
     * Get EventFiringWebDriver.
     *
     * @return  webDriver
     */
    public static WebDriver getWebDriver(String browser, String mode) {
        return getWebDriver(false, browser, mode);
    }
    
    public static WebDriver getWebDriver() {
        return getWebDriver(false);
    }

    public static WebDriver getWebDriver(final Boolean isCreate, final String browser, final String mode) {
        if (driverSession.get() == null && isCreate) {
            try {
                getWebUXDriver().createWebDriver(browser, mode);
            } catch (Exception e) {
                System.out.println("Capture customexception when create web driver");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return driverSession.get();
    }

    
    
    public static WebDriver getWebDriver(final Boolean isCreate) {
        if (driverSession.get() == null && isCreate) {
            try {
                getWebUXDriver().createWebDriver();
            } catch (Exception e) {
                System.out.println("Capture customexception when create web driver");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return driverSession.get();
    }

    public static WebUIDriver getWebUXDriver() {
        if (uxDriverSession.get() == null) {
            uxDriverSession.set(new WebUIDriver());
        }

        return uxDriverSession.get();
    }

    public static void setWebDriver(final WebDriver driver) {
        if (driver == null) {
            driverSession.remove();
        } else {
            if (getWebUXDriver() == null) {
                new WebUIDriver();
            }

            driverSession.set(driver);
        }
    }

  

    public WebUIDriver() {
        init();
        uxDriverSession.set(this);
    }

    public WebUIDriver(final String browser, final String mode) {
        init();
        this.setBrowser(browser);
        this.setMode(mode);
        uxDriverSession.set(this);
    }

    public WebDriver createRemoteWebDriver(final String browser, final String mode) throws Exception {
        WebDriver driver = null;
        config.setBrowser(BrowserType.getBrowserType(browser));
        config.setMode(DriverMode.valueOf(mode));


        if (config.getMode() == DriverMode.SauceLab) {
        	String platform=SeleniumTestsContextManager.getThreadContext().getPlatform();
        	String browserVersion=SeleniumTestsContextManager.getThreadContext().getWebBrowserVersion();
        	config.setBrowserVersion(browserVersion);
        	config.setPlatform(Platform.valueOf(platform));
            webDriverBuilder = new RemoteDriverFactory(this.config);
            
        } else  if (config.getMode() == DriverMode.ExistingGrid) {
            webDriverBuilder = new RemoteDriverFactory(this.config);
            
        } else {
            if (config.getBrowser() == BrowserType.FireFox) {
                webDriverBuilder = new FirefoxDriverFactory(this.config);
            } else if (config.getBrowser() == BrowserType.InternetExplore) {
                webDriverBuilder = new IEDriverFactory(this.config);
            } else if (config.getBrowser() == BrowserType.Chrome) {
            	System.out.println("Chrome driver");
                webDriverBuilder = new ChromeDriverFactory(this.config);
            } else if (config.getBrowser() == BrowserType.HtmlUnit) {
                webDriverBuilder = new HtmlUnitDriverFactory(this.config);
            } else if (config.getBrowser() == BrowserType.Safari) {
                webDriverBuilder = new SafariDriverFactory(this.config);
            } else if (config.getBrowser() == BrowserType.Android) {
                webDriverBuilder = new AndroidDriverFactory(this.config);
            } else if (config.getBrowser() == BrowserType.PhantomJS) {
                webDriverBuilder = new PhantomJSDriverFactory(this.config);
          
            } else if (config.getBrowser() == BrowserType.IPhone) {
            

                // webDriverBuilder = new IPhoneDriverFactory(this.config);
                webDriverBuilder = (IWebDriverFactory) Class.forName(
                                                                "com.elastica.browserfactory.IPhoneDriverFactory")
                                                            .getConstructor(DriverConfig.class).newInstance(
                                                                this.config);
            } else if (config.getBrowser() == BrowserType.IPad) {

                // webDriverBuilder = new IPadDriverFactory(this.config);
                webDriverBuilder = (IWebDriverFactory) Class.forName(
                                                                "com.elastica.browserfactory.IPadDriverFactory")
                                                            .getConstructor(DriverConfig.class).newInstance(
                                                                this.config);
            } else if (config.getBrowser() == BrowserType.Opera) {
                webDriverBuilder = new OperaDriverFactory(this.config);
            } else {
                throw new RuntimeException("Unsupported browser" + browser);
            }
        }

        synchronized (this.getClass()) {
            driver = webDriverBuilder.createWebDriver();
        }

        /*if (config.getBrowserWindowWidth() > 0 && config.getBrowserWindowHeight() > 0){
         *              new WebUtility(driver).resizeWindow(config.getBrowserWindowWidth(),
         * config.getBrowserWindowHeight());
         *      } else {
         *              new WebUtility(driver).maximizeWindow();
         *      }*/
        driver = handleListeners(driver);
        new WebUtility(driver).maximizeWindow();
        return driver;
    }

    protected WebDriver handleListeners(WebDriver driver) {

        // driver = new EventFiringWebDriver(driver).register(new
        // DriverExceptionListener());
        // Support customized listeners
        ArrayList<WebDriverEventListener> listeners = config.getWebDriverListeners();
        if (listeners != null && listeners.size() > 0) {
            for (int i = 0; i < config.getWebDriverListeners().size(); i++) {
                driver = new CustomEventFiringWebDriver(driver).register(listeners.get(i));
            }
        }

        return driver;
    }

    public WebDriver createWebDriver(final String browser, final String mode) throws Exception {
        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Start creating web driver instance: "
                + this.getBrowser());
        System.out.println("Browser type: "+config.getBrowser().getBrowserType());
        System.out.println("Mode: "+config.getMode().name());
       
        System.out.println("browser: "+browser);
        System.out.println("mode: "+mode);
        
        //driver = createRemoteWebDriver(config.getBrowser().getBrowserType(), config.getMode().name());
        
        driver = createRemoteWebDriver(browser,mode);

        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Finish creating web driver instance: "
                + this.getBrowser());

        driverSession.set(driver);
        return driver;
    }
    
    
    public WebDriver createWebDriver() throws Exception {
        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Start creating web driver instance: "
                + this.getBrowser());
        System.out.println("Browser type: "+config.getBrowser().getBrowserType());
        System.out.println("Mode: "+config.getMode().name());
       
        driver = createRemoteWebDriver(config.getBrowser().getBrowserType(), config.getMode().name());

        System.out.println(Thread.currentThread() + ":" + new Date() + ":::Finish creating web driver instance: "
                + this.getBrowser());

        driverSession.set(driver);
        return driver;
    }

    public String getBrowser() {
        return config.getBrowser().getBrowserType();
    }

    public String getPlatform() {
        return config.getPlatform().name();
    }

    public String getBrowserVersion() {
        return config.getBrowserVersion();
    }

    public String getChromeBinPath() {
        return config.getChromeBinPath();
    }

    public String getChromeDriverPath() {
        return config.getChromeDriverPath();
    }

    public DriverConfig getConfig() {
        return config;
    }

    public int getExplicitWait() {
        return config.getExplicitWaitTimeout();
    }

    public String getFfBinPath() {
        return config.getFirefoxBinPath();
    }

    public String getFfProfilePath() throws URISyntaxException {
        return config.getFirefoxProfilePath();
    }

    public String getOperaProfilePath() throws URISyntaxException {
        return config.getOperaProfilePath();
    }

    public void setOperaProfilePath(final String operaProfilePath) {
        config.setOperaProfilePath(operaProfilePath);
    }

    public String getHubUrl() {
        return config.getHubUrl();
    }

    public String getIEDriverPath() {
        return config.getIeDriverPath();
    }

    public double getImplicitWait() {
        return config.getImplicitWaitTimeout();
    }

    public String getMode() {
        return config.getMode().name();
    }

    public String getOutputDirectory() {
        return config.getOutputDirectory();
    }

    public String getNtlmAuthTrustedUris() {
        return config.getNtlmAuthTrustedUris();
    }

    public void setNtlmAuthTrustedUris(final String url) {
        config.setNtlmAuthTrustedUris(url);
    }

    public int getPageLoadTimeout() {
        return config.getPageLoadTimeout();
    }

    public String getProxyHost() {
        return config.getProxyHost();
    }

    public void setUserAgentOverride(final String userAgentOverride) {
        config.setUserAgentOverride(userAgentOverride);
    }

    public String getUserAgentOverride() {
        return config.getUserAgentOverride();
    }

    public IWebDriverFactory getWebDriverBuilder() {
        return webDriverBuilder;
    }

    public int getWebSessionTimeout() {
        return config.getWebSessionTimeout();
    }

    private void init() {
        if (SeleniumTestsContextManager.getThreadContext() == null) {
            return;
        }

        String browser = SeleniumTestsContextManager.getThreadContext().getWebRunBrowser();
        config.setBrowser(BrowserType.getBrowserType(getBrowser()));

        String mode = SeleniumTestsContextManager.getThreadContext().getWebRunMode();
        config.setMode(DriverMode.valueOf(mode));

        String hubUrl = SeleniumTestsContextManager.getThreadContext().getWebDriverGrid();
        config.setHubUrl(hubUrl);

        String ffProfilePath = SeleniumTestsContextManager.getThreadContext().getFirefoxUserProfilePath();
        config.setFfProfilePath(ffProfilePath);

        String operaProfilePath = SeleniumTestsContextManager.getThreadContext().getOperaUserProfilePath();
        config.setOperaProfilePath(operaProfilePath);

        String ffBinPath = SeleniumTestsContextManager.getThreadContext().getFirefoxBinPath();
        config.setFfBinPath(ffBinPath);

        String chromeBinPath = SeleniumTestsContextManager.getThreadContext().getChromeBinPath();
        config.setChromeBinPath(chromeBinPath);

        String chromeDriverPath = SeleniumTestsContextManager.getThreadContext().getChromeDriverPath();
        config.setChromeDriverPath(chromeDriverPath);

        String ieDriverPath = SeleniumTestsContextManager.getThreadContext().getIEDriverPath();
        config.setIeDriverPath(ieDriverPath);

        int webSessionTimeout = SeleniumTestsContextManager.getThreadContext().getWebSessionTimeout();
        config.setWebSessionTimeout(webSessionTimeout);

        double implicitWaitTimeout = SeleniumTestsContextManager.getThreadContext().getImplicitWaitTimeout();
        config.setImplicitWaitTimeout(implicitWaitTimeout);

        int explicitWaitTimeout = SeleniumTestsContextManager.getThreadContext().getExplicitWaitTimeout();
        config.setExplicitWaitTimeout(explicitWaitTimeout);
        config.setPageLoadTimeout(SeleniumTestsContextManager.getThreadContext().getPageLoadTimeout());

        String outputDirectory = SeleniumTestsContextManager.getGlobalContext().getOutputDirectory();
        config.setOutputDirectory(outputDirectory);

        if (SeleniumTestsContextManager.getThreadContext().isWebProxyEnabled()) {
            String proxyHost = SeleniumTestsContextManager.getThreadContext().getWebProxyAddress();
            config.setProxyHost(proxyHost);
        }

        String browserVersion = SeleniumTestsContextManager.getThreadContext().getWebBrowserVersion();
        config.setBrowserVersion(browserVersion);

        String platform = SeleniumTestsContextManager.getThreadContext().getPlatform();
        if (platform != null) {
            config.setPlatform(Platform.valueOf(platform));
        }

        if ("false".equalsIgnoreCase(
                    (String) SeleniumTestsContextManager.getThreadContext().getAttribute(
                        SeleniumTestsContext.Set_Assume_Untrusted_Certificate_Issuer))) {
            config.setSetAssumeUntrustedCertificateIssuer(false);
        }

        if ("false".equalsIgnoreCase(
                    (String) SeleniumTestsContextManager.getThreadContext().getAttribute(
                        SeleniumTestsContext.Set_Accept_Untrusted_Certificates))) {
            config.setSetAcceptUntrustedCertificates(false);
        }

        if ("false".equalsIgnoreCase(
                    (String) SeleniumTestsContextManager.getThreadContext().getAttribute(
                        SeleniumTestsContext.ENABLE_JAVASCRIPT))) {
            config.setEnableJavascript(false);
        }

        if (SeleniumTestsContextManager.getThreadContext().getNtlmAuthTrustedUris() != null) {
            config.setNtlmAuthTrustedUris(SeleniumTestsContextManager.getThreadContext().getNtlmAuthTrustedUris());
        }

        if (SeleniumTestsContextManager.getThreadContext().getBrowserDownloadDir() != null) {
            config.setBrowserDownloadDir(SeleniumTestsContextManager.getThreadContext().getBrowserDownloadDir());
        }

        if (SeleniumTestsContextManager.getThreadContext().getAddJSErrorCollectorExtension() != null) {
            config.setAddJSErrorCollectorExtension(Boolean.parseBoolean(
                    SeleniumTestsContextManager.getThreadContext().getAddJSErrorCollectorExtension()));
        }

        String ua = null;
        if (SeleniumTestsContextManager.getThreadContext().getUserAgent() != null) {
            ua = SeleniumTestsContextManager.getThreadContext().getUserAgent();
        } else {
            ua = null;
        }

        config.setUserAgentOverride(ua);

        String listeners = SeleniumTestsContextManager.getThreadContext().getWebDriverListener();
        if (SeleniumTestsContextManager.getThreadContext().getEnableExceptionListener()) {
            if (listeners != null) {
                listeners = listeners + ",";
            } else {
                listeners = "";
            }

            listeners = listeners + DriverExceptionListener.class.getName();
        }

        if (listeners != null && listeners != "") {
            config.setWebDriverListeners(listeners);
        } else {
            config.setWebDriverListeners("");
        }

        config.setUseFirefoxDefaultProfile(SeleniumTestsContextManager.getThreadContext().isUseFirefoxDefaultProfile());

        String size = SeleniumTestsContextManager.getThreadContext().getBrowserWindowSize();
        if (size != null) {
            int width = -1;
            int height = -1;
            try {
                width = Integer.parseInt(size.split(",")[0].trim());
                height = Integer.parseInt(size.split(",")[1].trim());
            } catch (Exception ex) { }

            config.setBrowserWindowWidth(width);
            config.setBrowserWindowHeight(height);
        }
    }

    public static void main(final String[] args) {
        System.out.println(DriverExceptionListener.class.getName());
    }

    public boolean isSetAcceptUntrustedCertificates() {
        return config.isSetAcceptUntrustedCertificates();
    }

    public boolean isAddJSErrorCollectorExtension() {
        return config.isAddJSErrorCollectorExtension();
    }

    public void setAddJSErrorCollectorExtension(final Boolean addJSErrorCollectorExtension) {
        config.setAddJSErrorCollectorExtension(addJSErrorCollectorExtension);
    }

    public boolean isSetAssumeUntrustedCertificateIssuer() {
        return config.isSetAssumeUntrustedCertificateIssuer();
    }

    public boolean isEnableJavascript() {
        return config.isEnableJavascript();
    }

    public void setEnableJavascript(final Boolean enableJavascript) {
        config.setEnableJavascript(enableJavascript);
    }

    public void setBrowser(final String browser) {
        config.setBrowser(BrowserType.getBrowserType(browser));

    }

    public void setBrowserVersion(final String browserVersion) {
        config.setBrowserVersion(browserVersion);
    }

    public void setPlatform(final String platform) {
        config.setPlatform(Platform.valueOf(platform));
    }

    public void setChromeBinPath(final String chromeBinPath) {
        config.setChromeBinPath(chromeBinPath);
    }

    public void setBrowserDownloadDir(final String browserDownloadDir) {
        config.setBrowserDownloadDir(browserDownloadDir);
    }

    public String getBrowserDownloadDir() {
        return config.getBrowserDownloadDir();
    }

    public void setChromeDriverPath(final String chromeDriverPath) {
        config.setChromeDriverPath(chromeDriverPath);
    }

    public void setConfig(final DriverConfig config) {
        this.config = config;
    }

    public void setExplicitTimeout(final int explicitWaitTimeout) {
        config.setExplicitWaitTimeout(explicitWaitTimeout);
    }

    public void setFfBinPath(final String ffBinPath) {
        config.setFfBinPath(ffBinPath);
    }

    public void setFfProfilePath(final String ffProfilePath) {
        config.setFfProfilePath(ffProfilePath);
    }

    public void setHubUrl(final String hubUrl) {
        config.setHubUrl(hubUrl);
    }

    public void setIEDriverPath(final String ieDriverPath) {
        config.setIeDriverPath(ieDriverPath);
    }

    public void setImplicitlyWaitTimeout(final double implicitTimeout) {
        config.setImplicitWaitTimeout(implicitTimeout);
    }

    public void setMode(final String mode) {
        config.setMode(DriverMode.valueOf(mode));
    }

    public void setOutputDirectory(final String outputDirectory) {
        config.setOutputDirectory(outputDirectory);
    }

    public void setPageLoadTimeout(final int pageLoadTimeout) {
        config.setPageLoadTimeout(pageLoadTimeout);
    }

    public void setProxyHost(final String proxyHost) {
        config.setProxyHost(proxyHost);
    }

    public void setSetAcceptUntrustedCertificates(final boolean setAcceptUntrustedCertificates) {
        config.setSetAcceptUntrustedCertificates(setAcceptUntrustedCertificates);
    }

    public void setSetAssumeUntrustedCertificateIssuer(final boolean setAssumeUntrustedCertificateIssuer) {
        config.setSetAssumeUntrustedCertificateIssuer(setAssumeUntrustedCertificateIssuer);
    }

    public void setWebDriverBuilder(final IWebDriverFactory builder) {
        this.webDriverBuilder = builder;
    }

    public void setWebSessionTimeout(final int webSessionTimeout) {
        config.setWebSessionTimeout(webSessionTimeout);
    }

}
