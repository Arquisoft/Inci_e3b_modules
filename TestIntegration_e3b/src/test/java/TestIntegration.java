

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotEquals;

import java.net.URL;

/**
 * INFO: los servicios están en estos puertos:
 *
 *      http://localhost:8090/ (Agent)
 *      http://localhost:8091/ (InciManager)
 *      http://localhost:8092/ (InciDashboard)
 *
 */
@SuppressWarnings("deprecation")
public class TestIntegration {
    /**
     * Pasos previos para la ejecución de estos tests
     *
     * 1. Ejecutar Zookeeper y Zafka
     * 2. Ejecutar base de datos hsqldb
     * 3. Ejecutar los módulos implicados (Agents, InciManager e InciDashboard)
     */

    private URL base;
    private RestTemplate template;

    /**
     * Comprobar aquí haciendo una petición GET o POST a los puertos 8090 (Agent), 8091 (InciManager), 8092 (InciDashboard),
     * y que no devuelvan un error 404 (servidor no disponible)
     */
    @BeforeClass
    public static void checkServicesRunning(){
        ResponseEntity<String> response;
        HttpStatus statusCode = null;
        // TODO: llamar a los servicios y que estén corriendo.
        
        //Comprobación Agents
        
        String userURI = "http://localhost:8090/";
	    response = template.getForEntity(userURI, String.class);
        statusCode = response.getStatusCode();
        assertNotEquals(HttpStatus.NOT_FOUND, equalTo(statusCode));
        
        //Comprobación InciManager

		userURI = "http://localhost:8091/";
		response = template.getForEntity(userURI, String.class);
        statusCode = response.getStatusCode();
        assertNotEquals(HttpStatus.NOT_FOUND, equalTo(statusCode));
        
        //Comprobación InciDashboard
        
        userURI = "http://localhost:8092/";
		response = template.getForEntity(userURI, String.class);
        statusCode = response.getStatusCode();
        assertNotEquals(HttpStatus.NOT_FOUND, equalTo(statusCode));
     
    }

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    /**
     * En este test, añado una incidencia mediante una llamada al servicio REST de inciManager (POST)
     * http://localhost:8091/ <- En el puerto 8091 está InciManager
     */
    @Test
    public void test1_InciManagerAddIncidence(){
        String url = "http://localhost:8091//addIncidence";
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	String jsonText = "{\"username\":\"paco@gmail.com\","
    			+ "\"password\":\"123456\","
    			+ "\"incidenceName\":\"name\","
    			+ "\"description\":\"description\","
    			+ "\"location\":\"location\","
    			+ "\"labels\":[],"
    			+ "\"campos\":{},"
    			+ "\"status\":\"ABIERTA\","
    			+ "\"expiration\":\"123\","
    			+ "\"cacheable\":\"true\"}";
    	String restResponse = "{\"idIncidencia\":null,"
    			+ "\"username\":\"paco@gmail.com\","
    			+ "\"password\":\"123456\","
    			+ "\"incidenceName\":\"name\","
    			+ "\"description\":\"description\","
    			+ "\"location\":\"location\","
    			+ "\"labels\":[],"
    			+ "\"campos\":{},"
    			+ "\"status\":\"ABIERTA\","
    			+ "\"expiration\":123,"
    			+ "\"cacheable\":true}";
    	HttpEntity<String> entity = new HttpEntity<String>(jsonText, headers);
    	ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, String.class);
    	assertThat(response.getBody(),equalTo(restResponse));
    }

    /**
     * En este test, compruebo que la incidencia añadida en el test anterior la ha recibido el módulo InciDashboard
     * http://localhost:8092/ <- En el puerto 8092 está InciDashboard
     */
    @Test
    public void test2_InciDashboardReadIncidence(){
        //TODO: completar
    }

}
