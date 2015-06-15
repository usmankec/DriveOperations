package com.elastica.actions;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.elastica.core.SeleniumTestsContextManager;
import com.elastica.core.TestLogging;
import com.elastica.dataobject.User;
import com.elastica.test.constants.DriveConstants;
import com.elastica.test.constants.DriveConstants.FileType;
import com.elastica.webelements.BasePage;
import com.elastica.webpage.DriveLogin;
import com.elastica.webpage.OneDrive;


public class GoogleDriveAction extends BasePage implements DriveLogin, OneDrive {
	private static final Logger logger = TestLogging.getLogger(GoogleDriveAction.class);
	public static FileType fileType;
	public static String driveName=null;
	
	
	/*public void driveLogin(User user) throws Exception{
		 driveName=SeleniumTestsContextManager.getThreadContext().getSaasApp();
		 logger.info("Saas App: "+driveName);
		 DriveLoginPage	driveLoginPage= new DriveLoginPage(driveName);
		 driveLoginPage.loginToDrive(user);
	    	Thread.sleep(8000);
	    }*/
	
	
	 public void clickUplaodLink(){
		 uploadLink.click();
		 System.out.println(uploadFileLink.isDisplayed());
		 System.out.println(uploadFileLink.isElementPresent());
		 uploadFileLink.click();
	 }
	 
	 public void clickManageLink(){
		 System.out.println(manageLink.isDisplayed());
		 System.out.println(manageLink.isElementPresent());
		 manageLink.click();
	 }
	 
	 public void clickCreateLink(){
		 System.out.println(createLink.isDisplayed());
		 System.out.println(createLink.isElementPresent());
		 createLink.click();
	 }
	 
	 
	 public void clickCreateFolderLink(){
		 System.out.println(createFolderLink.isDisplayed());
		 System.out.println(createFolderLink.isElementPresent());
		 createFolderLink.click();
	 }
	 
	 public void clickCreateFileLink(String fileTypeLocal, String content){
		 fileType=DriveConstants.FileType.valueOf(fileTypeLocal);
		 switch(fileType){
		 case TXTFILE:
			 System.out.println(createTextFileLink.isDisplayed());
			 createTextFileLink.click();
			 break;
		 case WORDFILE:
			 System.out.println(createWordFileLink.isDisplayed());
			 createWordFileLink.click();
			 break;
		 case PPTFILE:
			 System.out.println(createPptFileLink.isDisplayed());
			 createPptFileLink.click();
			 break;
		 case EXLFILE:
			 System.out.println(createExlFileLink.isDisplayed());
			 createExlFileLink.click();
			 break;
		 case ONENOTE:
			 System.out.println(createOnenoteFileLink.isDisplayed());
			 createOnenoteFileLink.click();
			 break;
		 case EXLSURVEY:
			 System.out.println(createExlSurveyFileLink.isDisplayed());
			 createExlSurveyFileLink.click();
			 break;
		 }
		
	 }
	 
	 
	 public void typeInNewlyCreatedFile(String content){
		 System.out.println(createTextFileLink.isDisplayed());
		 System.out.println(createTextFileLink.isElementPresent());
		 createTextFileLink.click();
	 }
	 
	 
	 
	 public  void typeInFolderNameTextbox(String folderName){
	    	System.out.println("email field: "+typeInFolderNameTextbox.isDisplayed());
	    	System.out.println("email field: "+typeInFolderNameTextbox.isDisplayed());
	    	typeInFolderNameTextbox.type(folderName);
	    	
	    }
	 
	 public void clickCreateFolderButton(){
		 System.out.println(createFolderButton.isDisplayed());
		 System.out.println(createFolderButton.isElementPresent());
		 createFolderButton.click();
	 }
	 
	 public void clickDeleteLink(){
		 System.out.println(delete.isDisplayed());
		 System.out.println(delete.isElementPresent());
		 delete.click();
	 }
	 
	 
	 
	 public void clickFolderUplaodLink(){
		 uploadLink.click();
		 System.out.println(uploadFileLink.isDisplayed());
		 System.out.println(uploadFileLink.isElementPresent());
		 //uploadFileLink.click();
	 }
	 
	
	 
