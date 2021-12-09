package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import enums.TipoUsuario;
import play.data.validation.Equals;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model{
	
	
	@MinSize(10)
	@Required
	public String nome;
	
	@MinSize(6)
	@MaxSize(15)
	@Unique
	@Required	
	public String matricula;
	
	
	@Required
	@Equals("confirmacaoSenha")
	public String senha;
	
	@Transient
	public String confirmacaoSenha;
	
	public String fotoperfil;
	
	public String curso;
	
	public String sugestao;
	
	public void setConfirmacaoSenha(String senha) {
		
			confirmacaoSenha = senha;
		
	}
	
	
	
	@OneToMany
	@JoinColumn(name="NOME_id")
	public List<Seac> seacs;
	
	
	@Required
	public TipoUsuario tipoUser;
	public Usuario() {
		tipoUser = TipoUsuario.ALUNO;	
		tipoUser = TipoUsuario.PROFESSOR;
		tipoUser = TipoUsuario.SEAC;
		
	}
	

	
}