import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
public class NewPageTest {

  WebDriver webdriver;

  @BeforeAll
  public void setupAll() {
    System.setProperty("webdriver.chrome.driver",
            "src/test/resources/chromedriver_linux64/chromedriver.exe");
  }

  @BeforeEach
  public void setup() {
    webdriver = new ChromeDriver();
    webdriver.manage().window().maximize();
  }

  //assertEquals
  @Test
  public void openPageFalse() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br");
    Assertions.assertEquals("http://www.artesanatodepernambuco.pe.gov.br",
            webdriver.getCurrentUrl());
  }

  //assertTrue
  @Test
  public void checkPageTitleTrue() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br");
    boolean checkTitle = webdriver.getTitle().equalsIgnoreCase("Portal do Artesanato");
    Assertions.assertTrue(checkTitle);
  }

  // assertFalse
  @Test
  public void checkPageTitleFalse() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br");
    boolean checkTitle = webdriver.getTitle().equalsIgnoreCase("Artesanato de Pernambuco");
    Assertions.assertFalse(checkTitle);
  }

  //assertFalse
  @Test
  public void checkUrlAfterClick() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br");
    webdriver.findElement(By.xpath("/html/body/nav/div[1]/div[2]/div/a[2]")).click();
    boolean newUrl = webdriver.getCurrentUrl().equals("http://www.artesanatodepernambuco.pe.gov.br");
    Assertions.assertFalse(newUrl);
  }

  //Find Elements
  @Test
  public void findImgTags() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br");
    List<WebElement> elements = webdriver.findElements(By.tagName("img"));
    System.out.println("Number of <img>:" + elements.size());
  }

  //By.id
  @Test
  public void FindId() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br");
    WebElement menu_id = webdriver.findElement(By.id("home_menu"));
  }

  //sendKeys and submit
  //Ao fim desse teste, necessário scrollar página para baixo para vizualizar resultado exibido.
  @Test
  public void findingMasters() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br/pt-BR/mestres/nossos-mestres");
    Actions actions = new Actions(webdriver);
    WebElement searchInput = webdriver.findElement(By.id("search"));
    searchInput.sendKeys("J borges");
    WebElement submitButton = webdriver.findElement(By.id("filter_name_btn"));
    submitButton.submit();
  }

  //MoveTo
  @Test
  public void insertText() {
    webdriver.get("http://www.artesanatodepernambuco.pe.gov.br/pt-BR/centro-de-artesanato/unidade-recife/contato");
    WebElement inputName = webdriver.findElement(By.id("contact_name"));
    new Actions(webdriver).moveToElement(inputName).click();
    inputName.sendKeys("Marcelle Mascarenhas");
  }

  //MoveTo
  @Test
  public void clickMenuItem() {
    webdriver.get("https://www.sds.pe.gov.br/");
    WebElement menuLink = webdriver.findElement(By.xpath("/html/body/div/header/div/div/div[1]/nav/ul/li[3]"));
    new Actions(webdriver).moveToElement(menuLink).click();
  }


  //selectByIndex
  @Test
  public void selectIndexElement() {
    webdriver.get("https://www.sds.pe.gov.br/");
    Select menuItem = new Select(webdriver.findElement(By.id("category-select")));
    menuItem.selectByIndex(2);
  }
}