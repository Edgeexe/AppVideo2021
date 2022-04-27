package umu.tds.AppVideo;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import dominio.*;
import umu.tds.AppVideo.Persistencia.*;
import umu.tds.Controlador.Controlador;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) throws DAOException {
		FactoriaDAO dao=FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		IAdaptadorUsuarioDAO adaptadorUsuario=dao.getUsuarioDAO();
		List<String> etiquetas=new LinkedList<String>();
		etiquetas.add("Musica");
		//Video v1=new Video("https://www.youtube.com/watch?v=wM8qN_nuF3M","KAROL G - PROVENZA (Official Video)",etiquetas);
		
		for(Usuario video : adaptadorUsuario.getAll()) {
			adaptadorUsuario.delete(video);
		}
	
	}
}
