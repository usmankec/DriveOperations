package com.elastica.common;

import com.elastica.test.constants.DriveConstants;



public class DriveLoginHelper {
	public static DriveType driveTypeThis;	
	public static String drivename="";
	
	public enum DriveType {
		ONEDRIVE, BOX, DROPBOX, GDRIVE
	}
	  
	 public static String getDriveUrl(String driveName){
		 	drivename=driveName;
	    	System.out.println("Login to Drive: "+driveName);
	    	driveTypeThis= DriveType.valueOf(driveName.toUpperCase());
			String driveUrl="";
			switch(driveTypeThis){
			case ONEDRIVE:
				driveUrl=DriveConstants.ONEDRIVE_PAGE_URL;
				break;
			case BOX:
				driveUrl=DriveConstants.BOX_PAGE_URL;
				break;
			case DROPBOX:
				driveUrl=DriveConstants.DROPBOX_PAGE_URL;
				break;
			case GDRIVE:
				driveUrl=DriveConstants.GDRIVE_PAGE_URL;
				break;
			}
			return driveUrl;
			
	    }
	    
	 public static DriveType driveType(){
	    	System.out.println("Login to Drive: "+drivename);
	    	driveTypeThis= DriveType.valueOf(drivename.toUpperCase());
			return driveTypeThis;
	
	 }
	
}
