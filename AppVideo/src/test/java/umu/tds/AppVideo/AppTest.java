package umu.tds.AppVideo;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import dominio.*;
import umu.tds.AppVideo.Persistencia.*;




/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) throws DAOException {
		FactoriaDAO dao=FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		IAdaptadorUsuarioDAO adaptadorUsuario=dao.getUsuarioDAO();
		IAdaptadorVideoDAO adaptadorVideo=dao.getVideoDAO();
		List<String> etiquetas=new LinkedList<String>();
		etiquetas.add("Musica");
		Usuario u1=new Usuario("Javi","Jimenez Hernandez","13/11/2001","javitenista46@gmail.com","chof16","12345678");
		Usuario u2=new Usuario("Pepe","Martinez Hernandez","13/11/2001","mskha46@gmail.com","manolo","ddd");
		Video v1=new Video("https://www.youtube.com/watch?v=l5TMjYj-8MQ","Becky G - Bella Ciao (Official Video)",etiquetas);
		Video v2=new Video("https://www.youtube.com/watch?v=zEf423kYfqk","Becky G, Natti Natasha - Sin Pijama (Official Video)",etiquetas);
		adaptadorUsuario.create(u1);
		adaptadorUsuario.create(u2);
		adaptadorUsuario.create(u2);
		adaptadorVideo.registrarVideo(v1);
		adaptadorVideo.registrarVideo(v2);
		adaptadorVideo.registrarVideo(v2);
		List<Usuario> usuarios=adaptadorUsuario.getAll();
		List<Video> videos=adaptadorVideo.recuperarTodosVideo();
		for (Usuario usuario : usuarios) {
			System.out.println("Usuario cod: "+usuario.getCodigo()+" -nombre: "+usuario.getNombre()+" -apellidos"
					+usuario.getApellidos()+" -nombreUsuario: "+usuario.getUsuario()+" -fecha nacimiento: "+(usuario.getFecha()));
		}
		
		for (Video video : videos) {
			System.out.println("URL: "+video.getUrl()+" Nombre: "+video.getTitiulo()+" Etiquetas: "+video.etiquetaToString());
		}
		
		adaptadorUsuario.delete(u1);
		adaptadorUsuario.delete(u2);
		adaptadorVideo.borrarVideo(v1);
		adaptadorVideo.borrarVideo(v2);
		
	}
	

}
