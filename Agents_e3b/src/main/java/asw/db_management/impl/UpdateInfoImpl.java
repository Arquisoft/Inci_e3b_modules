package asw.db_management.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.db_management.UpdateInfo;
import asw.db_management.model.Agent;
import asw.db_management.repository.AgentRepository;

@Service
public class UpdateInfoImpl implements UpdateInfo {

	private AgentRepository repository;
	
	@Autowired
	public UpdateInfoImpl(AgentRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * Método que permite la actualización de la contraseña del Participante
	 * Se comprueba que las contraseñas no estén vacías, sean distintas y 
	 * la actual coincida con la del participante
	 */
	@Override
	public void updatePassword(Agent agent, String password, String newPassword) {
		
		if (password != null && newPassword != null && !(password.equals(newPassword))
				&& agent.getPassword().equals(password)) {
			agent.setPassword(newPassword);
			this.repository.save(agent);
		}
		
	}

	/**
	 * Método que permite la actualización del email del Participante
	 * Se comprueba que el email no esté vacío
	 */
	@Override
	public void updateEmail(Agent agent, String email) {
		if(email != null){
			agent.setEmail(email);
			this.repository.save(agent);
		}
	}

}
