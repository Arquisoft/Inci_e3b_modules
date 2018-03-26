package inciDashboard.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Agent;
import inciDashboard.entities.Incidence;
import inciDashboard.entities.Operario;

@Service
public class InsertSampleDataService {
	
	@Autowired
	OperariosService operariosService;
	
	@Autowired
	AgentsService agentsService;
	
	@Autowired
	IncidenciasService incidenciasService;
	
	@Autowired
	RolesService rolesService;
	
	
	@PostConstruct
	public void init() {
		Operario oper1 = new Operario( "oper12@gmail.es","123456","Operario");
		oper1.setRole(rolesService.getRoles()[0]);
		Agent ag1 = new Agent("Agent1","123456","Norte","agent121223221@gmail.com","Ag111223221","Person");
		Agent ag2 = new Agent("Agent2","123456","Sur","agent221223221@gmail.com","Ag221223221","Sensor");
		Agent ag3 = new Agent("Agent3","123456","Este","agent321223221@gmail.com","Ag331223221","Entity");
		agentsService.addAgent(ag1);
		agentsService.addAgent(ag2);
		agentsService.addAgent(ag3);
		Incidence inci1 = new Incidence(ag1,"Incidencia1","Descripcion1","21,33",null);
		Incidence inci2 = new Incidence(ag2,"Incidencia2","Descripcion2","60,43",null);
		Incidence inci3 = new Incidence(ag3,"Incidencia3","Descripcion3","-8,-99",null);
		Incidence inciPeligrosa = new Incidence(ag3,"Incidencia3","Peligro","-8,-99",null);
		inciPeligrosa.setOperario(oper1);
		inci1.setOperario(oper1);
		inci2.setOperario(oper1);
		inci3.setOperario(oper1);
		oper1.añadirIncidencia(inciPeligrosa);
		oper1.añadirIncidencia(inci1);
		oper1.añadirIncidencia(inci2);
		oper1.añadirIncidencia(inci3);
		operariosService.addOperador(oper1);		
	}
	
}
