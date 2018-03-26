package asw.selenium;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SeleniumTest {
	private WebDriver driver = new HtmlUnitDriver();
	private String URL = "http://localhost:8091/login";
	private StringBuffer verificationErrors = new StringBuffer();
	private int timeout = 9;

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	 //Inicio de sesión con datos válidos.
//	@Test
//	public void testUnioviTest3() throws Exception {
//		login("paco@gmail.com", "123456");
//		try {
//			assertTrue(driver.getCurrentUrl().equals("http://localhost:8091/home"));
//		} catch (Error e) {
//			verificationErrors.append(e.toString());
//		}
//	}

	/**
	 * Método auxiliar para loguearse
	 * 
	 * @param username
	 *            correo del usuario
	 * @param password
	 *            contraseña
	 */
	private void login(String username, String password) {
		esperaHastaQueCargueByName("username");
		rellenarByName("username", username);
		rellenarByName("password", password);
		click("loginButton");
	}

	/**
	 * Método auxiliar para esperar que carguen las páginas. Usa la variable timeout
	 * de 9 segundos en caso de tardar mucho
	 * 
	 * @param string
	 *            ID del elemento que esperas que cargue
	 */
	private WebElement esperaHastaQueCargue(String string) {
		return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.id(string)));
	}

	/**
	 * Método auxiliar para hacer click en elementos.
	 * 
	 * @param string
	 *            ID del elemento en el que quieres hacer click
	 */
	private void click(String string) {
		driver.findElement(By.id(string)).click();
	}

	/**
	 * Método auxiliar para rellenar campos de formularios
	 * @param nombre nombre del campo a rellenar
	 * @param texto valor
	 */
	private void rellenarByName(String nombre, String texto) {
		driver.findElement(By.name(nombre)).sendKeys(texto);
	}
	
	/**
	 * Método auxiliar para esperar que carguen las páginas. Usa la variable timeout
	 * de 9 segundos en caso de tardar mucho
	 * 
	 * @param string
	 *            nombre del elemento que esperas que cargue
	 */
	private WebElement esperaHastaQueCargueByName(String string) {
		return (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.name(string)));
	}

	/**
	 * Método auxiliar para rellenar campos de formularios
	 * 
	 * @param campoID
	 *            ID del campo a rellenar
	 * @param texto
	 *            valor
	 */
	private void rellenar(String campoID, String texto) {
		driver.findElement(By.id(campoID)).sendKeys(texto);
	}

	/**
	 * Aborta si el "texto" no está presente en la página actual
	 * 
	 * @param driver:
	 *            apuntando al navegador abierto actualmente.
	 * @param texto:
	 *            texto a buscar
	 */
	static public void textoPresentePagina(WebDriver driver, String texto) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));
		assertTrue("Texto " + texto + " no localizado!", list.size() > 0);
	}
}
