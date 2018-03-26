package asw.agents.web_service.request;

public class PeticionInfoREST {

	private String login;
	private String password;
	private String kind;

    public PeticionInfoREST(String login, String password, String kind) {
        this.login = login;
        this.password = password;
        this.kind = kind;
    }

    public PeticionInfoREST() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
