package com.elastica.webpage;

import org.openqa.selenium.By;

import com.elastica.webelements.ButtonElement;
import com.elastica.webelements.HtmlElement;
import com.elastica.webelements.Table;
import com.elastica.webelements.TextFieldElement;
import com.elastica.uilocators.DriveLoginUilocators;

public interface DriveLogin {
	    public static final TextFieldElement oneDriveUserNameTextBox = new TextFieldElement("Login Text Box", By.name(DriveLoginUilocators.userNameOneDrive));
	    public static final TextFieldElement oneDrivePasswordTextBox = new TextFieldElement("Password Te  xt Box", By.name(DriveLoginUilocators.passwordOneDrive));
	    public static final ButtonElement 	oneDriveLoginButton = new ButtonElement("Login ButtonElement", By.id(DriveLoginUilocators.signInButtonOneDrive));
	    
	    public static final TextFieldElement boxUserNameTextBox = new TextFieldElement("Login Text Box", By.id(DriveLoginUilocators.userNameBox));
	    public static final TextFieldElement boxPasswordTextBox = new TextFieldElement("Password Te  xt Box", By.id(DriveLoginUilocators.passwordBox));
	    public  static final ButtonElement 	boxLoginButton = new ButtonElement("Login ButtonElement", By.id(DriveLoginUilocators.signInButtonBox));
	    
	    public static final TextFieldElement dropboxUserNameTextBox = new TextFieldElement("Login Text Box", By.cssSelector(DriveLoginUilocators.userNameDropBox));
	    public static final TextFieldElement dropboxPasswordTextBox = new TextFieldElement("Password Te  xt Box", By.cssSelector(DriveLoginUilocators.passwordDropBox));
	    public static final ButtonElement 	dropboxLoginButton = new ButtonElement("Login ButtonElement", By.cssSelector(DriveLoginUilocators.signInButtonDropBox));
	    
	    public static final TextFieldElement gDriveUserNameTextBox = new TextFieldElement("Login Text Box", By.id(DriveLoginUilocators.userNameGDrive));
	    public static final TextFieldElement gDrivePasswordTextBox = new TextFieldElement("Password Te  xt Box", By.id(DriveLoginUilocators.passwordGDrive));
	    public static final ButtonElement 	gDriveLoginButton = new ButtonElement("Login ButtonElement", By.id(DriveLoginUilocators.signInButtonGDrive));
	  
	   
	    
	    
	    
		
	    
	    
	    

}
