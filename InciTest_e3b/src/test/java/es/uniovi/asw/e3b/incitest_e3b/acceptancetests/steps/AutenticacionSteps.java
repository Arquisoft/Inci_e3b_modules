package es.uniovi.asw.e3b.incitest_e3b.acceptancetests.steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;

public class AutenticacionSteps {

	private static final Logger logger = LogManager.getLogger(AutenticacionSteps.class);

	// @Value("${agents.server.address:localhost}")
	private String agentsServerAddress = "localhost";
	// @Value("${agents.server.port:8090}")
	private int agentsServerPort = 8090;
	// @Value("${incimanager.server.address:localhost}")
	private String inciManagerServerAddress = "localhost";
	// @Value("${incimanager.server.port:8091}")
	private int inciManagerServerPort = 8091;
	// @Value("${incidashboard.server.address:localhost}")
	private String inciDashboardServerAddress = "localhost";
	// @Value("${incidashboard.server.port:8092}")
	private int inciDashboardServerPort = 8092;

	private String agentsBaseUrl;
	private String inciManagerBaseUrl;
	private String inciDashboardBaseUrl;
	private WebDriver driver;
	private int implicitTimeout = 10;
	private int explicitTimeout = 30;

	private String getServiceBaseUrl(String serviceName) {
		String serviceBaseUrl = null;
		switch (serviceName) {
		case "Agents":
			serviceBaseUrl = agentsBaseUrl;
			break;
		case "InciManager":
			serviceBaseUrl = inciManagerBaseUrl;
			break;
		case "InciDashboard":
			serviceBaseUrl = inciDashboardBaseUrl;
			break;
		}
		return serviceBaseUrl;
	}

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
		agentsBaseUrl = "http://" + agentsServerAddress + ":" + agentsServerPort;
		inciManagerBaseUrl = "http://" + inciManagerServerAddress + ":" + inciManagerServerPort;
		inciDashboardBaseUrl = "http://" + inciDashboardServerAddress + ":" + inciDashboardServerPort;
		logger.debug("Base URL for sevice 'Agents': " + agentsBaseUrl);
		logger.debug("Base URL for sevice 'InciManager': " + inciManagerBaseUrl);
		logger.debug("Base URL for sevice 'InciDashboard': " + inciDashboardBaseUrl);
	}

	@Dado("^un agente previamente registrado en el sistema con el nombre de usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\"$")
	public void unAgentePreviamenteRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasenia(String username,
			String password) throws Throwable {
		logger.debug("Dado un agente previamente registrado en el sistema con el nombre de usuario: '" + username
				+ "' y la contraseña: '" + password + "'");
	}

	@Y("^no puedo acceder a la página principal del servicio: \"([^\"]*)\"$")
	public void noPuedoAccederALaPaginaPrincipalDelServicio(String service) throws Throwable {
		logger.debug("Y no puedo acceder a la página principal del servicio: '" + service + "'");
		driver.navigate().to(inciManagerBaseUrl + "/home");
		assertThat(driver.getCurrentUrl()).isNotEqualTo(getServiceBaseUrl(service) + "/home");
		;
	}

	@Cuando("^visito la página de inicio de sesión del servicio: \"([^\"]*)\"$")
	public void visitoLaPaginaDeIncioDeSesionDelServicio(String service) throws Throwable {
		logger.debug("Cuando visito la página de inicio de sesión del servicio: '" + service + "'");
		driver.navigate().to(inciManagerBaseUrl + "/login");
		// ToDO: Wait for element
		assertThat(driver.getCurrentUrl()).isEqualTo(getServiceBaseUrl(service) + "/login");
	}

	@Y("^introduzco el nombre de usuario: \"([^\"]*)\" en el formulario$")
	public void introduzcoElNombreDeUsuarioEnElFormulario(String username) throws Throwable {
		logger.debug("E introduzco el nombre de usuario: '" + username + "' en el formulario");
		new WebDriverWait(driver, explicitTimeout)
				.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		driver.findElement(By.name("username")).sendKeys(username);
	}

	@Y("^introduzco la contraseña de usuario: \"([^\"]*)\" en el formulario$")
	public void introduzcoLaContraseniaDeUsuarioEnElFormulario(String password) throws Throwable {
		logger.debug("E introduzco la contraseña de usuario: '" + password + "' en el formulario");
		new WebDriverWait(driver, explicitTimeout)
				.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		driver.findElement(By.name("password")).sendKeys(password);
	}

	@Y("^presiono el botón de confirmación$")
	public void presionoElBotonDeConfirmacion() throws Throwable {
		logger.debug("Y presiono el botón de confirmación");
		driver.findElement(By.id("loginButton")).click();
	}

	@Entonces("^puedo acceder a la página principal del servicio: \"([^\"]*)\"$")
	public void puedoAccederALaPaginaPrincipalDelServicio(String service) throws Throwable {
		logger.debug("Entonces puedo acceder a la página principal del servicio: '" + service + "'");
		assertThat(driver.getCurrentUrl()).isEqualTo(getServiceBaseUrl(service) + "/home");
	}

	@Dado("^ningún agente registrado en el sistema con el nombre de usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\"$")
	public void ningunAgenteNoRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasenia(String username, String password)
			throws Throwable {
		logger.debug("Dado nigún agente registrado en el sistema con el nombre de usuario: '" + username
				+ "' y la contraseña: '" + password + "'");
	}

	@Entonces("^recibo una notificación de error de acceso al servicio: \"([^\"]*)\"$")
	public void reciboUnaNotificacionDeErrorDeAccesoAlServicio(String service) throws Throwable {
		logger.debug("Entonces recibo una notifiación de error de acceso al servicio: '" + service + "'");
		assertThat(driver.getCurrentUrl()).isEqualTo(getServiceBaseUrl(service) + "/login?error");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
