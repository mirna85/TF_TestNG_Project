package variousConcepts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandle {

	WebDriver driver;

	@Before
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://www.yahoo.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void windowHandle() {

		String Handle1 = driver.getWindowHandle();
		System.out.println("Handle 1: " + Handle1);
		System.out.println("1. " + driver.getTitle());

		driver.findElement(By.xpath("//input[@id='ybar-sbq']")).sendKeys("xpath");
		driver.findElement(By.xpath("//input[@id='ybar-search']")).click();

		// driver.findElement(By.xpath("//a[text()= 'XPath Tutorial -
		// W3Schools']")).click();
		driver.findElement(By.linkText("XPath Tutorial - W3Schools")).click();

		//Switches driver to W3 School
		System.out.println("2. " + driver.getTitle());
		Set<String> Handles = driver.getWindowHandles();
		System.out.println("Set: " + Handles);

		for (String i : Handles) {
			System.out.println(i);
			driver.switchTo().window(i);
		}

		System.out.println("3. " + driver.getTitle());

		driver.findElement(By.linkText("SQL Tutorial")).click(); // this does not open a new window

		// driver goes back to Yahoo.com
		driver.switchTo().window(Handle1); 
		System.out.println("4. " + driver.getTitle());

		driver.findElement(By.xpath(" //a[text()= 'XPath - Quick Guide - Tutorialspoint']")).click();

		//Switches driver to Tutorialspoint
		Set<String> MoreHandles = driver.getWindowHandles();
		System.out.println("Set: " + MoreHandles);

		for (String i : MoreHandles) {
			System.out.println(i);
			driver.switchTo().window(i);
		}

		System.out.println("5. " + driver.getTitle());

		driver.findElement(By.xpath("//a[text()= 'XPath - Relative Path']")).click();

		//Driver goes back to Yahoo
		driver.switchTo().window(Handle1);
		System.out.println("6. " + driver.getTitle());
		
		driver.findElement(By.xpath(" //a[text()= 'XPath - Wikipedia']")).click();

		//Switches driver to Wikipedia
		Set<String> EvenMoreHandles = driver.getWindowHandles();
		System.out.println("Set: " + EvenMoreHandles);

		for (String i : EvenMoreHandles) {
			System.out.println(i);
			driver.switchTo().window(i);
		}

		System.out.println("7. " + driver.getTitle());
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
