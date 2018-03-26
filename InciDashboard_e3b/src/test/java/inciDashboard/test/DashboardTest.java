package inciDashboard.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import inciDashboard.test.PO.PO_LoginView;
import inciDashboard.test.PO.PO_NavView;
import inciDashboard.test.PO.PO_View;

public class DashboardTest {

	// En Windows (Debe ser la versi�n 46.0 y desactivar las actualizacioens
		// autom�ticas)):
		static String PathFirefox = "F:\\SDI\\WebTesting\\Firefox46.win\\FirefoxPortable.exe";
		// En MACOSX (Debe ser la versi�n 46.0 y desactivar las actualizaciones
		// autom�ticas):
		// static String PathFirefox =
		// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
		// Com�n a Windows y a MACOSX

		static WebDriver driver = getDriver(PathFirefox);
		static String URL = "http://localhost:8090";

		public static WebDriver getDriver(String PathFirefox) {
			// Firefox (Versi�n 46.0) sin geckodriver para Selenium 2.x.
			System.setProperty("webdriver.firefox.bin", PathFirefox);
			WebDriver driver = new FirefoxDriver();
			return driver;
		}

		// Antes de cada prueba se navega al URL home de la aplicaci�nn
		@Before
		public void setUp() {
			driver.navigate().to(URL);	
		}

		// Despu�s de cada prueba se borran las cookies del navegador
		@After
		public void tearDown() {
			driver.manage().deleteAllCookies();
		}

		// Antes de la primera prueba
		@BeforeClass
		static public void begin() {
		}

		// Al finalizar la �ltima prueba
		@AfterClass
		static public void end() {
			// Cerramos el navegador al finalizar las pruebas
			driver.quit();
		}

		// PR01. Acceder a la p�gina principal /
		@Test
		public void testLogin() {
			// Vamos al formulario de logueo.
			PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "oper121223221@gmail.es", "123456");
			// COmprobamos que entramos en la pagina privada de Alumno
			PO_View.checkElement(driver, "text", "oper12@gmail.es");
		}
}
