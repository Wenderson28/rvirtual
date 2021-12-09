package controllers;

import java.util.Arrays;
import java.util.List;

import enums.TipoUsuario;
import models.Requerimento;
import models.Seac;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.MaxSize;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.Controller;

public class Usuarios extends Controller {
	 
	public static void cadastro() {
		Usuario usuario = (Usuario) Cache.get("usuario");
		Cache.clear();	
		List<TipoUsuario> tiposUser = Arrays.asList(TipoUsuario.PROFESSOR, TipoUsuario.ALUNO);
		
		render(tiposUser, usuario); 	
	}
	
	public static void salvar(Usuario usuario, String senha){
		
			if(senha.equals("") == false) {
				usuario.senha = senha;
				
			}
			validation.valid(usuario);
			
			if(validation.hasErrors()){
				validation.keep();
				
				flash.error("Cadastro Inválido!");
				Cache.set("usuario", usuario);
				
				cadastro();
			}
			
			
				
		usuario.save();
		flash.success("Cadastro Concluido!");
		render("Application/login.html");
	}
			
	
}