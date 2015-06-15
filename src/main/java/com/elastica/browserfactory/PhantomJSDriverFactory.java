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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.elastica.driver.DriverConfig;

public class PhantomJSDriverFactory extends AbstractWebDriverFactory implements IWebDriverFactory {
    private long timeout = 60;

    /**
     * @param  cfg  the configuration of the firefoxDriver
     */
    public PhantomJSDriverFactory(final DriverConfig cfg) {
        super(cfg);
    }

    /**
     * create native driver instance, designed for unit testing.
     *
     * @return
     */
    protected WebDriver createNativeDriver() {
    	
    	//File file = new File (System.getProperty("user.dir")+"\\src\\test\\resource\\phantomjs.exe");
    	//System.out.println("Absolute: "+file.getAbsolutePath());
    	File file = new File("C:\\Users\\musman\\Downloads\\phantomjs-2.0.0-windows\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		
    				//w PhantomJSDriver();
        return new PhantomJSDriver(new PhantomJSCapabilitiesFactory().createCapabilities(webDriverConfig));
    }

    @Override
    public WebDriver createWebDriver() throws IOException {
        DriverConfig cfg = this.getWebDriverConfig();

        driver = createNativeDriver();

        setImplicitWaitTimeout(cfg.getImplicitWaitTimeout());
        if (cfg.getPageLoadTimeout() >= 0) {
            setPageLoadTimeout(cfg.getPageLoadTimeout());
        }

        this.setWebDriver(driver);
        return driver;
    }

    protected void setPageLoadTimeout(final long timeout) {
        try {
            driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        } catch (UnsupportedCommandException e) {
            // chromedriver1 does not support pageLoadTimeout
        }
    }
}
