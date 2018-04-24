package es.uniovi.asw.e3b.incitest_e3b.acceptancetests.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class DisponibilidadSteps {
	private static final Logger logger = LogManager.getLogger(DisponibilidadSteps.class);

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private int implicitTimeout = 10;

	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
	}

	@Dado("^el servicio: \"([^\"]*)\" desplegado en el puerto: \"([^\"]*)\" del anfitrión: \"([^\"]*)\"$")
	public void elServicioDesplegadoEnElPuertoDelAnfitrion(String service, String port, String host) throws Throwable {
		logger.trace("Dado el servicio: '" + service + "' desplegado en el puerto: '" + port + "' del anfitrión: '" + host + "'");
	}
	
	@Cuando("^el usuario quiere disponer del servicio: \"([^\"]*)\"$")
	public void elUsuarioVisitaLaDireccionDelServicio(String service) throws Throwable {
		logger.trace("Cuando el usuario quiere disponer del servicio: '" + service + "'");
	}

	@Entonces("^se encuentra disponible la dirección web: \"([^\"]*)\"$")
	public void seEncuentraDisponibleLaDireccionWeb(String url) throws Throwable {
		logger.trace("se encuentra disponible la dirección web: '" + url + "'");
		driver.navigate().to(url);
		// ToDo: Parametrizar la dirección de los servicios.
		assertThat(driver.getCurrentUrl()).isEqualTo(url);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}