	 public void clickFileCheckbox(int index){
	    	System.out.println("Checkbox: "+fileSelectionCheckboxList.isDisplayed());
	    	System.out.println("Checkbox: "+fileSelectionCheckboxList.getAllElements().get(index).isDisplayed());
	    	fileSelectionCheckboxList.getAllElements().get(index).click();
	    	
	    }
	 
	 public void clickSherLink(){
	    	System.out.println("share link: "+shareLink.isDisplayed());
	    	System.out.println("share link: "+shareLink.isDisplayed());
	    	shareLink.click();
	    	
	    }
	 
	 
	 public void clickCloseButton(){
	    	System.out.println("close button: "+closeButton.isDisplayed());
	    	System.out.println("close button: "+closeButton.isDisplayed());
	    	closeButton.click();
	    	
	    }
	 
	 
	 
	 public void clickShareButton(){
	    	System.out.println("share button: "+shareButton.isDisplayed());
	    	System.out.println("share button: "+shareButton.isDisplayed());
	    	shareButton.click();
	    	
	    }
	 
	 
	 public void typeInEmailTo(String email){
	    	System.out.println("email field: "+emailToShareTextBox.isDisplayed());
	    	System.out.println("email field: "+emailToShareTextBox.isDisplayed());
	    	emailToShareTextBox.type(email);
	    	
	    }
	 
	 public void typeInQuickNote(String msg){
	    	System.out.println("quick note: "+quickNoteTextBox.isDisplayed());
	    	System.out.println("quick note: "+quickNoteTextBox.isDisplayed());
	    	quickNoteTextBox.type(msg);
	    	
	    }
	 
	 
	 
	 public void getListFiles(){
	    	System.out.println("Row: "+fileFolder.getRowCount());
	    	
	    }
	 
	 public void getFolderList(){
		 System.out.println("Folder: "+folder.getAllElements().size()/2); 
	 }
	 
	 public int getFileList(){
		 int fileCount=file.getAllElements().size();
		 System.out.println("Files: "+fileCount); 
		 return fileCount;
	 }
	 
	 public void getAllResourceListSize(){
		 System.out.println("All resource list: "+(resourceList.getAllElements().size()-2)); 
	 }

	 public void logout(){
		 if (getDriver()!=null)
		 getDriver().get("https://login.live.com/logout.srf");
	 }
	 
	@Override
	protected void capturePageSnapshot() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public int uploadFileAutoIT(String fileName) {

			int countBefore = getFileList();
			fileUplaod(fileName);
			//driver.navigate().refresh();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			int countAfter = getFileList();
			return countAfter-countBefore;
			
	}
	
	
	public void fileUplaod(String fileName){
		
		try {
					File fileLocation   =	new File(DriveConstants.ASSIGNMENT_FILES_PATH);

					String absolutePath = 	fileLocation.getAbsolutePath() + "\\" + fileName;
					File exeLocation = null;
					
					//if ( instanceof ChromeDriver){
						 exeLocation	= new File(DriveConstants.AUTO_IT_EXE_PATH_CHROME_CMD);
					//}
					
					/*else if (getDriver() instanceof InternetExplorerDriver){
						 exeLocation	= new File(DriveConstants.AUTO_IT_EXE_PATH_IE);
					}
					
					else if (getDriver() instanceof SafariDriver){
						 exeLocation	= new File(DriveConstants.AUTO_IT_EXE_PATH_SAFARI);
						 System.out.println("exeLocation "+exeLocation);
					}
					else if (driver instanceof FirefoxDriver){
						 exeLocation	= new File(DriveConstants.AUTO_IT_EXE_PATH);
						 System.out.println("exeLocation "+exeLocation);
					}*/
					//File exeLocation	= new File(AssignmentsConstants.AUTO_IT_EXE_PATH);
					String exeAbsolutePath  =  exeLocation.getAbsolutePath().replace("\\", "\\\\");

					String args[]   = 	new String[2];
					args[0] 		= 	exeAbsolutePath;
					args[1]			=	absolutePath;
					System.out.println(args[0]);
					System.out.println(args[1]);
					Runtime run = Runtime.getRuntime();
					System.out.println("Before file executed");
					
						run.exec(args);
						Thread.sleep(20000);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("After file executed");
					
				
	}
			
	
}
