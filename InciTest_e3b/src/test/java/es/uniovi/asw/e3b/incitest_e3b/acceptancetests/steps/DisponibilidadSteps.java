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

	@Cuando("^el usuario quiere disponer del servicio: \"([^\"]*)\"$")
	public void elUsuarioVisitaLaDireccionDelServicio(String servicio) throws Throwable {
		logger.trace("Cuando el usuario quiere disponer del servicio: '" + servicio + "'");
	}

	@Entonces("^el usuario tiene disponible la página web del servicio: \"([^\"]*)\" en la dirección: \"([^\"]*)\"$")
	public void elUsuarioTieneDisponibleLaPaginaWebDelServicioEnLaDireccion(String servicio, String direccion)
			throws Throwable {
		logger.trace("Entonces el usuario tiene disponible la página web del servicio: '" + servicio
				+ "' en la dirección: '" + direccion + "'");
		driver.navigate().to(direccion);
		// ToDo: Parametrizar la dirección de los servicios.
		assertThat(driver.getCurrentUrl()).isEqualTo(direccion);
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