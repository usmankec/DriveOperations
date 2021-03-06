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

package com.elastica.tests;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import jxl.Sheet;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.elastica.actions.BoxDriveAction;
import com.elastica.actions.DriveLoginAction;
import com.elastica.core.Filter;
import com.elastica.core.SeleniumTestPlan;
import com.elastica.core.TestLogging;
import com.elastica.dataobject.User;
import com.elastica.listeners.Priority;
import com.elastica.util.SpreadSheetHelper;
import com.elastica.util.internal.entity.TestEntity;
import com.elastica.utils.ReadExcel;


public class BoxDriveOperations extends SeleniumTestPlan {
	private static final Logger logger = TestLogging.getLogger(BoxDriveOperations.class);
	DriveLoginAction driveLoginAction= new DriveLoginAction();
	
	/* @Priority(1)
	    @Test(dataProvider="loginData",groups = {"oneDrive"})
	    public void loginToDrive(final TestEntity testEntity, final User user) throws Exception {
	    	logger.info("Loging to One Drive Saas App");
	    	System.out.println(testEntity.getTestMethod());
	    	System.out.println(user.getUserID());
	    	driveLoginAction.driveLogin(user);
	    	//test();
	    	
	    	
	    }
    */
	 
	  @DataProvider(name = "loginData", parallel = false)
	    public static Iterator<Object[]> getUserInfo(final Method m, final ITestContext testContext) throws Exception {
	        Filter filter = Filter.equalsIgnoreCase(TestEntity.TEST_METHOD, m.getName());

	        System.out.println("Method: "+m.getName());
	        LinkedHashMap<String, Class<?>> classMap = new LinkedHashMap<String, Class<?>>();
	        classMap.put("TestEntity", TestEntity.class);
	        classMap.put("User", User.class);

	        return SpreadSheetHelper.getEntitiesFromSpreadsheet(OneDriveOperations.class, classMap, "loginuser.csv", 0, null,
	                filter);
	    }
	    
	
	    
	    @Priority(2)
	    @Test(groups = {"oneDrive"})
	    public void uploadFile(final Method m, final ITestContext testContext) throws Exception {
	    	logger.info("Uploading a file to Saas App");
	    	ReadExcel rexel= new ReadExcel();
	    	Sheet sheet=rexel.getSheet("D:\\testdata\\datafile.xls");
	    	int rowCount=rexel.getRowCount(sheet);
	    		    	
	    	BoxDriveAction boxDriveAction= new BoxDriveAction();
	    	driveLoginAction.driveLogin(user);
	    	Runtime run = Runtime.getRuntime();
	    	run.exec("D:\\testdata\\mousemove.exe");
	    	for (int i=1; i<rowCount; i++){
	    	boxDriveAction.clickUplaodLink();
	    	boxDriveAction.uploadFileAutoIT(rexel.readRowData(sheet,i).get("Filename")+"."+rexel.readRowData(sheet,i).get("Filetype"));
	    	Thread.sleep(8000);
	    	}
	    	
	    }
    
    /*@Priority(2)
    @Test(groups = {"oneDrive"})
    public void uploadFile() throws Exception {
    	logger.info("Uploading a file to Saas App");
    	BoxDriveAction boxDriveAction= new BoxDriveAction();
    	driveLoginAction.driveLogin(user);
    	
    	boxDriveAction.clickUplaodLink();
    	boxDriveAction.uploadFileAutoIT("Test.docx");
    	Thread.sleep(5000);
    	driveLoginAction.testPortalLogin(user);
    	Thread.sleep(5000);
    	driveLoginAction.reloadDrive();
     	Thread.sleep(5000);
     	boxDriveAction.clickUplaodLink();
    	boxDriveAction.uploadFileAutoIT("Test.docx");
    	driveLoginAction.reloadTestPortal();
    	Thread.sleep(5000);
    	driveLoginAction.reloadDrive();
    	
    }*/
    
  /* 
    @Priority(3)
    @Test(groups = {"oneDrive"})
    public void deleteFile() throws Exception {
    	logger.info("Deleting a file from saas App");
    	BoxDriveAction boxDriveAction= new BoxDriveAction();
    	driveLoginAction.driveLogin(user);
    	boxDriveAction.clickFileCheckbox(1);
    	boxDriveAction.clickManageLink();
    	boxDriveAction.clickDeleteLink();
    	Thread.sleep(5000);
    	
    }*/
     
