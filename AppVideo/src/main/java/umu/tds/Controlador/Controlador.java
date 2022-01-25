package umu.tds.Controlador;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import dominio.*;
import dominio.Video;
import umu.tds.AppVideo.Persistencia.*;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;

public class Controlador implements VideosListener {
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private TDSFactoriaDAO factoria;

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public void cargarVideos(File archivo_xml) {
		Videos vi=new Videos();
		vi.addVideosListene(getUnicaInstancia());
		vi.setArchivoVideos(archivo_xml);
	}

	@Override
	public void nuevosVideos(EventObject event) {
		//Si han llegado nuevos videos incluirlos en el catálogo de videos
		if(event instanceof VideosEvent){
		  VideosEvent ev=(VideosEvent)event;
		  CatalogoVideos.getUnicaInstancia().anadirVideos(ev.getVideos());
		}
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(nombre);
		if (usuario != null && usuario.getContrasena().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			String fechaNacimiento) throws ParseException {

		if (esUsuarioRegistrado(login))
			return false;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Usuario usuario = new Usuario(nombre, apellidos, formato.parse(fechaNacimiento),email, login, password);
		IAdaptadorUsuarioDAO usuarioDAO = factoria
				.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO. registrarUsuario(usuario);

		CatalogoUsuarios.getUnicaInstancia().addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getUsuario()))
			return false;

		IAdaptadorUsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.borrarUsuario(usuario);

		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}

	public LinkedList<Video> getVideos(String text) throws DAOException {
		LinkedList<Video> devolver=new LinkedList<Video>();
		List<Video> videos=CatalogoVideos.getUnicaInstancia().getVideos();
		for (Video video : videos) {
			if(video.getTitulo().contains(text)) devolver.add(video);
		}
		return devolver ;
	}
	
}
