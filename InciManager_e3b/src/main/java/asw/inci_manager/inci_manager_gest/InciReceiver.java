package asw.inci_manager.inci_manager_gest;

import asw.inci_manager.inci_manager_gest.request.IncidenceREST;
import asw.inci_manager.inci_manager_gest.responses.RespuestaAddIncidenceREST;
import org.springframework.http.ResponseEntity;

public interface InciReceiver {

    public ResponseEntity<RespuestaAddIncidenceREST> addIncidence(IncidenceREST incidenceREST);
}
