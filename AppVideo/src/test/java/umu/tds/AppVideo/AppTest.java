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
		IAdaptadorListaVideosDAO adaptadorLista=dao.getListaVideosDAO();
		List<String> etiquetas=new LinkedList<String>();
		etiquetas.add("Musica");
		Usuario u1=new Usuario("Javi","Jimenez Hernandez","13/11/2001","javitenista46@gmail.com","1","1");
		Video v1=new Video("https://www.youtube.com/watch?v=l5TMjYj-8MQ","Becky G - Bella Ciao (Official Video)",etiquetas);
		Video v2=new Video("https://www.youtube.com/watch?v=zEf423kYfqk","Becky G, Natti Natasha - Sin Pijama (Official Video)",etiquetas);
		ListaVideos lista1=new ListaVideos("lista_prueba",v1,v2);
		List<ListaVideos> lisu1=u1.getListasVideos();
		lisu1.add(lista1);
		u1.setListasVideos(lisu1);
		List<Video> rec=new LinkedList<Video>();
		rec.add(v1);
		rec.add(v2);
		u1.setRecientes(rec);
		adaptadorUsuario.create(u1);
		Usuario usuario=adaptadorUsuario.get(u1.getCodigo());
		adaptadorVideo.registrarVideo(v2);
		adaptadorVideo.registrarVideo(v1);
		
		for(Video video : adaptadorVideo.recuperarTodosVideo()) {
			System.out.println(video.getTitulo());
		}
		/*adaptadorUsuario.delete(u1);
		adaptadorVideo.borrarVideo(v1);
		adaptadorVideo.borrarVideo(v2);
		adaptadorLista.borrarListaVideos(lista1);*/
	
	}
}
