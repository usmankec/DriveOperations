
package com.elastica.common;


import java.net.URL;

import org.apache.log4j.Logger;

import com.elastica.core.TestLogging;
import com.elastica.dataobject.User;
import com.elastica.webelements.PageObject;
import com.elastica.webpage.DriveLogin;



public class DriveLoginPage extends PageObject implements DriveLogin{
	private static final Logger logger = TestLogging.getLogger(DriveLoginPage.class);
	//DriveAction driveAction  = new DriveAction();
	String portalurl="usser login";
    public DriveLoginPage() throws Exception {
    	super("********", true);
    }
    
    public DriveLoginPage(URL url) throws Exception {
    	super(url.toString(), true);
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
    
    
    public void loginToPortal(final User user) throws Exception {
	
    	portalEmail.sendKeys("user name");
    	portalPassword.sendKeys("*******");
  	  	portalLogin.click();
  	  	Thread.sleep(10000);
      System.out.println("Size 1: "+chart1.getAllElements().size());
      System.out.println(chart1.getAllElements().get(0).getTagName());
      System.out.println(chart1.getAllElements().get(0).isDisplayed());
      System.out.println(chart1.getAllElements().get(0).getAttribute("tooltip-title"));
      chart1.getAllElements().get(0).click();
      System.out.println(chart1.getAllElements().get(0).getAttribute("tooltip-title"));
      System.out.println(chart1.getAllElements().get(1).getAttribute("tooltip-title"));
      System.out.println(chart1.getAllElements().get(2).getAttribute("tooltip-title"));
      System.out.println(chart1.getAllElements().get(3).getAttribute("tooltip-title"));
      
      System.out.println("Size 2: "+chart2.getAllElements().size());
      System.out.println(chart2.getAllElements().get(0).getTagName());
      System.out.println(chart2.getAllElements().get(0).isDisplayed());
      System.out.println(chart2.getAllElements().get(0).getAttribute("tooltip-title"));
      System.out.println(chart2.getAllElements().get(0).getAttribute("tooltip-title"));
      System.out.println(chart2.getAllElements().get(1).getAttribute("tooltip-title"));
      System.out.println(chart2.getAllElements().get(2).getAttribute("tooltip-title"));
      
      System.out.println("Size 3: "+chart3.getAllElements().size());
      System.out.println(chart3.getAllElements().get(0).getTagName());
      System.out.println(chart3.getAllElements().get(0).isDisplayed());
      System.out.println(chart3.getAllElements().get(0).getAttribute("tooltip-title"));
      System.out.println(chart3.getAllElements().get(0).getAttribute("tooltip-title"));
      System.out.println(chart3.getAllElements().get(1).getAttribute("tooltip-title"));
      System.out.println(chart3.getAllElements().get(2).getAttribute("tooltip-title"));
    }
    
    
   /* public  void loadDrive(String driveName) throws Exception {
    	getDriver().get(DriveLoginHelper.getDriveUrl(driveName));
    }
    
    public  void loadTestPortal() throws Exception {
    	getDriver().get(portalurl);
    }*/

    
   
}
