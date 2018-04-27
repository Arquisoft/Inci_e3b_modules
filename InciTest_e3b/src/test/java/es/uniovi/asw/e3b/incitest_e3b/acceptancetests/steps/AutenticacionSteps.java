package es.uniovi.asw.e3b.incitest_e3b.acceptancetests.steps;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import es.uniovi.asw.e3b.incitest_e3b.ConfigProperties;
import es.uniovi.asw.e3b.incitest_e3b.TestContextConfiguration;

@ContextConfiguration(classes = TestContextConfiguration.class)
public class AutenticacionSteps {

	private static final Logger logger = LogManager.getLogger(AutenticacionSteps.class);

	@Autowired
	private ConfigProperties configProperties;

	private WebDriver driver;
	private int implicitTimeout = 10;
	private int explicitTimeout = 30;
	private RestTemplate restTemplate;
	private HttpEntity<String> httpEntity;

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
		restTemplate = new RestTemplate();
	}

	@Dado("^un agente previamente registrado en el sistema con el nombre de usuario: \"([^\"]*)\" y la contraseña: \"([^\"]*)\"$")
	public void unAgentePreviamenteRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasenia(String username,
			String password) throws Throwable {
		logger.debug("Dado un agente previamente registrado en el sistema con el nombre de usuario: '" + username
				+ "' y la contraseña: '" + password + "'");
	}

	@Y("^con el tipo de agente: \"([^\"]*)\"$")
	public void conElTipoDeAgente(String kind) throws Throwable {
		logger.debug("Y con el tipo de agente: '" + kind + "'");
	}

	@Cuando("^invoco el servicio con la siguiente solicitud en formato JSON:$")
	public void invocoElServicioConLaSiguienteSolicitudEnFormatoJSON(String jsonRequest) throws Throwable {
		logger.debug("Cuando invoco el servicio con la siguiente solicitud en formato JSON: '" + jsonRequest + "'");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpEntity = new HttpEntity<String>(jsonRequest, httpHeaders);
	}

	@Entonces("^el servicio devuelve la siguiente respuesta en formato JSON:$")
	public void elServicioDevuelveLaSiguienteRespuestaEnFormatoJSON(String jsonResponse) throws Throwable {
		logger.debug("Entonces el servicio devuelve la siguiente respuesta en formato JSON:'" + jsonResponse + "'");
		ResponseEntity<String> responseEntity = restTemplate.exchange(
				configProperties.getServiceBaseUrlByName("Agents") + "/user", HttpMethod.POST, httpEntity,
				String.class);
		assertThatJson(responseEntity.getBody()).isEqualTo(jsonResponse);
	}

	@Y("^no puedo acceder a la página principal del servicio: \"([^\"]*)\"$")
	public void noPuedoAccederALaPaginaPrincipalDelServicio(String service) throws Throwable {
		logger.debug("Y no puedo acceder a la página principal del servicio: '" + service + "'");
		driver.navigate().to(configProperties.getServiceBaseUrlByName(service) + "/home");
		assertThat(driver.getCurrentUrl()).isNotEqualTo(configProperties.getServiceBaseUrlByName(service) + "/home");
	}

	@Cuando("^visito la página de inicio de sesión del servicio: \"([^\"]*)\"$")
	public void visitoLaPaginaDeIncioDeSesionDelServicio(String service) throws Throwable {
		logger.debug("Cuando visito la página de inicio de sesión del servicio: '" + service + "'");
		driver.navigate().to(configProperties.getServiceBaseUrlByName(service) + "/login");
		// ToDO: Wait for element
		assertThat(driver.getCurrentUrl()).isEqualTo(configProperties.getServiceBaseUrlByName(service) + "/login");
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
		assertThat(driver.getCurrentUrl()).isEqualTo(configProperties.getServiceBaseUrlByName(service) + "/home");
	}

	@Dado("^ningún agente registrado en el sistema con el nombre de usuario: \"([^\"]*)\" y la contraseña: \"([^\"]*)\"$")
	public void ningunAgenteNoRegistradoEnElSistemaConElNombreDeUsuarioYLaContrasenia(String username, String password)
			throws Throwable {
		logger.debug("Dado nigún agente registrado en el sistema con el nombre de usuario: '" + username
				+ "' y la contraseña: '" + password + "'");
	}

	@Entonces("^recibo una notificación de error de acceso al servicio: \"([^\"]*)\"$")
	public void reciboUnaNotificacionDeErrorDeAccesoAlServicio(String service) throws Throwable {
		logger.debug("Entonces recibo una notifiación de error de acceso al servicio: '" + service + "'");
		assertThat(driver.getCurrentUrl())
				.isEqualTo(configProperties.getServiceBaseUrlByName(service) + "/login?error");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
