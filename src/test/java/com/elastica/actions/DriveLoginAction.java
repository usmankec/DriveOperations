package com.elastica.actions;

import org.apache.log4j.Logger;

import com.elastica.common.DriveLoginHelper;
import com.elastica.common.DriveLoginPage;
import com.elastica.core.SeleniumTestsContextManager;
import com.elastica.core.TestLogging;
import com.elastica.dataobject.User;
import com.elastica.test.constants.DriveConstants.FileType;


public class DriveLoginAction {
	private static final Logger logger = TestLogging.getLogger(DriveLoginAction.class);
	public static FileType fileType;
	public static String driveName=null;
	
	public void driveLogin(User user) throws Exception{
		 driveName=SeleniumTestsContextManager.getThreadContext().getSaasApp();
		 logger.info("Saas App: "+driveName);
		 DriveLoginPage	driveLoginPage= new DriveLoginPage(driveName);
		 driveLoginPage.loginToDrive(user);
	    	Thread.sleep(8000);
	    }
	
	public void reloadDrive() throws Exception{
		 driveName=SeleniumTestsContextManager.getThreadContext().getSaasApp();
		 logger.info("Saas App: "+driveName);
		 new DriveLoginPage(driveName);
	    	Thread.sleep(8000);
	    }
	
	public void testPortalLogin(User user) throws Exception{
		 logger.info("Login to portal");
		 DriveLoginPage	portalLoginPage= new DriveLoginPage();
		 portalLoginPage.loginToPortal(user);
	    	Thread.sleep(8000);
	    }
	
	public void reloadTestPortal() throws Exception{
		logger.info("Login to portal");
		new DriveLoginPage();
		 Thread.sleep(8000);
	    }
	
	
}
