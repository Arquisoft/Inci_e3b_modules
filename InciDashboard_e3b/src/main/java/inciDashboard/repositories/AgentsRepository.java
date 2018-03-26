package inciDashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Agent;

public interface AgentsRepository extends CrudRepository<Agent,Long>{

}
