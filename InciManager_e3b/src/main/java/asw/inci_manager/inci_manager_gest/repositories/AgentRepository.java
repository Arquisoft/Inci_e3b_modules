package asw.inci_manager.inci_manager_gest.repositories;

import asw.inci_manager.inci_manager_gest.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    /**
     * Método que devuelve el Participante el cual es buscado por email
     * en la base de datos
     * @param email del agente
     * @return El Participante con dicho email
     */
    Agent findByEmail(String email);

    /**
     *
     * @param email
     * @return
     */
    @Query("select u from Agent u where (LOWER(u.email) like lower(?1))")
    Agent findByEmailFlexible(String email);
}
