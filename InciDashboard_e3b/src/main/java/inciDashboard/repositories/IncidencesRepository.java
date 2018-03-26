package inciDashboard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;

public interface IncidencesRepository extends CrudRepository<Incidence,Long> {

	@Query("SELECT i FROM Incidence i WHERE i.operario=?1 ")
	public List<Incidence> getIncidenciasForOperario(Operario operario);
	
	  
	
}
