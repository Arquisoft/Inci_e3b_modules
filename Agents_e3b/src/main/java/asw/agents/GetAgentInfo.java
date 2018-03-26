package asw.agents;

import org.springframework.http.ResponseEntity;

import asw.agents.web_service.request.PeticionInfoREST;
import asw.agents.web_service.responses.RespuestaInfoREST;

public interface GetAgentInfo {

	public ResponseEntity<RespuestaInfoREST> getPOSTpetition(PeticionInfoREST peticion);

}
