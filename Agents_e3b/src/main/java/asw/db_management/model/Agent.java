package asw.db_management.model;

import asw.agents.util.Utilidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Agents")
public class Agent {

	// Id generado automáticamente para diferenciar cada uno (para mapear)
	@Id
	@GeneratedValue
	private Long id;

	// Atributos del agente
	private String nombre;
	private String password;
	private String location;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String ident;   // USUARIO, dni, nif o lo que sea que identifique al agente
	private String kind;    // "Person","Entity", "Sensor"

	/**
	 * Constructor vacío (ya que es para mapear)
	 */
	Agent() {
	}

    public Agent(String nombre, String password, String location, String email, String ident, String kind) {
        this.nombre = nombre;
        this.password = password;
        this.location = location;
        this.email = email;
        this.ident = ident;
        this.kind = kind;
    }

    public Long getId() {
		return id;
	}

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getIdent() {
        return ident;
    }

    public String getKind() {
        return kind;
    }

    public int getKindCode(){
        return Utilidades.getKindCode(getKind());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Agent{" +
                "nombre='" + nombre + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", ident='" + ident + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }

}
