package asw.inci_manager.inci_manager_gest.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import asw.inci_manager.inci_manager_gest.entities.Agent;
import asw.inci_manager.inci_manager_gest.entities.Incidence;
import asw.inci_manager.inci_manager_gest.repositories.IncidenceRepository;
import asw.inci_manager.inci_manager_gest.request.IncidenceREST;
import asw.inci_manager.inci_manager_gest.responses.RespuestaAddIncidenceREST;
import asw.inci_manager.inci_manager_gest.responses.RespuestaFailedREST;
import asw.inci_manager.inci_manager_gest.responses.RespuestaREST;
import asw.inci_manager.kafka_manager.producers.KafkaProducer;

@Service
public class IncidenceService {

	private static final Logger logger = Logger.getLogger(IncidenceService.class);

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private IncidenceRepository incidenceRepository;

	public void send(Incidence incidence) {
		// ToDO: Incorporar un campo topic dinámico o incluirlo como propertie:
		kafkaProducer.send("topic", new Gson().toJson(incidence));
		logger.info("Sending incidence \"" + incidence.getIncidenceName() + "\" to topic '" + "topic" + "'");
	}

	public RespuestaREST send(IncidenceREST incidenceREST, Agent agent)
		{
			// ToDO: Incorporar un campo topic dinámico o incluirlo como propertie:
			if (agent != null && agent.getPassword().equals(incidenceREST.getPassword())&&incidenceREST.isCacheable()) {
				kafkaProducer.send("topic", new Gson().toJson(incidenceREST));
				logger.info("Sending incidence \"" + incidenceREST.getIncidenceName() + "\" to topic '" + "topic" + "'");
			return new RespuestaAddIncidenceREST(incidenceREST.getUsername(), incidenceREST.getPassword(),
					incidenceREST.getIncidenceName(), incidenceREST.getDescription(), incidenceREST.getLocation(),
					incidenceREST.getLabels(), incidenceREST.getCampos(), incidenceREST.getStatus(),
					incidenceREST.getExpiration(), incidenceREST.isCacheable());
			} else if (incidenceREST.isCacheable()){
				logger.info("Wrong authentication, incidence not sending");
				return new RespuestaFailedREST("Wrong authentication, incidence not sending");
			}else {
				logger.info("Not cacheable, incidence not sending");
				return new RespuestaFailedREST("Not cacheable, incidence not sending");
			}

	 	}

	/**
	 * Devuelve las incidencias de un agente pasado por parámetro
	 * 
	 * @param agent
	 *            del que quieres obtener las incidencias
	 * @return lista de incidencias
	 */
	public Set<Incidence> getIncidencesFromAgent(Agent agent) {
		return incidenceRepository.findIncidenceByAgent(agent);
	}

	/**
	 * Recibe una incidencia y la almacena en la base de datos
	 *
	 * @param incidence
	 *            incidencia a guardar en la base de datos
	 */
	public void addIncidence(Incidence incidence) {
		incidenceRepository.save(incidence);
	}

	/**
	 * Retorna una incidencia buscando el id por parámetro
	 * 
	 * @param id,
	 *            incidencia a buscar
	 * @return la incidencia buscada
	 */
	public Incidence getIncidenceById(Long id) {
		return incidenceRepository.findIncidenceById(id);
	}

	/**
	 * Recibe del formulario un String de incidencias separadas por comas y lo
	 * devuelve como un hashset de strings
	 * 
	 * @param label
	 *            String de incidencias separadas por comas
	 * @return HashSet de Strings
	 */
	public Set<String> labelsParser(String label) {
		String[] etiquetas = label.split(",");
		Set<String> labels = new HashSet<String>();
		for (String string : etiquetas) {
			labels.add(string);
		}
		return labels;
	}

	/**
	 * Recibe de formulario un String con la forma campoA : valordelcampoA ;
	 * campoB: valordelcampoB ;campoC : valordelcampoC ;
	 * Luego, lo separa por ";"
	 * Y lo mete en un mapa después de separar por ":"
	 * @param fields String de campos
	 * @return HashMap con el resultado
	 */
	public HashMap<String, String> fielsParser(String fields) {
		HashMap<String, String> mapa = new HashMap<>();
		String[] pares = fields.split(";");
		for (String string : pares) {
			String[] valores = string.split(":");
			mapa.put(valores[0], valores[1]);
		}
		return mapa;
	}
}
