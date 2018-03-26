package asw.agents.web_service.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import asw.db_management.model.Agent;
import asw.agents.util.Utilidades;

@XmlRootElement(name = "agent")
public class RespuestaInfoREST {	
	
	private String nombre;
	private String location;
	private String email;
	private String id;
	private String kind;
	private int kindCode;

	
	public RespuestaInfoREST() {}
	
	public RespuestaInfoREST(Agent agent){
		setNombre(agent.getNombre());
		setLocation(agent.getLocation());
		setEmail(agent.getEmail());
		setId(agent.getIdent());
		setKind(agent.getKind());
		setKindCode(agent.getKindCode());

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getKindCode() {
		return kindCode;
	}

	public void setKindCode(int kindCode) {
		this.kindCode = kindCode;
	}
}
