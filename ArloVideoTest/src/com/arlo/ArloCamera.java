package com.arlo;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ArloCamera {

	static String currentDir = System.getProperty("user.dir");
	WebDriver driver;
	@Test
	public void arlo() throws InterruptedException, IOException {

		

		System.setProperty("webdriver.chrome.driver", currentDir
				+ "\\jarFiles\\chromedriver_win32\\chromedriver.exe");

		String timeStamp2 = new SimpleDateFormat("MMddyyyy").format(Calendar.getInstance().getTime());
		//String downloadFilePath = "C:\\Users\\502000533\\Downloads\\Recorded-Videos\\" + timeStamp2+"\\";
		String downloadFilePath = "C:\\Users\\Mashekul\\Desktop\\ArloDailyDiveo\\" + timeStamp2+"";

		//Download files from browser,
		//Save in a specified folder on hard disk.

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, new HashMap<String, Object>());
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(cap);

		// Open Arlo

		driver.get("https://arlo.netgear.com/#/login");

		// Maximize browser

		driver.manage().window().maximize();

		driver.findElement(By.xpath(".//input[@id='userId']")).sendKeys("masheknyus@gmail.com");

		Thread.sleep(1000);
//		byte [] encode = Base64.encodeBase64(pass.getBytes());
//		System.out.println("String Before  " + pass);
//		System.out.println("String After   " + new String(encode));
		String encodePasswd ="UGhvdG9uQDEyMw==";
		driver.findElement(By.xpath(".//input[@id='password']")).sendKeys(decodeStr(encodePasswd));
		Thread.sleep(1000);

		driver.findElement(By.xpath(".//button[@id='loginButton']")).click();

		Thread.sleep(6000);

		// Click Library

		driver.findElement(By.xpath(".//div[@id='footer_library']/div")).click();

		Thread.sleep(6000);

		int videoCount = driver.findElements(By.xpath(".//div[@class='timeline-record']")).size();

		System.out.println("Number of video " + videoCount);

		Thread.sleep(1000);

		/*
		 * List<WebElement> videos =
		 * driver.findElements(By.xpath(".//div[@class='timeline-record']"));
		 * 
		 * for(WebElement abc :videos){ System.out.println(abc.getText()); }
		 */


		for (int i = 0; i < videoCount; i++) {

			driver.findElement(By.xpath(".//*[@id='day_record_" + i+ "']/div/div[1]/div[1]")).click();

			Thread.sleep(500);

			// driver.findElement(By.xpath(".//*[@id='day_record_0']/div/div[1]/div[1]")).click();

			driver.findElement(By.xpath("//div[@class='arlo-cp calendar-footer arlo-margin']/div/div[2]")).click();
			Thread.sleep(500);


			driver.findElement(By.xpath("//div[@class='arlo-cp calendar-footer arlo-margin']/div/div[5]")).click();
			Thread.sleep(1000);

		}

		Thread.sleep(5000);

		for (int i = videoCount-1; i >= 0; i--) {

			driver.findElement(By.xpath(".//*[@id='day_record_" + i+ "']/div/div[1]/div[1]")).click();

			Thread.sleep(500);

			driver.findElement(By.xpath("//div[@class='arlo-cp calendar-footer arlo-margin']/div/div[4]")).click();
			Thread.sleep(1000);

			//driver.findElement(By.xpath(".//*[@id='buttonCancel']")).click();
			driver.findElement(By.xpath(".//*[@id='buttonConfirm']")).click();
			Thread.sleep(500);
			if(driver.findElements(By.xpath(".//div[@class='modal-footer arlo-cp arlo-fs17']")).size()!=0){
				driver.findElement(By.xpath(".//div[@class='modal-footer arlo-cp arlo-fs17']")).click();
			}			
			Thread.sleep(500);

		}
		driver.close();
		driver.quit();
	}
public static String decodeStr(String encodedStr) {
		byte [] decoded = Base64.decodeBase64(encodedStr);
		return new String(decoded);
	}
	
}

