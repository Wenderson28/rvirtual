package interceptores;

import controllers.Application;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
	
	@Before(unless= {"Application.login", "Login.autenticar"})
	public static void checarAutenticacao() {
		if(session.get("matriculaUsuarioAutenticado") == null) {
			Application.login();			
		}
	}
}
