package controllers;


import java.util.Arrays;
import java.util.List;

import java.io.File;


import enums.TipoUsuario;
import models.Professor;
import models.Requerimento;
import models.Seac;

import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Seacs extends Controller{

	public static void inicio(){
		render();
	}
	
	
	public static void form() {
		render();
	}
	public static void cadastro() {
		render();
	}
	
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		List<TipoUsuario> tiposUser = Arrays.asList(TipoUsuario.PROFESSOR, TipoUsuario.ALUNO);
		renderTemplate("Seacs/cadEdit.html", usuario, tiposUser);
		
	}
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listar();
	}
	public static void cadEdit() {
		Usuario usuario = (Usuario) Cache.get("usuario");
		Cache.clear();	
		List<TipoUsuario> tiposUser = Arrays.asList(TipoUsuario.PROFESSOR, TipoUsuario.ALUNO);
		render(tiposUser, usuario);
	}
	public static void cadSalvar(Usuario usuario, String senha) {
		if(senha.equals("") == false) {
			usuario.senha = senha;			
		}
		validation.valid(usuario);
		
		if(validation.hasErrors()){
			validation.keep();
			
			flash.error("Cadastro Inválido!");
			Cache.set("usuario", usuario);
			
			cadEdit();
		}
			
		usuario.save();
		flash.success("Cadastro Concluido!");
		listar();
	}
	
	
	public static void salvar(@Valid Requerimento requerimento, File foto, File foto2) {
		
		if(foto != null && foto2 != null) {
			foto.renameTo(new File("./uploads/" + foto.getName()));
			requerimento.requerimento_anexado = foto.getName();
			foto2.renameTo(new File("./uploads2/" + foto2.getName()));
			requerimento.requerimento_anexado2 = foto2.getName();
			requerimento.save();
			listar_Requerimento(null);
		} else if(foto != null){
			foto.renameTo(new File("./uploads/" + foto.getName()));
			requerimento.requerimento_anexado = foto.getName();
			requerimento.save();
			listar_Requerimento(null);
		} else if(foto2 != null) {
			foto2.renameTo(new File("./uploads2/" + foto2.getName()));
			requerimento.requerimento_anexado2 = foto2.getName();
			requerimento.save();	
			listar_Requerimento(null);
		}
	
		
		if (validation.hasErrors()){
			validation.keep();
			
			flash.error("Cadastro Inválido!");
			Cache.set("requerimento", requerimento);				
			listar_Requerimento(null);
			
		}		
		
		
		if(requerimento.sit != null && requerimento.matriculaProf == null) {
			flash.success("Requerimento Processado!");
			requerimento.save();	
		}
		if(requerimento.sit == null && requerimento.matriculaProf != null) {
			flash.success("Requerimento Processado!");
			requerimento.save();
		}
		if(requerimento.sit == null && requerimento.matriculaProf == null) {
			flash.error("Processamento inválido! (selecione uma opção)");
			listar_Requerimento(null);
		}
		if(requerimento.matriculaProf != null && requerimento.sit != null) {		
			flash.error("Processamento inválido! (selecione apenas uma opção)");
							
			listar_Requerimento(null);
		}
		
		listar_Requerimento(null);
	}
	
	
	public static void listar_Requerimento(String busca) {
		Requerimento requerimento = (Requerimento) Cache.get("requerimento");
		Cache.clear();	

		List<Requerimento> requerimentos;
		List<Usuario> usuarios = Usuario.findAll();
		List<TipoUsuario> tiposUser = Arrays.asList(TipoUsuario.PROFESSOR);
			if(busca == null) {
				requerimentos = Requerimento.findAll();
			} else {
				requerimentos = Requerimento.find("byMotivoLike", "%"+busca+"%").fetch();
				
			}
	
		render(requerimentos, usuarios);
		}
	public static void analisar() {
		Usuario usuario = (Usuario) Cache.get("usuario");

		
		Cache.clear();	
		render(usuario);
	}
	
	public static void edit(Long id) {
		List<Usuario> usuarios = Usuario.findAll();
		List<Requerimento> requerimentos = Requerimento.findAll();
		Requerimento requerimento = Requerimento.findById(id);		
		renderTemplate("Seacs/analisar.html", usuarios, requerimento, requerimentos);
		
	}
	
	public static void abrirReqEnc() {
		Usuario usuario = (Usuario) Cache.get("usuario");

		
		Cache.clear();	
		render(usuario);
	}
	
	public static void edit2(Long id) {
		List<Usuario> usuarios = Usuario.findAll();
		List<Requerimento> requerimentos = Requerimento.findAll();
		Requerimento requerimento = Requerimento.findById(id);		
		renderTemplate("Seacs/abrirReqEnc.html", usuarios, requerimento, requerimentos);
		
	}
	
	
	
	
	
	
	public static void encReq(String busca) {	
		List<Usuario> usuarios = Usuario.findAll();
		List<Requerimento> requerimentos;
			if(busca == null) {
				requerimentos = Requerimento.findAll();
			} else {
				requerimentos = Requerimento.find("byMatriculaProfLike", "%"+busca+"%").fetch();
				
			}
			
			render(requerimentos, usuarios);
		
		}
	
	
	
	public static void listarReq_Def(String busca) {			
		List<Requerimento> requerimentos;
			if(busca == null) {
				requerimentos = Requerimento.findAll();
			} else {
				requerimentos = Requerimento.find("byMotivoLike", "%"+busca+"%").fetch();
				
			}
		
			render(requerimentos);
	}
	
	
	public static void sugestao() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	
	public static void Contato() {
		render();
	}
		
	
}

