package com.elastica.webpage;

import org.openqa.selenium.By;

import com.elastica.uilocators.BoxDriveUilocators;
import com.elastica.webelements.HtmlElement;
import com.elastica.webelements.LinkElement;
import com.elastica.webelements.Table;

public interface BoxDrive {
	
		public static final LinkElement uploadLink = new LinkElement("Login Text Box", By.cssSelector(BoxDriveUilocators.uploadLink));
	    public static final HtmlElement uploadFileLink = new HtmlElement("Login Text Box", By.id(BoxDriveUilocators.uploadFileLink));
	    public static final LinkElement uploadButtonPopup = new LinkElement("Login Text Box", By.cssSelector(BoxDriveUilocators.uploadButtonPopup));
	    
	    
	    /*public static final HtmlElement fileSelectionCheckboxList = new HtmlElement("Login Text Box", By.cssSelector(OneDriveUilocators.fileSelectionCheckboxList));
	    public static final LinkElement shareLink = new LinkElement("Login Text Box", By.linkText(OneDriveUilocators.shareLink));
	    public static final TextFieldElement emailToShareTextBox = new TextFieldElement("Login Text Box", By.cssSelector(OneDriveUilocators.emailToShareTextBox));
	    public static final TextFieldElement quickNoteTextBox = new TextFieldElement("Login Text Box", By.cssSelector(OneDriveUilocators.quickNoteTextBox));
	    public static final ButtonElement shareButton = new ButtonElement("Login Text Box", By.cssSelector(OneDriveUilocators.shareButton));
	    public static final ButtonElement closeButton = new ButtonElement("Login Text Box", By.cssSelector(OneDriveUilocators.closeButton));
	    public static final LinkElement manageLink = new LinkElement("Login Text Box", By.linkText(OneDriveUilocators.manageLink));
	    public static final LinkElement delete = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.delete));
	    public static final LinkElement createLink = new LinkElement("Login Text Box", By.linkText(OneDriveUilocators.createLink));
	    public static final LinkElement createFolderLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createFolderLink));
	    public static final TextFieldElement typeInFolderNameTextbox = new TextFieldElement("Login Text Box", By.cssSelector(OneDriveUilocators.typeInFolderNameTextbox));
	    public static final ButtonElement createFolderButton = new ButtonElement("Login Text Box", By.cssSelector(OneDriveUilocators.createFolderButton));
	    public static final LinkElement createTextFileLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createTextFileLink));
	    public static final LinkElement createWordFileLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createWordFileLink));
	    public static final LinkElement createExlFileLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createExlFileLink));
	    public static final LinkElement createPptFileLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createPptFileLink));
	    public static final LinkElement createOnenoteFileLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createOnenoteFileLink));
	    public static final LinkElement createExlSurveyFileLink = new LinkElement("Login Text Box", By.cssSelector(OneDriveUilocators.createExlSurveyFileLink));*/
	    
	    public static final Table fileFolder= new Table("This is Table locator", By.cssSelector(".centerColumn .fillTable>table>tbody"));
	    
	    public static final HtmlElement file= new HtmlElement("This is Table locator", By.cssSelector("#files #mod-item-list .list_view .file"));
	    
	    public static final HtmlElement folder= new HtmlElement("This is Table locator", By.cssSelector(".centerColumn .fillTable>table>tbody .folder"));
	    
	    
	    public static final HtmlElement resourceList= new HtmlElement("This is Table locator", By.cssSelector(".centerColumn .fillTable>table>tbody .c-ListView .child"));
	    
	    
	    
	   
}
