package jobs;

import controllers.Usuarios;
import enums.TipoUsuario;

import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;


@OnApplicationStart
public class Inicializador extends Job {
	@Override
	public void doJob() throws Exception {
		 if(Usuario.count() == 0) {
			Usuario usuario1 = new Usuario();
			usuario1.nome = "SEAC";
			usuario1.matricula = "2018107";
			usuario1.senha= "123";
			usuario1.tipoUser= TipoUsuario.SEAC;
			usuario1.save();
		}
		
	}

}