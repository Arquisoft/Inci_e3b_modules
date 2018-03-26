package asw.agents.web_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import asw.db_management.GetAgent;
import asw.db_management.model.Agent;
import asw.agents.GetAgentInfo;
import asw.agents.util.Assert;
import asw.agents.web_service.request.PeticionInfoREST;
import asw.agents.web_service.responses.RespuestaInfoREST;
import asw.agents.web_service.responses.errors.ErrorResponse;

@RestController
public class GetAgentInfoRESTController implements GetAgentInfo {

	@Autowired
	private GetAgent getAgent;

	@Override
	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = { "Accept=application/json",
			"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<RespuestaInfoREST> getPOSTpetition(@RequestBody(required = true) PeticionInfoREST peticion) {

		Assert.isEmailEmpty(peticion.getLogin());
		Assert.isEmailValid(peticion.getLogin());
		Assert.isPasswordEmpty(peticion.getPassword());

		Agent agent = getAgent.getAgent(peticion.getLogin());

		Assert.isAgentNull(agent);

		Assert.isLoginCorrect(peticion.getPassword(), agent, peticion.getKind()); // comprueba password y kind


		/*
		 * Añadimos la información al modelo, para que se muestre en la pagina
		 * html: datosParticipant
		 */

		return new ResponseEntity<RespuestaInfoREST>(new RespuestaInfoREST(agent), HttpStatus.OK);
	}

	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse error) {
		return error.getMessageJSONFormat();
	}
}
