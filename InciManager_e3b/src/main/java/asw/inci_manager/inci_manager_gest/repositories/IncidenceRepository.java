package asw.inci_manager.inci_manager_gest.repositories;

import asw.inci_manager.inci_manager_gest.entities.Agent;
import asw.inci_manager.inci_manager_gest.entities.Incidence;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface IncidenceRepository extends CrudRepository<Incidence, Long> {

    Set<Incidence> findIncidenceByAgent(Agent agent);
    Incidence findIncidenceById(Long id);

}
