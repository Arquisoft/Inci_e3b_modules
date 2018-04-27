package es.uniovi.asw.e3b.incitest_e3b;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@Component
@ContextConfiguration(classes = TestContextConfiguration.class)
@PropertySource("classpath:application.properties")
public class ConfigProperties {
	
	@Value("${agents.server.address:localhost}")
	private String agentsServerAddress;
	@Value("${agents.server.port:8090}")
	private int agentsServerPort;
	@Value("${incimanager.server.address:localhost}")
	private String inciManagerServerAddress;
	@Value("${incimanager.server.port:8091}")
	private int inciManagerServerPort;
	@Value("${incidashboard.server.address:localhost}")
	private String inciDashboardServerAddress;
	@Value("${incidashboard.server.port:8092}")
	private int inciDashboardServerPort;

	/**
	 * @return the agentsServerAddress
	 */
	public String getAgentsServerAddress() {
		return agentsServerAddress;
	}

	/**
	 * @param agentsServerAddress
	 *            the agentsServerAddress to set
	 */
	public void setAgentsServerAddress(String agentsServerAddress) {
		this.agentsServerAddress = agentsServerAddress;
	}

	/**
	 * @return the agentsServerPort
	 */
	public int getAgentsServerPort() {
		return agentsServerPort;
	}

	/**
	 * @param agentsServerPort
	 *            the agentsServerPort to set
	 */
	public void setAgentsServerPort(int agentsServerPort) {
		this.agentsServerPort = agentsServerPort;
	}

	/**
	 * @return the inciManagerServerAddress
	 */
	public String getInciManagerServerAddress() {
		return inciManagerServerAddress;
	}

	/**
	 * @param inciManagerServerAddress
	 *            the inciManagerServerAddress to set
	 */
	public void setInciManagerServerAddress(String inciManagerServerAddress) {
		this.inciManagerServerAddress = inciManagerServerAddress;
	}

	/**
	 * @return the inciManagerServerPort
	 */
	public int getInciManagerServerPort() {
		return inciManagerServerPort;
	}

	/**
	 * @param inciManagerServerPort
	 *            the inciManagerServerPort to set
	 */
	public void setInciManagerServerPort(int inciManagerServerPort) {
		this.inciManagerServerPort = inciManagerServerPort;
	}

	/**
	 * @return the inciDashboardServerAddress
	 */
	public String getInciDashboardServerAddress() {
		return inciDashboardServerAddress;
	}

	/**
	 * @param inciDashboardServerAddress
	 *            the inciDashboardServerAddress to set
	 */
	public void setInciDashboardServerAddress(String inciDashboardServerAddress) {
		this.inciDashboardServerAddress = inciDashboardServerAddress;
	}

	/**
	 * @return the inciDashboardServerPort
	 */
	public int getInciDashboardServerPort() {
		return inciDashboardServerPort;
	}

	/**
	 * @param inciDashboardServerPort
	 *            the inciDashboardServerPort to set
	 */
	public void setInciDashboardServerPort(int inciDashboardServerPort) {
		this.inciDashboardServerPort = inciDashboardServerPort;
	}

	public String getAgentsBaseUrl() {
		return "http://" + agentsServerAddress + ":" + agentsServerPort;
	}

	public String getInciManagerBaseUrl() {
		return "http://" + inciManagerServerAddress + ":" + inciManagerServerPort;
	}

	public String getInciDashboardBaseUrl() {
		return "http://" + inciDashboardServerAddress + ":" + inciDashboardServerPort;
	}
	
	public String getServiceBaseUrlByName(String serviceName) {
		String serviceBaseUrl = null;
		switch (serviceName) {
		case "Agents":
			serviceBaseUrl = getAgentsBaseUrl();
			break;
		case "InciManager":
			serviceBaseUrl = getInciManagerBaseUrl();
			break;
		case "InciDashboard":
			serviceBaseUrl = getInciDashboardBaseUrl();
			break;
		}
		return serviceBaseUrl;
	}
}
