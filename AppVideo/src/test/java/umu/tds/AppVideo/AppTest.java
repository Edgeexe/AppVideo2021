package umu.tds.AppVideo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.*;
import org.junit.Test;
import dominio.*;
import umu.tds.AppVideo.Persistencia.*;
import umu.tds.Controlador.Controlador;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private FactoriaDAO dao;
	private  IAdaptadorEtiquetaDAO adaptadorEtiqueta;
	private  IAdaptadorListaVideosDAO adaptadorListaVideos;
	private  IAdaptadorUsuarioDAO adaptadorUsuario;
	private  IAdaptadorVideoDAO adaptadorVideo;
	private Video videoRegistrado;
	private Video videoSinRegistrar;
	private Controlador controlador;
	private Etiqueta e1;
	
	@Before 
	public void prepararDatos() throws DAOException{
		dao=FactoriaDAO.getInstancia();
		adaptadorEtiqueta=dao.getEtiquetaDAO();
		adaptadorListaVideos=dao.getListaVideosDAO();
		adaptadorUsuario=dao.getUsuarioDAO();
		adaptadorVideo=dao.getVideoDAO();
		controlador=Controlador.getUnicaInstancia();
		
		
		List<Video> videos=adaptadorVideo.recuperarTodosVideo();
		videoRegistrado=videos.get(0);
		
		e1=new Etiqueta("Infantil");
		List<Etiqueta> etiquetas=new LinkedList<Etiqueta>();
		etiquetas.add(e1);
		videoSinRegistrar=new Video("https://www.youtube.com/watch?v=WiFb7IR7thU","Baby Yoda tomando un descanso",etiquetas);
	}
	
	@After 
	public void imprimirDatos() throws DAOException{
		List<Video> videos=adaptadorVideo.recuperarTodosVideo();
		for (Video video : videos) {
			System.out.println("Titulo: "+video.getTitulo()+
								"\nUrl: "+video.getUrl()+
								"\nNum Repro: "+video.getNumRepro()+
								"\nEtiquetas: "+video.EtiquetasToString()+"\n");
		}
		
		System.out.println("------------------------");
		
		List<Usuario> usuarios=adaptadorUsuario.getAll();
		for (Usuario usuario : usuarios) {
			List<Video> recientes=usuario.getRecientes();
			
			List<String> nombre=new LinkedList<String>();
			recientes.stream()
			.map(v->v.getTitulo())
			.forEach(t->nombre.add(t));
			
			List<ListaVideos> listas=usuario.getListasVideos();
			
			List<String> nombres=new LinkedList<String>();
			listas.stream()
				.map(l->l.getNombre())
				.forEach(n->nombres.add(n));
	
			
			System.out.println("Nombre: "+usuario.getNombre()+
					"\nApellidos: "+usuario.getApellidos()+
					"\nNombre Usuario: "+usuario.getUsuario()+
					"\nFecha Nacimiento: "+usuario.getFecha()+
					"\nRecientes: "+nombre.toString()+
					"\nListas: "+nombres.toString()+"\n");
		}
		
		System.out.println("------------------------");
		
		List<Etiqueta> etiquetas=adaptadorEtiqueta.recuperarTodasEtiquetas();
		for (Etiqueta etiqueta2 : etiquetas) {
			System.out.println("Etiqueta: "+etiqueta2.getEtiqueta());
		}
		
		System.out.println("------------------------");
		
		List<ListaVideos> listas=adaptadorListaVideos.recuperarTodasListasVideos();
		for (ListaVideos listaVideos : listas) {
			List<String> nombreVideos=new LinkedList<String>();
			listaVideos.getListaVideos().stream()
			.map(v->v.getTitulo())
			.forEach(n->nombreVideos.add(n));
			System.out.println("\nNombre Lista: "+listaVideos.getNombre()+
								"\nVideos: "+nombreVideos.toString());
		}
	}
	
	@Test
	public void test(){
		//Comprueban registrarUsuario y esUsuarioRegistrado
		assertFalse(controlador.esUsuarioRegistrado("jjh_15"));
		assertTrue(controlador.esUsuarioRegistrado("chof15"));
		
		Usuario usuarioRegistrado=adaptadorUsuario.getAll().get(1);
		controlador.setUsuarioActual(usuarioRegistrado);
		
		assertNotNull(controlador.buscarPlaylist("Prueba1"));
		assertNull(controlador.buscarPlaylist("NoExiste"));
		
		assertTrue(controlador.esVideoRegistrado(videoRegistrado.getUrl()));
		assertFalse(controlador.esVideoRegistrado(videoSinRegistrar.getUrl()));
		
		assertFalse(controlador.esEtiquetaRegistrada(e1));
		assertTrue(controlador.esEtiquetaRegistrada(new Etiqueta("Adulto")));
		
		
	}

}
