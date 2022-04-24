package umu.tds.Controlador;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import dominio.*;
import umu.tds.AppVideo.Persistencia.*;
import umu.tds.componente.CargadorVideos;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;

public class Controlador implements VideosListener {
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoVideos catalogoVideos;
	private CargadorVideos cargador;
	
	private Usuario usuarioActual;
	
	private static Controlador unicaInstancia;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorVideoDAO adaptadorVideo;

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new Controlador();
		return unicaInstancia;
	}
	
	public Controlador() {
		cargador=new CargadorVideos();
		cargador.addVideosListene(this);
		inicilaizarAdaptadores();
		inicializarCatalogos();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios=CatalogoUsuarios.getUnicaInstancia();
		catalogoVideos=CatalogoVideos.getUnicaInstancia();
	}

	private void inicilaizarAdaptadores() {
		FactoriaDAO factoria=null;
		try {
			factoria=FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch(DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario=factoria.getUsuarioDAO();
		adaptadorVideo=factoria.getVideoDAO();
	}

	public void cargarVideos(File archivo_xml) {
		cargador.setArchivoVideos(archivo_xml);
	}

	@Override
	public void nuevosVideos(EventObject event) {
		//Si han llegado nuevos videos incluirlos en el catálogo de videos
		if(event instanceof VideosEvent){
		  VideosEvent ev=(VideosEvent)event;
		  registrarVideos(ev.getVideos());
		}
	}
	
	private void registrarVideos(Videos videos) {
		for (umu.tds.componente.Video video : videos.getVideo()) {
			if(!esVideoRegistrado(video.getURL())) {
				Video vi=new Video(video.getURL(),video.getTitulo(),video.getEtiqueta());
				registrarVideo(vi);	
			}
		}
	}

	private void registrarVideo(Video video) {
		if(!esVideoRegistrado(video.getUrl())) {
			adaptadorVideo.registrarVideo(video);
			catalogoVideos.addVideo(video);			
		}
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String login) {
		return catalogoUsuarios.getUsuario(login) != null;
	}
	
	public boolean esVideoRegistrado(String url) {
		return catalogoVideos.getVideo(url) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombre);
		if (usuario != null && usuario.getContrasena().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,String fechaNacimiento) {
		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos,fechaNacimiento,email, login, password);
		adaptadorUsuario.create(usuario);
		catalogoUsuarios.addUsuario(usuario);
		return true;
	}

	/*public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getUsuario()))
			return false;
		adaptadorUsuario.borrarUsuario(usuario);
		CatalogoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}*/

	public List<Video> getVideos(String titulo) throws DAOException {
			List<Video> listaVideos = catalogoVideos.getVideos().stream()
				.filter(vid -> vid.getTitulo().contains(titulo))
				.collect(Collectors.toList());
			return listaVideos;
	}
	
	public ArrayList<Video> getVideos() throws DAOException {
		ArrayList<Video> devolver=new ArrayList<Video>();
		List<Video> videos= catalogoVideos.getVideos();
		for (Video video : videos) {
			devolver.add(video);
		}
		return devolver ;
	}

	public ListaVideos buscarPlaylist(String playlist) {
		return getUnicaInstancia().getUsuarioActual().tienePlaylist(playlist);
	}
	
	public void anadirPlaylist(ListaVideos playlist) {
		getUnicaInstancia().getUsuarioActual().addListaVideos(playlist);
	}

	public void eliminarPlaylist(ListaVideos playlist) {
		getUnicaInstancia().getUsuarioActual().borrarListaVideos(playlist);
	}
	
	public void actualizarPlaylist(ListaVideos antigua, ListaVideos nueva) {
		getUnicaInstancia().getUsuarioActual().actualizarListaVideos(antigua,nueva);
	}
	
}
