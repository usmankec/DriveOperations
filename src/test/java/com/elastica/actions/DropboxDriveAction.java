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


public class DropboxDriveAction extends BasePage implements OneDrive {
	private static final Logger logger = TestLogging.getLogger(DropboxDriveAction.class);
	public static FileType fileType;
	public static String driveName=null;

	
	 public void clickUplaodLink(){
		 uploadLink.click();
		 logger.info(uploadFileLink.isDisplayed());
		 logger.info(uploadFileLink.isElementPresent());
		 uploadFileLink.click();
	 }
	 
	 public void clickManageLink(){
		 logger.info(manageLink.isDisplayed());
		 logger.info(manageLink.isElementPresent());
		 manageLink.click();
	 }
	 
	 public void clickCreateLink(){
		 logger.info(createLink.isDisplayed());
		 logger.info(createLink.isElementPresent());
		 createLink.click();
	 }
	 
	 
	 public void clickCreateFolderLink(){
		 logger.info(createFolderLink.isDisplayed());
		 logger.info(createFolderLink.isElementPresent());
		 createFolderLink.click();
	 }
	 
	 public void clickCreateFileLink(String fileTypeLocal, String content){
		 fileType=DriveConstants.FileType.valueOf(fileTypeLocal);
		 switch(fileType){
		 case TXTFILE:
			 logger.info(createTextFileLink.isDisplayed());
			 createTextFileLink.click();
			 break;
		 case WORDFILE:
			 logger.info(createWordFileLink.isDisplayed());
			 createWordFileLink.click();
			 break;
		 case PPTFILE:
			 logger.info(createPptFileLink.isDisplayed());
			 createPptFileLink.click();
			 break;
		 case EXLFILE:
			 logger.info(createExlFileLink.isDisplayed());
			 createExlFileLink.click();
			 break;
		 case ONENOTE:
			 logger.info(createOnenoteFileLink.isDisplayed());
			 createOnenoteFileLink.click();
			 break;
		 case EXLSURVEY:
			 logger.info(createExlSurveyFileLink.isDisplayed());
			 createExlSurveyFileLink.click();
			 break;
		 }
		
	 }
	 
	 
	 public void typeInNewlyCreatedFile(String content){
		 logger.info(createTextFileLink.isDisplayed());
		 logger.info(createTextFileLink.isElementPresent());
		 createTextFileLink.click();
	 }
	 
	 
	 
	 public  void typeInFolderNameTextbox(String folderName){
	    	logger.info("email field: "+typeInFolderNameTextbox.isDisplayed());
	    	logger.info("email field: "+typeInFolderNameTextbox.isDisplayed());
	    	typeInFolderNameTextbox.type(folderName);
	    	
	    }
	 
	 public void clickCreateFolderButton(){
		 logger.info(createFolderButton.isDisplayed());
		 logger.info(createFolderButton.isElementPresent());
		 createFolderButton.click();
	 }
	 
	 public void clickDeleteLink(){
		 logger.info(delete.isDisplayed());
		 logger.info(delete.isElementPresent());
		 delete.click();
	 }
	 
	 
	 
	 public void clickFolderUplaodLink(){
		 uploadLink.click();
		 logger.info(uploadFileLink.isDisplayed());
		 logger.info(uploadFileLink.isElementPresent());
		 //uploadFileLink.click();
	 }
	 
	
	 
	 public void clickFileCheckbox(int index){
	    	logger.info("Checkbox: "+fileSelectionCheckboxList.isDisplayed());
	    	logger.info("Checkbox: "+fileSelectionCheckboxList.getAllElements().get(index).isDisplayed());
	    	fileSelectionCheckboxList.getAllElements().get(index).click();
	    	
	    }
	 
	 public void clickSherLink(){
	    	logger.info("share link: "+shareLink.isDisplayed());
	    	logger.info("share link: "+shareLink.isDisplayed());
	    	shareLink.click();
	    	
	    }
	 
	 
	 public void clickCloseButton(){
	    	logger.info("close button: "+closeButton.isDisplayed());
	    	logger.info("close button: "+closeButton.isDisplayed());
	    	closeButton.click();
	    	
	    }
	 
	 
	 
	 public void clickShareButton(){
	    	logger.info("share button: "+shareButton.isDisplayed());
	    	logger.info("share button: "+shareButton.isDisplayed());
	    	shareButton.click();
	    	
	    }
	 
	 
	 public void typeInEmailTo(String email){
	    	logger.info("email field: "+emailToShareTextBox.isDisplayed());
	    	logger.info("email field: "+emailToShareTextBox.isDisplayed());
	    	emailToShareTextBox.type(email);
	    	
	    }
	 
	 public void typeInQuickNote(String msg){
	    	logger.info("quick note: "+quickNoteTextBox.isDisplayed());
	    	logger.info("quick note: "+quickNoteTextBox.isDisplayed());
	    	quickNoteTextBox.type(msg);
	    	
	    }
	 
	 
	 
	 public void getListFiles(){
	    	logger.info("Row: "+fileFolder.getRowCount());
	    	
	    }
	 
	 public void getFolderList(){
		 logger.info("Folder: "+folder.getAllElements().size()/2); 
	 }
	 
	 public int getFileList(){
		 int fileCount=file.getAllElements().size();
		 logger.info("Files: "+fileCount); 
		 return fileCount;
	 }
	 
	 public void getAllResourceListSize(){
		 logger.info("All resource list: "+(resourceList.getAllElements().size()-2)); 
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
						 logger.info("exeLocation "+exeLocation);
					}
					else if (driver instanceof FirefoxDriver){
						 exeLocation	= new File(DriveConstants.AUTO_IT_EXE_PATH);
						 logger.info("exeLocation "+exeLocation);
					}*/
					//File exeLocation	= new File(AssignmentsConstants.AUTO_IT_EXE_PATH);
					String exeAbsolutePath  =  exeLocation.getAbsolutePath().replace("\\", "\\\\");

					String args[]   = 	new String[2];
					args[0] 		= 	exeAbsolutePath;
					args[1]			=	absolutePath;
					logger.info(args[0]);
					logger.info(args[1]);
					Runtime run = Runtime.getRuntime();
					logger.info("Before file executed");
					
						run.exec(args);
						Thread.sleep(20000);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logger.info("After file executed");
					
				
	}
			
	
}
