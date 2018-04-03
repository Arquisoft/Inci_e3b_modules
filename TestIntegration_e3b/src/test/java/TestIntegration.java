

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;


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
        // TODO: llamar a los servicios y que estén corriendo.
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
        //TODO: completar
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
