package Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Amazontest {
	
	
	
	public static void main(String[] args) {
		String Searchitem = null;
		String Category = null;
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver");
        
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Amazon_search","root","root");
        	Statement stmt = connect.createStatement();
        	ResultSet res = stmt.executeQuery("select * from Amazon_search");
        	
        	while(res.next())
			{
				System.out.println("Search category is: "+res.getString(2)+ "\n" +"Search Item is: "+res.getString(3));
				Category = res.getString(2);
				Searchitem = res.getString(3);
				
			}
        	
            
        }
	
        catch (ClassNotFoundException e) {
        	System.out.println("class not found");
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        	WebElement SearchAll =driver.findElement(By.xpath("//*[@title='Search in']"));
        	Select Options = new Select(SearchAll);
        	Options.selectByVisibleText(Category);
        	
        	WebElement SearchMob =driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
        	SearchMob.sendKeys(Searchitem);
        
        	WebElement Submit =driver.findElement(By.xpath("//*[@id='nav-search-submit-button']"));
        	Submit.click();
        
        	List<WebElement> ListCount = driver.findElements(By.xpath("//*[@class='s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col sg-col-12-of-16']"));
        	System.out.println("Total search result are: " + ListCount.size());
		
        	String ListCounttxt = String.valueOf(ListCount.size());
		
        	WebElement Message = driver.findElement(By.xpath("//*[@class='a-section a-spacing-small a-spacing-top-small']"));
        	System.out.println("Amazon Message: " + Message.getText());
		
        	String MessageCounttxt = Message.getText().substring(2, 4);
        	System.out.println("ResultCountTxt = " + MessageCounttxt);
		
		
        	if ( MessageCounttxt.equals(ListCounttxt)) 
        	{
        		System.out.println("Both numbers are matching");	
        	}
        	else 
        	{
        		System.out.println("Both Numbers are Not Matching");
        	}
		
		}	

}

        