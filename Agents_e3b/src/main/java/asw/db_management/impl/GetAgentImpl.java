package asw.db_management.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.db_management.GetAgent;
import asw.db_management.model.Agent;
import asw.db_management.repository.AgentRepository;


@Service
public class GetAgentImpl implements GetAgent {
	
	private AgentRepository repository;
	
	@Autowired
	public GetAgentImpl(AgentRepository repository) {
		this.repository = repository;
	}
	
	
	/**
	 * Método que devuelve el Participante buscado por email
	 * Hace uso del método findByEmail (mapeador)
	 */
	@Override
	public Agent getAgent(String email) {
		
		return this.repository.findByEmail(email);
	}

}
