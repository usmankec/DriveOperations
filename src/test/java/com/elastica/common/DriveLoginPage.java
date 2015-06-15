
package com.elastica.common;

import com.elastica.dataobject.User;
import com.elastica.webelements.PageObject;
import com.elastica.webpage.DriveLogin;



public class DriveLoginPage extends PageObject implements DriveLogin{
	
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
			   System.out.println("One Drive Selected");
			   oneDriveUserNameTextBox.clear();
			   oneDriveUserNameTextBox.sendKeys(user.getUserID());
			   oneDrivePasswordTextBox.clear();
			   oneDrivePasswordTextBox.sendKeys(user.getPassword());
			   oneDriveLoginButton.click();
			   
			   break;
		case BOX:
		      System.out.println("Box Drive Selected");
		      boxUserNameTextBox.clear();
			  boxUserNameTextBox.sendKeys(user.getUserID());
			  boxPasswordTextBox.clear();
			  boxPasswordTextBox.sendKeys(user.getPassword());
			  boxLoginButton.click();
		break;
				
		case DROPBOX:
		      System.out.println("Drop box Drive Selected");
		      dropboxUserNameTextBox.clear();
			  dropboxUserNameTextBox.sendKeys(user.getUserID());
			  dropboxPasswordTextBox.clear();
			  dropboxPasswordTextBox.sendKeys(user.getPassword());
			  dropboxLoginButton.click();
		      Thread.sleep(8000);
		      System.out.println("login Title: "+driver.getTitle());
		      driver.get("https://www.dropbox.com/logout");
		      System.out.println("logout title: "+driver.getTitle());
			break;
			
		case GDRIVE:
		      System.out.println("G Drive Selected");
		      gDriveUserNameTextBox.clear();
			  gDriveUserNameTextBox.sendKeys(user.getUserID());
			  gDrivePasswordTextBox.clear();
			  gDrivePasswordTextBox.sendKeys(user.getPassword());
			  gDriveLoginButton.click();
		break;
		
		}
    	
    }
    
}
