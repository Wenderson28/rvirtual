package models;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Professor extends Model{

	@Required
	public String nome_prof;
	@Required
	public String numero_pro;
		
	public String requerimento_prof;
	
	@Required
	public String situacao;
	@Required
	public String numero;
	
	public String requerimento;
	
}
