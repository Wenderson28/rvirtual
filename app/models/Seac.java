package models;


import javax.persistence.Entity;

import enums.TipoUsuario;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Seac extends Model{

	@Required
	public String nome;
	@Required
	public String matricula;
	
	public String requerimento;
		
}

