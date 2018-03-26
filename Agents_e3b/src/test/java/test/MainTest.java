package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import asw.Application;
import asw.db_management.GetAgent;
import asw.db_management.model.Agent;
import asw.agents.web_service.request.PeticionChangeEmailREST;
import asw.agents.web_service.request.PeticionChangePasswordREST;
import asw.agents.web_service.request.PeticionInfoREST;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	@Autowired
	private GetAgent getAgent;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	// {"login": usuario, "password": password, "kind": tipo de agente}
	/*
	 * repository.save(new
	 * Agent("Paco González","123456","","paco@gmail.com","paco","1"));
	 * repository.save(new
	 * Agent("Pepe Fernandez","123456","","pepe@gmail.com","pepe","1"));
	 * repository.save(new
	 * Agent("Sensor_123 2018","123456","43.361368, -5.853591","admin@sensores.com",
	 * "sensor_123","3")); repository.save(new
	 * Agent("Ministerio medioambiente","123456","43.359486, -5.846986"
	 * ,"ambiente@ministerio.com","medioambiente","2")); repository.save(new
	 * Agent("Space X sensor model A","123456","33.921209, -118.327940"
	 * ,"elonmusk@spacex.com","spacex","2"));
	 */

	@Test
	public void t1DomainModelEqualsTest() {
		Agent agent1 = getAgent.getAgent("paco@gmail.com");
		Agent agent2 = getAgent.getAgent("admin@sensores.com");
		Agent agent3 = getAgent.getAgent("paco@gmail.com");
		Agent agent4 = getAgent.getAgent("pepe@gmail.com");
		assertFalse(agent1.equals(agent2));
		assertFalse(agent1.equals(4));
		assertTrue(agent1.equals(agent3));
		assertTrue(agent1.equals(agent1));
		assertFalse(agent1.equals(agent4));
	}

	@Test
	public void t2DomainModelToString() {
		Agent agent1 = getAgent.getAgent("paco@gmail.com");
		assertEquals(agent1.toString(),
				"Agent{" + "nombre='" + agent1.getNombre() + '\'' + ", location='" + agent1.getLocation() + '\''
						+ ", email='" + agent1.getEmail() + '\'' + ", ident='" + agent1.getIdent() + '\'' + ", kind='"
						+ agent1.getKind() + '\'' + '}');
	}

	@Test
	public void t3DomainModelHashCodeTest() {
		Agent participant1 = getAgent.getAgent("paco@gmail.com");
		Agent participant3 = getAgent.getAgent("paco@gmail.com");
		assertEquals(participant1.hashCode(), participant3.hashCode());
	}

	@Test
	public void t4participantExistAndCorrectPasssword() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/user";

		response = template.postForEntity(userURI, new PeticionInfoREST("paco@gmail.com", "123456", "Person"), String.class);
		assertThat(response.getBody(), equalTo(
				"{\"nombre\":\"Paco González\",\"location\":\"\",\"email\":\"paco@gmail.com\",\"id\":\"paco\",\"kind\":\"Person\",\"kindCode\":1}"));

		response = template.postForEntity(userURI, new PeticionInfoREST("pepe@gmail.com", "123456", "Person"), String.class);
		assertThat(response.getBody(), equalTo(
				"{\"nombre\":\"Pepe Fernandez\",\"location\":\"\",\"email\":\"pepe@gmail.com\",\"id\":\"pepe\",\"kind\":\"Person\",\"kindCode\":1}"));

		response = template.postForEntity(userURI, new PeticionInfoREST("elonmusk@spacex.com", "123456", "Sensor"), String.class);
		assertThat(response.getBody(), equalTo(
				"{\"nombre\":\"Space X sensor model A\",\"location\":\"33.921209, -118.327940\",\"email\":\"elonmusk@spacex.com\",\"id\":\"spacex\",\"kind\":\"Sensor\",\"kindCode\":3}"));
	}

	@Test
	public void t5participantDoNotExist() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/user";
		String userNotFound = "{\"reason\": \"User not found\"}";

		response = template.postForEntity(userURI, new PeticionInfoREST("ofelia@hotmail.com", "ajksdkje", "Person"),
				String.class);
		assertThat(response.getBody(), equalTo(userNotFound));

		response = template.postForEntity(userURI, new PeticionInfoREST("martin@hotmail.com", "shcxhqw", "Person"), String.class);
		assertThat(response.getBody(), equalTo(userNotFound));
	}

	@Test
	public void t6incorrectPassword() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/user";
		String incorrectPassword = "{\"reason\": \"Password do not match\"}";
		response = template.postForEntity(userURI, new PeticionInfoREST("paco@gmail.com", "12356", "Person"), String.class);
		assertThat(response.getBody(), equalTo(incorrectPassword));

		response = template.postForEntity(userURI, new PeticionInfoREST("pepe@gmail.com", "12346", "Person"), String.class);
		assertThat(response.getBody(), equalTo(incorrectPassword));
	}

	@Test
	public void t7emptyEmail() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/user";
		String emptyEmail = "{\"reason\": \"User email is required\"}";
		response = template.postForEntity(userURI, new PeticionInfoREST("", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionInfoREST("", "1223", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionInfoREST("", "iewgs", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionInfoREST("   ", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));
	}

	@Test
	public void t8invalidEmail() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/user";
		String wrongEmailStyle = "{\"reason\": \"Wrong mail style\"}";
		response = template.postForEntity(userURI, new PeticionInfoREST("ajsjc", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionInfoREST("jxjsjd@", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionInfoREST("chdgetc@chhsy", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionInfoREST("sjhwuwsc", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));
	}

	@Test
	public void t9emptyPassword() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/user";
		String emptyPassword = "{\"reason\": \"User password is required\"}";

		response = template.postForEntity(userURI, new PeticionInfoREST("paco@hotmail.com", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyPassword));

		response = template.postForEntity(userURI, new PeticionInfoREST("pepe@gmail.com", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyPassword));

		response = template.postForEntity(userURI, new PeticionInfoREST("carmen@yahoo.com", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyPassword));

		response = template.postForEntity(userURI, new PeticionInfoREST("isabel@gmail.com", "", "Person"), String.class);
		assertThat(response.getBody(), equalTo(emptyPassword));
	}

	@Test
	public void t10emailRequiredChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String emptyEmail = "{\"reason\": \"User email is required\"}";

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("", "", ""), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("	", "", ""), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("", "", "shfhs"), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));
	}

	@Test
	public void t12newEmailRequiredChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String emptyEmail = "{\"reason\": \"User email is required\"}";

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail.com", "", ""),
				String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail.com", "", "   "),
				String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail.com", "", "	"),
				String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));
	}

	@Test
	public void t13invalidEmailChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String wrongEmailStyle = "{\"reason\": \"Wrong mail style\"}";

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco", "", ""), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@", "", "   "), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail", "  ", "	"),
				String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));
	}

	@Test
	public void t14newInvalidEmailChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String wrongEmailStyle = "{\"reason\": \"Wrong mail style\"}";

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail.com", "", "xhhuwi"),
				String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail.com", "", "fhgythf@"),
				String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("paco@hotmail.com", "", "fhfyg@hotmail"),
				String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));
	}

	@Test
	public void t15emailChangeUserNotFound() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String userNotFound = "{\"reason\": \"User not found\"}";

		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("pao@hotmail.com", "123456", "pac@hotmail.com"), String.class);
		assertThat(response.getBody(), equalTo(userNotFound));

		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("pee@gmail.com", "123456", "pepe@hotmail.com"), String.class);
		assertThat(response.getBody(), equalTo(userNotFound));

		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("pa@hotmail.com", "123456", "fhfyg@hotmail.com"), String.class);
		assertThat(response.getBody(), equalTo(userNotFound));
	}

	@Test
	public void t16sameEmailErrorChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String sameEmail = "{\"reason\": \"Same email\"}";

		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("paco@hotmail.com", "", "paco@hotmail.com"), String.class);
		assertThat(response.getBody(), equalTo(sameEmail));

		response = template.postForEntity(userURI, new PeticionChangeEmailREST("pepe@gmail.com", "", "pepe@gmail.com"),
				String.class);
		assertThat(response.getBody(), equalTo(sameEmail));

		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("carmen@yahoo.com", "", "carmen@yahoo.com"), String.class);
		assertThat(response.getBody(), equalTo(sameEmail));
	}

	@Test
	public void t17emailRequiredPasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String emptyEmail = "{\"reason\": \"User email is required\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("", "", ""), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("	", "chsh", ""), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("", "dfhe", "dhdgx"), String.class);
		assertThat(response.getBody(), equalTo(emptyEmail));
	}

	@Test
	public void t18inValidRequiredPasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String wrongEmailStyle = "{\"reason\": \"Wrong mail style\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("shdgr", "", ""), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("shdgr@", "", ""), String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("shdgr@hotmail", "", ""),
				String.class);
		assertThat(response.getBody(), equalTo(wrongEmailStyle));
	}

	@Test
	public void t19passwordRequiredPasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String passwordRequired = "{\"reason\": \"User password is required\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "", ""),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "", "dkdddd"),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "", "dkejd"),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));
	}

	@Test
	public void t20newPasswordRequiredPasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String passwordRequired = "{\"reason\": \"User password is required\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "djfhr", ""),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "djvhrhc", ""),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "dkejd", ""),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));
	}

	@Test
	public void t21samePasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String passwordRequired = "{\"reason\": \"Password Incorrect\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "djfhr", "djfhr"),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));

		response = template.postForEntity(userURI,
				new PeticionChangePasswordREST("paco@hotmail.com", "djvhrhc", "djvhrhc"), String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "dkejd", "dkejd"),
				String.class);
		assertThat(response.getBody(), equalTo(passwordRequired));
	}

	@Test
	public void t22notFoundParticipantPasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String userNotFound = "{\"reason\": \"User not found\"}";

		response = template.postForEntity(userURI,
				new PeticionChangePasswordREST("pac@hotmail.com", "djfhr", "djfhrtt"), String.class);
		assertThat(response.getBody(), equalTo(userNotFound));

		response = template.postForEntity(userURI,
				new PeticionChangePasswordREST("martin@hotmail.com", "djvhrhc", "tt"), String.class);
		assertThat(response.getBody(), equalTo(userNotFound));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("juan@hotmail.com", "dkejd", "tt"),
				String.class);
		assertThat(response.getBody(), equalTo(userNotFound));
	}

	@Test
	public void t23notSamePasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String passwordIncorrect = "{\"reason\": \"Password Incorrect\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@hotmail.com", "djfhr", "djfhr"),
				String.class);
		assertThat(response.getBody(), equalTo(passwordIncorrect));

		response = template.postForEntity(userURI,
				new PeticionChangePasswordREST("pepe@gmail.com", "djvhrhc", "djvhrhc"), String.class);
		assertThat(response.getBody(), equalTo(passwordIncorrect));

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("carmen@yahoo.com", "dkejd", "dkejd"),
				String.class);
		assertThat(response.getBody(), equalTo(passwordIncorrect));
	}

	@Test
	public void t24testHtmlController() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/";
		String str_final = "<!DOCTYPEHTML><html><head><metacharset=\"UTF-8\"/><title>Login</title><linkrel=\"stylesheet\"type=\"text/css\"href=\"/templates/css/styles.css\"/><linkrel=\"stylesheet\"href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\"/><scriptsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"></script><scriptsrc=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"></script></head><body><mainclass=\"containerlogin_page\"><h1>Login</h1><formmethod=\"POST\"action=\"login\"class=\"form-group\"><table><tr><td><labelfor=\"email\"><strong>Usuario:</strong></label></td><td><inputtype=\"text\"class=\"form-control\"id=\"email\"name=\"email\"/></td></tr><tr><td><labelfor=\"password\"><strong>Contraseña:</strong></label></td><td><inputtype=\"password\"class=\"form-control\"id=\"password\"name=\"password\"/></td></tr><tr><tdcolspan=\"2\"><buttontype=\"submit\"class=\"btnbtn-primarybtn_enviar\"id=\"login\">Entrar</button></td></tr></table></form></main></body></html>";
		response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody().replace(" ", "").replace("\n", "").replace("\t", ""),
				equalTo((str_final).replace(" ","")));
	}

	@Test
	public void emailChangeCorrect() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";

		String correctChange = "{\"agent\":\"paco1@gmail.com\",\"message\":\"email actualizado correctamente\"}";
		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("paco@gmail.com", "123456", "paco1@gmail.com"), String.class);
		assertThat(response.getBody(), equalTo(correctChange));
	}

	@Test
	public void correctPasswordChange() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String correctPassword = "{\"agent\":\"paco@gmail.com\",\"message\":\"contraseña actualizada correctamente\"}";

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@gmail.com", "123456", "djfhr"),
				String.class);
		assertThat(response.getBody(), equalTo(correctPassword));
	}

	@Test
	public void correctPasswordChangeXML() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changePassword";
		String correctChange = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<ChangeInfoResponse><agent>paco@gmail.com</agent>"
				+ "<message>contraseÃ±a actualizada correctamente</message></ChangeInfoResponse>";

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new AcceptInterceptor());

		template.setInterceptors(interceptors);

		response = template.postForEntity(userURI, new PeticionChangePasswordREST("paco@gmail.com", "djfhr", "123456"),
				String.class);
		assertThat(response.getBody(), equalTo(correctChange));
	}

	@Test
	public void emailChangeCorrectXML() {
		ResponseEntity<String> response;
		String userURI = base.toString() + "/changeEmail";
		String correctChange = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<ChangeInfoResponse><agent>paco@gmail.com</agent>"
				+ "<message>email actualizado correctamente</message>" + "</ChangeInfoResponse>";

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new AcceptInterceptor());

		template.setInterceptors(interceptors);

		response = template.postForEntity(userURI,
				new PeticionChangeEmailREST("paco1@gmail.com", "123456", "paco@gmail.com"), String.class);
		assertThat(response.getBody(), equalTo(correctChange));
	}

	// Cabecera HTTP para pedir respuesta en XML
	public class AcceptInterceptor implements ClientHttpRequestInterceptor {
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException {
			HttpHeaders headers = request.getHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
			return execution.execute(request, body);
		}
	}
}
