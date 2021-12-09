package controllers;


import java.io.File;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.constraints.Min;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import enums.TipoUsuario;
import models.Aluno;
import models.Professor;
import models.Requerimento;
import models.Seac;
import models.Usuario;
import play.cache.Cache;

import play.data.validation.Required;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.Controller;

public class Alunos extends Controller{

	public static void inicio() {
		
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}	
	
	public static void meuperfil(){
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	
	public static void editar(Long id) {
		List<Usuario> usuarios = Usuario.findAll();
		Usuario usuario = Usuario.findById(id);		
		renderTemplate("Alunos/cadEdit.html", usuario, usuarios);
		
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
	public static void newReq() {
		Requerimento requerimento = (Requerimento) Cache.get("requerimento");
		List<Usuario> usuarios = Usuario.findAll();
		Cache.clear();
		render(requerimento, usuarios);
	}
	
	public static void salvar(@Valid Requerimento requerimento, File foto, File foto2) {
		
		if(foto != null && foto2 != null) {
			foto.renameTo(new File("./uploads/" + foto.getName()));
			requerimento.requerimento_anexado = foto.getName();
			foto2.renameTo(new File("./uploads2/" + foto2.getName()));
			requerimento.requerimento_anexado2 = foto2.getName();
			flash.success("Requerimento Anexado!");
			requerimento.save();
			newReq();
		} else if(foto != null){
			foto.renameTo(new File("./uploads/" + foto.getName()));
			requerimento.requerimento_anexado = foto.getName();
			flash.success("Requerimento Anexado!");
			requerimento.save();
			newReq();
		} else if(foto2 != null) {
			foto2.renameTo(new File("./uploads2/" + foto2.getName()));
			requerimento.requerimento_anexado2 = foto2.getName();
			flash.success("Requerimento Anexado!");
			requerimento.save();	
			newReq();
		}
		
		
		
		if (validation.hasErrors()){
			validation.keep();
			
			flash.error("Preenchimento Inválido!");
			Cache.set("requerimento", requerimento);				
			newReq();
			
		}		
		
		requerimento.save();
		flash.success("Requerimento Anexado!");
		newReq();
	}

	public static void sitReq(String busca, String matricula, Requerimento requerimento) {	
		List<Usuario> usuarios = Usuario.findAll();
		Usuario usuario = Usuario.find("matricula=?", matricula).first();
		
		List<Requerimento> requerimentos;
			if(busca == null) {
				requerimentos = Requerimento.findAll();
			} else {
				requerimentos = Requerimento.find("byMotivoLike", "%"+busca+"%").fetch();
				
			}
						
		
			
			render(requerimentos, usuarios, usuario);
				
		
		}
	public static void sitReq_Ana(String busca, String matricula) {	
		List<Usuario> usuarios = Usuario.findAll();
		Usuario usuario = Usuario.find("matricula=?", matricula).first();
		List<Requerimento> requerimentos;
			if(busca == null) {
				requerimentos = Requerimento.findAll();
			} else {
				requerimentos = Requerimento.find("byMotivoLike", "%"+busca+"%").fetch();
				
			}
		render(requerimentos, usuarios, usuario);
		}
	
		public static void Contato(){
			List<Usuario> usuarios = Usuario.findAll();
			render(usuarios);
		}
		public static void sugestao(){
			List<Usuario> usuarios = Usuario.findAll();
			render(usuarios);
			
			
		}
		public static void saveSugestao(Usuario usuario){	
		
		usuario.save();
		flash.success("Agradecemos pela sugestão!");
		sugestao();
		}
		
		public static void finalizados() {
			Usuario usuario = (Usuario) Cache.get("usuario");

			
			Cache.clear();	
			render(usuario);
		}
		
		public static void edit2(Long id) {
			List<Usuario> usuarios = Usuario.findAll();
			List<Requerimento> requerimentos = Requerimento.findAll();
			Requerimento requerimento = Requerimento.findById(id);		
			renderTemplate("Alunos/finalizados.html", usuarios, requerimento, requerimentos);
			
		}
	
		/*
		List<Requerimento> requerimentos;
		requerimentos = Requerimento.findAll();
		render(requerimentos);
		*/
	
}
	/*
	public void newReq() {
		render();
	}
	
	
	public static void listar() {
		List<Requerimento> requerimentos = Requerimento.findAll();
		render(requerimentos);
	}
}
*/
	/*
	public static void renderPDFAluno(Long idAluno) {
		Aluno aluno = Aluno.findById(4);
		response.setContentTypeIfNotSet(aluno.PDF.type());
		renderBinary(aluno.PDF.get());
	}


*/	



