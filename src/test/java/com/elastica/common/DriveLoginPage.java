
package com.elastica.common;

import org.apache.log4j.Logger;

import com.elastica.actions.DropboxDriveAction;
import com.elastica.core.TestLogging;
import com.elastica.dataobject.User;
import com.elastica.webelements.PageObject;
import com.elastica.webpage.DriveLogin;



public class DriveLoginPage extends PageObject implements DriveLogin{
	private static final Logger logger = TestLogging.getLogger(DriveLoginPage.class);
	//DriveAction driveAction  = new DriveAction();
	
    public DriveLoginPage() throws Exception {
    }

    
    
    public DriveLoginPage(final String drivename) throws Exception {
    	super(DriveLoginHelper.getDriveUrl(drivename) , true);
    }

  
    public void loginToDrive(final User user) throws Exception {
    		Thread.sleep(5000);
    	switch (DriveLoginHelper.driveTypeThis){
    	case ONEDRIVE:
			   logger.info("One Drive Selected");
			   oneDriveUserNameTextBox.clear();
			   oneDriveUserNameTextBox.sendKeys(user.getUserID());
			   oneDrivePasswordTextBox.clear();
			   oneDrivePasswordTextBox.sendKeys(user.getPassword());
			   oneDriveLoginButton.click();
			   
			   break;
		case BOX:
		      logger.info("Box Drive Selected");
		      boxUserNameTextBox.clear();
			  boxUserNameTextBox.sendKeys(user.getUserID());
			  boxPasswordTextBox.clear();
			  boxPasswordTextBox.sendKeys(user.getPassword());
			  boxLoginButton.click();
		break;
				
		case DROPBOX:
		      logger.info("Drop box Drive Selected");
		      dropboxUserNameTextBox.clear();
			  dropboxUserNameTextBox.sendKeys(user.getUserID());
			  dropboxPasswordTextBox.clear();
			  dropboxPasswordTextBox.sendKeys(user.getPassword());
			  dropboxLoginButton.click();
		      Thread.sleep(8000);
		      logger.info("login Title: "+driver.getTitle());
		      driver.get("https://www.dropbox.com/logout");
		      logger.info("logout title: "+driver.getTitle());
			break;
			
		case GDRIVE:
		      logger.info("G Drive Selected");
		      gDriveUserNameTextBox.clear();
			  gDriveUserNameTextBox.sendKeys(user.getUserID());
			  gDrivePasswordTextBox.clear();
			  gDrivePasswordTextBox.sendKeys(user.getPassword());
			  gDriveLoginButton.click();
		break;
		
		}
    	
    }
    
}
