package inciDashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Agent;
import inciDashboard.repositories.AgentsRepository;

@Service
public class AgentsService {
	
	@Autowired
	AgentsRepository agentsRepository;
	
	public void addAgent(Agent agent) {
		agentsRepository.save(agent);
	}

}
