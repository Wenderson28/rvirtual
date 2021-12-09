package controllers;

import enums.TipoUsuario;
import models.Seac;
import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{

	public static void login() {
		render();
	}
 
		
    public static void autenticar(String matricula, String senha) {
    
    	Usuario usuario = Usuario.find("matricula=? and senha=?", matricula, senha).first();
    		
    	
    	
    	if(usuario == null) {
    		
    		flash.error("Login ou senha incorretos, tente novamente!");
    		Application.login();
    	}else
    	
    		session.put("nomeUsuario", usuario.nome);
    		session.put("matriculaUsuario", usuario.matricula);
    		session.put("senhaUsuario", usuario.senha);
    		session.put("UsuarioID", usuario.id);
    		session.put("UsuarioCurso", usuario.curso);
    		
    		if(usuario.tipoUser.equals(TipoUsuario.ALUNO)) {
    			Alunos.inicio() ;
    		}else if(usuario.tipoUser.equals(TipoUsuario.PROFESSOR)) {
    			Professores.inicio();
    		}else if(usuario.tipoUser.equals(TipoUsuario.SEAC)) {
    			Seacs.inicio();
    			
    		}
    		
    	}	
    	
 
  	
    
    public static void logout() {
    	session.clear();
    	Application.login();
    }
    
}