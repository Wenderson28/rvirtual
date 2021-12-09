package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Min;


import enums.TipoUsuario;
import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;
import play.mvc.Scope.Flash;

@Entity
public class Requerimento extends Model{
	
	
	
	//Dados
	public String nome;
	@Required
	public String matricula;
	
	public String curso;
	//--------------------
	//Informações
	@Required
	public String motivo;
	public String outro;
	public String requerimento_anexado;
	
	
	public String textarea;
	private String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	
	//Informações(2)
	public String motivo2;
	public String outro2;
	public String requerimento_anexado2;
	
	public String textarea2;
	
	
	//- infor justificativa de falta
	public String data;
	public String nomeTipo;
	public String nomeDisc;
	public String nomeProf;
	public String data2;
	public String nomeTipo2;
	public String nomeDisc2;
	public String nomeProf2;
	
	//-infor justificativa de falta (2)
	public String data3;
	public String nomeTipo3;
	public String nomeDisc3;
	public String nomeProf3;
	public String data4;
	public String nomeTipo4;
	public String nomeDisc4;
	public String nomeProf4;
	
	//outros
	public String sit;	
	
	public String matriculaProf;
	public String nomeProfessor;
	
	
	
	
}
