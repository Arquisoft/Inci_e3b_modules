package asw.inci_manager.inci_manager_gest.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Operario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    @OneToMany
    private Set<Incidence> incidencias = new HashSet<>();


    public Operario(String email, String password, String role, Set<Incidence> incidencias) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.incidencias = incidencias;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((incidencias == null) ? 0 : incidencias.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        Operario other = (Operario) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (incidencias == null) {
            if (other.incidencias != null)
                return false;
        } else if (!incidencias.equals(other.incidencias))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (role == null) {
            return other.role == null;
        } else return role.equals(other.role);
    }

    public Operario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}