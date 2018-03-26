package asw.inci_manager.inci_manager_gest.responses;

public class RespuestaFailedREST implements RespuestaREST{

	private String mensaje; // mensaje de error

	public RespuestaFailedREST(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


}
