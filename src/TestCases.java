import java.util.Random;

import javax.security.auth.Refreshable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {

	WebDriver driver = new ChromeDriver();
	String URL = "https://asaqa988.github.io/Api-Library-System-/";
	Random rand = new Random();
	String[] bookName = { "Sky", "Fire", "Code", "Life", "Dark" };
	String[] bookISBN = { "101", "102", "103", "104", "105" };
	String[] bookAisle = { "11", "22", "33", "44", "55" };
	String[] authorName = { "Ali", "Omar", "Sara", "Noor", "Zed" };
	String ID = "";

	@BeforeTest
	public void mySetup() {
		driver.get(URL);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void addBook() throws InterruptedException {
		// Attention!!! take the text without space
		WebElement addBookButton = driver.findElement(By.linkText("Add new Book"));
		addBookButton.click();

		WebElement bookNameFeild = driver.findElement(By.id("bookName"));
		WebElement bookISBNFeild = driver.findElement(By.id("bookISBN"));
		WebElement bookAisleFeild = driver.findElement(By.id("bookAisle"));
		WebElement authorNameFeild = driver.findElement(By.id("authorName"));

		int RandomIndex = rand.nextInt(5);
		String randomBookName = bookName[RandomIndex];
		bookNameFeild.sendKeys(randomBookName);

		String randomBookISBN = bookISBN[RandomIndex];
		bookISBNFeild.sendKeys(randomBookISBN);

		String randomBookAisle = bookAisle[RandomIndex];
		bookAisleFeild.sendKeys(randomBookAisle);

		String randomAuthorName = authorName[RandomIndex];
		authorNameFeild.sendKeys(randomAuthorName);

		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
		submitButton.click();

		Thread.sleep(2000);
		String responseMsg = driver.findElement(By.id("response")).getText();
		System.out.println("Msg: " + responseMsg);
		String bookID = responseMsg.split("\"ID\": \"")[1].split("\"")[0];
		System.out.println("id: " + bookID);
		ID = bookID;

		WebElement homeButton = driver.findElement(By.id("homeButton"));
		homeButton.click();
		homeButton = driver.findElement(By.id("homeButton"));
		homeButton.click();

	}

	@Test(priority = 2)
	public void getBook() throws InterruptedException {
		WebElement getBookButton = driver.findElement(By.linkText("get Book By ID"));
		getBookButton.click();

		WebElement bookIDField = driver.findElement(By.id("bookID"));
		bookIDField.sendKeys(ID);

		WebElement getBookInformation = driver.findElement(By.xpath("//button[text()='Get Book Information']"));
		getBookInformation.click();

		Thread.sleep(1000);
		WebElement homeButton = driver.findElement(By.id("homeButton"));
		homeButton.click();

	}

	@Test(priority = 3)
	public void deleteBook() {
		WebElement deleteBookButton = driver.findElement(By.linkText("Delete Book By Id"));
		deleteBookButton.click();

		WebElement bookIDField = driver.findElement(By.id("bookID"));
		bookIDField.sendKeys(ID);

		WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete Book']"));
		deleteButton.click();

	}

	@AfterTest
	public void closeTheWebsit() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}

}
