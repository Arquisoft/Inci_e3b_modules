package model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.util.ModelException;

@Entity
@Table(name = "TAgent")
public class Agent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;	
	private Localizacion location;
	private String email;
	private String ident;
	private String kind;
	private String clave;

	Agent() {
	}

	public Agent(String nombre, Localizacion localizacion, String email, String identificador, String tipo) throws ModelException {
		super();
		this.nombre = nombre;
		this.location = localizacion;
		this.email = email;
		this.ident = identificador;
		this.kind = tipo;
		if(tipo.toLowerCase().equals("sensor") && localizacion==null)
			throw new ModelException("Un sensor debe tener localización");
		generateClave();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Localizacion getLocalizacion() {
		return location;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.location = localizacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificador() {
		return ident;
	}

	public void setIdentificador(String identificador) {
		this.ident = identificador;
	}

	public String getTipo() {
		return kind;
	}

	public void setTipo(String tipo) {
		this.kind = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		return "Agent [nombre=" + nombre + ", localizacion=" + location + ", email=" + email + ", identificador=" + ident + ", tipo=" + kind + "]";
	}

	public String getClave() {
		return clave;
	}

	public void generateClave() {
		Random r = new Random();
		this.clave = r.nextInt(99999)+"";
		
	}

	



}
