package controllers;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import enums.TipoUsuario;
import models.Professor;
import models.Requerimento;
import models.Seac;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Professores extends Controller{
	
	public static void inicio(){
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	public static void sugestao(){
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	public static void contato(){
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	public static void saveSugestao(Usuario usuario){	
					
	usuario.save();
	flash.success("Agradecemos pela sugestão!");
	sugestao();
	}
	
	//Editar perfil------------------------------------------
	public static void meuperfil(){
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	
	public static void editar(Long id) {
		List<Usuario> usuarios = Usuario.findAll();
		Usuario usuario = Usuario.findById(id);		
		renderTemplate("Professores/cadEdit.html", usuario, usuarios);
		
	}
	
	public static void cadEdit() {
		Usuario usuario = (Usuario) Cache.get("usuario");	
		Cache.clear();	
		render(usuario);
	
	}
	
	public static void salve(@Valid Usuario usuario, File foto) {
		if(foto != null){
			foto.renameTo(new File("./uploads/" + foto.getName()));
			usuario.fotoperfil = foto.getName();
			flash.success("Requerimento Anexado!");
			usuario.save();
			meuperfil();
		}
			
		if(validation.hasErrors()){
			validation.keep();		
			flash.error("Cadastro Inválido!");
			Cache.set("usuario", usuario);		
			cadEdit();
		}		
		usuario.save();
		flash.success("Dados atualizados com sucesso!");
		meuperfil();
	}
	
	//----------------------------------------------------------------
	
	
	public static void salvar(@Valid Requerimento requerimento, File foto, File foto2) {
		
		if(foto != null && foto2 != null) {
			foto.renameTo(new File("./uploads/" + foto.getName()));
			requerimento.requerimento_anexado = foto.getName();
			foto2.renameTo(new File("./uploads2/" + foto2.getName()));
			requerimento.requerimento_anexado2 = foto2.getName();
			requerimento.save();
			listarEnc(null);
		} else if(foto != null){
			foto.renameTo(new File("./uploads/" + foto.getName()));
			requerimento.requerimento_anexado = foto.getName();
			requerimento.save();
			listarEnc(null);
		} else if(foto2 != null) {
			foto2.renameTo(new File("./uploads2/" + foto2.getName()));
			requerimento.requerimento_anexado2 = foto2.getName();
			requerimento.save();	
			listarEnc(null);
		}
		if (validation.hasErrors()){
			validation.keep();
			
			flash.error("Cadastro Inválido!");
			Cache.set("requerimento", requerimento);				
			listarEnc(null);
			
		}		
		
		requerimento.save();
		flash.success("Requerimento Processado!");
		listarEnc(null);
	}
	
	public static void listarEnc(String busca) {
		Requerimento requerimento = (Requerimento) Cache.get("requerimento");
		Cache.clear();	

		List<Requerimento> requerimentos;
		List<Usuario> usuarios = Usuario.findAll();
			if(busca == null) {
				requerimentos = Requerimento.findAll();
			} else {
				requerimentos = Requerimento.find("byMatriculaLike", "%"+busca+"%").fetch();
				
			}
	
		render(requerimentos, usuarios);
		}
	
	public static void encReq(String busca) {
		List<Usuario> usuarios = Usuario.findAll();
		
		List<Requerimento> requerimentos;
		if(busca == null) {
			requerimentos = Requerimento.findAll();
		} else {
			requerimentos = Requerimento.find("byMatriculaLike", "%"+busca+"%").fetch();
			
		}
	render(requerimentos, usuarios);
	}
	
	public static void finalizados() {
		Usuario usuario = (Usuario) Cache.get("usuario");

		
		Cache.clear();	
		render(usuario);
	}
	
	public static void edit3(Long id) {
		List<Usuario> usuarios = Usuario.findAll();
		List<Requerimento> requerimentos = Requerimento.findAll();
		Requerimento requerimento = Requerimento.findById(id);		
		renderTemplate("Professores/finalizados.html", usuarios, requerimento, requerimentos);
		
	}
	public static void abrirFinalizados() {
		Usuario usuario = (Usuario) Cache.get("usuario");

		
		Cache.clear();	
		render(usuario);
	}
	
	public static void edit(Long id) {
		List<Usuario> usuarios = Usuario.findAll();
		List<Requerimento> requerimentos = Requerimento.findAll();
		Requerimento requerimento = Requerimento.findById(id);		
		renderTemplate("Professores/abrirFinalizados.html", usuarios, requerimento, requerimentos);
		
	}
	
}