   /* @Priority(4)
    @Test(groups = {"oneDrive"})
    public void createFolder() throws Exception {
    	logger.info("Create a folder");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFolderLink();
    	driveAction.typeInFolderNameTextbox("Folder2");
    	driveAction.clickCreateFolderButton();
    	Thread.sleep(5000);
    }
    
	
    
    @Priority(5)
    @Test(groups = {"oneDrive"})
    public void createTxtFile() throws Exception {
    	logger.info("Create text file");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFileLink("TXTFILE", "");
    	Thread.sleep(5000);
    	
    }
	
    @Priority(6)
    @Test(groups = {"oneDrive"})
    public void createWordFile() throws Exception {
    	logger.info("Create word file");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFileLink("WORDFILE", "");
    	Thread.sleep(5000);
    	
    }
    
    @Priority(7)
    @Test(groups = {"oneDrive"})
    public void createExlFile() throws Exception {
    	logger.info("Create Exl file");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFileLink("EXLFILE", "");
    	Thread.sleep(5000);
    	
    }
    
    @Priority(8)
    @Test(groups = {"oneDrive"})
    public void createPptFile() throws Exception {
    	logger.info("Create Ppt File");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFileLink("PPTFILE", "");
    	Thread.sleep(5000);
    	
    }
    
    @Priority(9)
    @Test(groups = {"oneDrive"})
    public void createOnenoteFile() throws Exception {
    	logger.info("Create one note file");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFileLink("ONENOTE", "");
    	Thread.sleep(20000);
    	
    }
    
    
    @Priority(10)
    @Test(groups = {"oneDrive"})
    public void createExlSurveyFile() throws Exception {
    	logger.info("Create exl Survey");
    	DriveAction driveAction= new DriveAction();
    	driveAction.driveLogin(user);
    	driveAction.clickCreateLink();
    	driveAction.clickCreateFileLink("EXLSURVEY", "");
    	Thread.sleep(5000);
    	
    }
    
   */
	
    
    
	/*
    
	@Priority(2)
    @Test(groups = {"oneDrive1"})
    public void loginAsValidUser2() throws Exception {
    	DriveAction da= new DriveAction();
    	User user= new User();
    	user.setUserID("elastictest@hotmail.com");
        user.setPassword("Test12!@");
    	da.driveLogin("onedrive", user);
    	da.getListFiles();
    	da.getFolderList();
    	da.getFileList();
    	da.clickFileCheckbox(0);
    	da.clickSherLink();
    	da.typeInEmailTo("usman.kec@gmail.com");
    	da.typeInQuickNote("This is for you");
    	da.clickShareButton();
    	Thread.sleep(10000);
    	da.clickCloseButton();
    	da.logout();
    	//da.clickUplaodLink();
    	//Thread.sleep(20000);
    }
    */
    
/*	
	
	
   // @Test(groups = {"oneDrive1"}, dataProvider = "loginData", description ="one Drive")
    public void loginAsValidUser(final TestEntity testEntity, final User user) throws Exception {
    	DriveAction da= new DriveAction();
    	
    	user.setUserID("elastictest@hotmail.com");
    //	user.setUserID("elastictest2");
    	user.setPassword("Test12!@");
    	
    	da.driveLogin("onedrive", user);
    	da.getListFiles();
    	da.getFolderList();
    	da.getFileList();
    	da.clickFileCheckbox(0);
    	da.clickSherLink();
    	da.typeInEmailTo("****@gmail.com");
    	da.typeInQuickNote("This is for you");
    	da.clickShareButton();
    	da.clickCloseButton();
    	//da.clickUplaodLink();
    	Thread.sleep(20000);
    	//da.getAllResourceListSize();
    	da.logout();
    	//driveLogin("box", user);
    	//driveLogin("dropbox", user);
    	
    	//user.setUserID("elastictest2");
        	//user.setPassword("Test12!@");
        //	driveLogin("gdrive", user);
        	
        	//getFileList();
    	//ap.verifyDocumentationDropDown();
    }*/
    
    
   /* public void driveLogin(String driveName, User user) throws Exception{
    	
    	DriveLoginPage tlp= new DriveLoginPage(driveName);
    	tlp.loginToDrive(user);
    	Thread.sleep(8000);
    	
    
    }
    
    
 public void getFilelist() throws Exception{
    
	 DriveAction da= new DriveAction();
	 da.getListFiles();
    	
    }
    */
    
    
   
  
}
