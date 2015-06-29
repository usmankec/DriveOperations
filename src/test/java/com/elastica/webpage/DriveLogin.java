package com.elastica.webpage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	    
	    
	    
	    public static final TextFieldElement 	portalEmail = new TextFieldElement("Login ButtonElement", By.name("email"));
	    public static final TextFieldElement 	portalPassword = new TextFieldElement("Login ButtonElement", By.name("password"));
	    public static final ButtonElement 	portalLogin = new ButtonElement("Login ButtonElement", By.cssSelector(".btn.btn-default.btn-lg.btn-block.ng-scope"));
	    
	    public static final HtmlElement	chart1 = new HtmlElement("Login ButtonElement", By.cssSelector(".widget-list-container ul:nth-child(1) li:nth-child(1) div div:nth-child(2) .widget-chart .e-venn-diagram svg g circle"));
	    
	    public static final HtmlElement	chart2 = new HtmlElement("Login ButtonElement", By.cssSelector(".widget-list-container ul:nth-child(1) li:nth-child(2) div div:nth-child(2) .widget-chart .e-venn-diagram svg g circle"));
	    
	    public static final HtmlElement	chart3 = new HtmlElement("Login ButtonElement", By.cssSelector(".widget-list-container ul:nth-child(1) li:nth-child(3) .e-widget-policy-alerts .widget-chart svg .bar"));
	    
	    
	    
	    
	    
	    		

}
