package asw.inci_manager.inci_manager_gest.entities;

import asw.inci_manager.util.Estado;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@Entity
@Table(name = "Incidences")
public class Incidence {

    // Id generado automáticamente para diferenciar cada uno (para mapear)
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Agent agent;                        // agente que envía la incidencia
    private String incidenceName;               // titulo de la incidencia
    private String description;                 // descripcion de la incidencia
    private String location;                    // formato de la localización: "45.67, 32.86"

    @ElementCollection(targetClass = String.class)
    private Set<String> labels;                // etiquetas de la incidencia

    @ElementCollection(targetClass = String.class)
    private Set<String> comments;               // comentarios del operario

    private HashMap<String, String> fields;     // campos con propiedad valor que pueden ser necesarios.

    private Estado status;                      // Ver Enum: "Estado". Ej: ABIERTA, EN_PROCESO, CERRADA, ANULADA
    private Date expiration;                    // fecha de caducidad, ej: en caso de los sensores de temperatura
    private boolean cacheable;                  // Si esta incidencia se va a guardar o no. true | false

    @ElementCollection(targetClass = String.class)
    private Set<String> others;                 // otros valores que los agentes quieran.

    @ManyToOne
    private Operario operario;                  // operario encargado de la incidencia

    public Incidence() {
    }

    /**
     * Constructor con todos los parametros
     *
     * @param agent
     * @param incidenceName
     * @param description
     * @param location
     * @param labels
     * @param comments
     * @param fields
     * @param status
     * @param expiration
     * @param cacheable
     * @param others
     */
    public Incidence(Agent agent, String incidenceName, String description, String location, Set<String> labels,
                     Set<String> comments, HashMap<String, String> fields, Estado status, Date expiration,
                     boolean cacheable, Set<String> others, Operario operario) {
        this.agent = agent;
        this.incidenceName = incidenceName;
        this.description = description;
        this.location = location;
        this.labels = labels;
        this.comments = comments;
        this.fields = fields;
        this.status = status;
        this.expiration = expiration;
        this.cacheable = cacheable;
        this.others = others;
        this.operario = operario;
    }


    public Incidence(Agent agent, String incidenceName, String description, String location, Set<String> labels) {
        this.agent = agent;
        this.incidenceName = incidenceName;
        this.description = description;
        this.location = location;
        this.labels = labels;
        this.status = Estado.ABIERTA;
    }

    public Long getId() {
        return id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getIncidenceName() {
        return incidenceName;
    }

    public void setIncidenceName(String incidenceName) {
        this.incidenceName = incidenceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public Set<String> getComments() {
        return comments;
    }

    public void setComments(Set<String> comments) {
        this.comments = comments;
    }

    public HashMap<String, String> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, String> fields) {
        this.fields = fields;
    }

    public Estado getStatus() {
        return status;
    }

    public void setStatus(Estado status) {
        this.status = status;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public Set<String> getOthers() {
        return others;
    }

    public void setOthers(Set<String> others) {
        this.others = others;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }
    
    @Override
    	public String toString() {
    		return "Incidence [id=" + id + ", agent=" + agent + ", incidenceName=" + incidenceName + ", description="
    				+ description + ", location=" + location + ", labels=" + labels + ", comments=" + comments + ", fields="
    				+ fields + ", status=" + status + ", expiration=" + expiration + ", cacheable=" + cacheable
    				+ ", others=" + others + ", operario=" + operario + "]";
    	}
}
