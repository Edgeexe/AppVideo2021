package umu.tds.Controlador;

import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import dominio.*;
import umu.tds.AppVideo.Persistencia.*;
import umu.tds.componente.CargadorVideos;
import umu.tds.componente.Videos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Controlador implements VideosListener {
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoVideos catalogoVideos;
	private CatalogoEtiquetas catalogoEtiquetas;
	private CargadorVideos cargador;
	
	private Usuario usuarioActual;
	
	private static Controlador unicaInstancia;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorListaVideosDAO adaptadorListas;
	private IAdaptadorEtiquetaDAO adaptadorEtiqueta;

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

	//Función para inicializar los catalogos 
	private void inicializarCatalogos() {
		catalogoVideos=CatalogoVideos.getUnicaInstancia();
		catalogoUsuarios=CatalogoUsuarios.getUnicaInstancia();
		catalogoEtiquetas=CatalogoEtiquetas.getUnicaInstancia();
	}

	//funcion para inicializar los adaptadores del servicio de persistencia
	private void inicilaizarAdaptadores() {
		FactoriaDAO factoria=null;
		try {
			factoria=FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch(DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario=factoria.getUsuarioDAO();
		adaptadorVideo=factoria.getVideoDAO();
		adaptadorListas=factoria.getListaVideosDAO();
		adaptadorEtiqueta=factoria.getEtiquetaDAO();
	}

	//función para que el componenete cargador cree los videos a añadir
	public void cargarVideos(File archivo_xml) {
		cargador.setArchivoVideos(archivo_xml);
	}

	//Función donde se recibe los nuevos videos a añadir
	@Override
	public void nuevosVideos(EventObject event) {
		//Si han llegado nuevos videos incluirlos en el catálogo de videos
		if(event instanceof VideosEvent){
		  VideosEvent ev=(VideosEvent)event;
		  registrarVideos(ev.getVideos());
		}
	}
	
	//Función para añadir los videos si no estaban prevamente registrados
	private void registrarVideos(Videos videos) {
		for (umu.tds.componente.Video video : videos.getVideo()) {
			if(!esVideoRegistrado(video.getURL())) {
				List<Etiqueta> etiquetas=new LinkedList<Etiqueta>();
				for (String e : video.getEtiqueta()) {
					etiquetas.add(new Etiqueta(e));
				}
				Video vi=new Video(video.getURL(),video.getTitulo(),etiquetas);
				registrarVideo(vi);
				for (Etiqueta e : vi.getEtiquetas()) {
					if(!esEtiquetaRegistrada(e)) {
						adaptadorEtiqueta.registrarEtiqueta(e);
						catalogoEtiquetas.addEtiqueta(e);
					}
				}
			}
		}
	}

	//Funcion para registrar el video tanto en la persistencia como ene el catalogo
	public void registrarVideo(Video video) {
		if(!esVideoRegistrado(video.getUrl())) {
			adaptadorVideo.registrarVideo(video);
			catalogoVideos.addVideo(video);			
		}
	}

	//funcion para obtener el usuario actual
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	//Funcion para saber si el usuario esta registrado
	public boolean esUsuarioRegistrado(String login) {
		return catalogoUsuarios.getUsuario(login) != null;
	}
	
	//Funcion para saber si el video esta registrado
	public boolean esVideoRegistrado(String url) {
		return catalogoVideos.getVideo(url) != null;
	}

	//Funcion para realizar el login del usuario
	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nombre);
		if (usuario != null && usuario.getContrasena().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	//Funcion para registrar al usuario si no estaba registrado previamente
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,Date fechaNacimiento) {
		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, apellidos,fechaNacimiento,email, login, password);
		adaptadorUsuario.create(usuario);
		catalogoUsuarios.addUsuario(usuario);
		return true;
	}

	//Funcion para buscar la playlist del usuario
	public ListaVideos buscarPlaylist(String playlist) {
		return usuarioActual.tienePlaylist(playlist);
	}
	
	//Funcion para añadir la playlist al usuario y servicio de persistencia
	public void anadirPlaylist(ListaVideos playlist) {
		usuarioActual.addListaVideos(playlist);
		adaptadorListas.registrarListaVideos(playlist);
		adaptadorUsuario.update(getUnicaInstancia().getUsuarioActual());
		
	}

	//Funcion para eliminar la playlist del usuario y del servicio de persistencia
	public void eliminarPlaylist(ListaVideos playlist) {
		getUnicaInstancia().getUsuarioActual().borrarListaVideos(playlist);
		adaptadorListas.borrarListaVideos(playlist);
		adaptadorUsuario.update(getUnicaInstancia().getUsuarioActual());
	}
	
	//Funcion para actualizar la playlist al usuario y del servicio de persistencia
	public void actualizarPlaylist(ListaVideos antigua, ListaVideos nueva) {
		usuarioActual.actualizarListaVideos(antigua,nueva);
		adaptadorListas.modificarListaVideos(nueva);
		adaptadorUsuario.update(getUnicaInstancia().getUsuarioActual());
	}

	//Funcion para obtener las listas del usuario
	public List<ListaVideos> getListasVideosUsuario() {
		return usuarioActual.getListasVideos();
	}

	//Funcion para obtener la lista del usuario 
	public ListaVideos getListaVideo(String nombre_playlist) {
		return usuarioActual.getListaVideos(nombre_playlist);
	}

	//Funcion para añadir una reproduccion al video
	public void sumarReproduccion(Video video) {
		usuarioActual.addVideoRecientes(video);
		actualizarVideo(video);
		adaptadorUsuario.update(usuarioActual);
	}

	//Funcion para actualizar el video
	public void actualizarVideo(Video video) {
		adaptadorVideo.modificarVideo(video);
		 catalogoVideos.addVideo(video);
		
	}

	//Funcion para obtener el nombre del video
	public String getNombreVideo(Video video) {
		Video vi=adaptadorVideo.recuperarVideo(video.getCodigo());
		if(vi.getUrl().equals(video.getUrl()))
			return video.getTitulo();
		else return null;
	}

	//Funcion para añadir el video a la playlist
	public void anadirVideoPlaylist(ListaVideos playlist, Video video) {
		usuarioActual.anadirVideoaPlaylist(playlist,video);
		
	}

	//Funcion para crear la playlist
	public ListaVideos crearPlaylist(String nombre) {
		return usuarioActual.crearPlaylist(nombre);
	}

	//Funcion para obtener el numero de reproducciones del video
	public int getNumRepro(Video video) {
		Video vi=adaptadorVideo.recuperarVideo(video.getCodigo());
		if(vi.getUrl().equals(video.getUrl()))
			return vi.getNumRepro();
		else return -1;
	}

	public List<Video> getRecientesUsuarioActual() {
		return usuarioActual.getRecientes();
	}

	public boolean usuarioActualEsPremium() {
		return usuarioActual.isPremium();
	}
	
	//Funcion para obtener los videos con una subcadena pasada como parametro
	public List<Video> getVideos(String titulo, String... etiquetas) throws DAOException {
		if(titulo.equals("")) return getVideosSinNombre(etiquetas);
		List<Video> listaVideos=catalogoVideos.getVideos(titulo,usuarioActual,etiquetas);
		return listaVideos;
	}

	//Funcion para obtener los videos sin subcadena pasada como parametro
	private List<Video> getVideosSinNombre(String... etiquetas) throws DAOException {
		List<Video> videos =catalogoVideos.getVideos(usuarioActual,etiquetas);
		
		return videos ;
	}

	public List<Video> recogerVideosMasVistos() throws DAOException {
		List<Video> videos=catalogoVideos.getTodosVideos();
		List<Video> masVistos=new LinkedList<Video>();
		//Crear TreeSet ordenando los videos de menor a mayor reproduccion
		TreeSet<Video> ordenado=new TreeSet<Video>((v1,v2)->Integer.valueOf(v1.getNumRepro()).compareTo(Integer.valueOf(v2.getNumRepro())));
		Video[] videos2=new Video[videos.size()];
		int i=0;
		for (Video video : videos) {
			videos2[i]=video;
			i++;
		}
		Collections.addAll(ordenado,videos2);
		//TreeSet con el orden inverso del TreeSet anterior
		TreeSet<Video> ordenInverso=(TreeSet<Video>) ordenado.descendingSet();
		for (Video video : ordenInverso) {
			masVistos.add(video);
			if(masVistos.size()==10) break;
		}
		return masVistos;
	}

	public HashSet<Etiqueta> getEtiquetasVideo(Video video) {
		Video vi=catalogoVideos.getVideo(video.getUrl());
		if(vi.getUrl().equals(video.getUrl()))
			return video.getEtiquetas();
		else return null;
	}

	public void nuevaEtiqueta(Video videoActual, String text) {
		Video vi=catalogoVideos.getVideo(videoActual.getUrl());
		if(vi.getUrl().equals(videoActual.getUrl())) {
			Etiqueta et=new Etiqueta(text);
			if(!esEtiquetaRegistrada(et)) {
				adaptadorEtiqueta.registrarEtiqueta(et);
				catalogoEtiquetas.addEtiqueta(et);
			}
			else et=catalogoEtiquetas.getEtiqueta(et);
			boolean e=videoActual.anadirEtiqueta(et);
			if(e) {
				actualizarVideo(videoActual);
			}
				
				
		}
	}

	public String getUrlVideo(Video video) {
		Video vi=catalogoVideos.getVideo(video.getUrl());
		if(vi.getUrl().equals(video.getUrl()))
			return video.getUrl();
		else return null;
	}

	public void cambiarFiltro(String opcion) {
		if(opcion.equals("No Filtro"))
			usuarioActual.setFiltros(new NoFiltro());
		else if(opcion.equals("Filtro Adulto"))
			usuarioActual.setFiltros(new FiltroAdultos());
		else usuarioActual.setFiltros(new FiltroMisListas());
		
	}

	public void generarPDf(String directorio) throws FileNotFoundException, DocumentException {
		 FileOutputStream archivo = new FileOutputStream(directorio+"\\listasVideos.pdf");
	      Document documento = new Document();
	      PdfWriter.getInstance(documento, archivo);
	      documento.open();
	      documento.add(new Paragraph("AppVideo (c) by Javier Jiménez Hernández y Diego Martínez López \n\n"));
	      documento.add(new Paragraph("Archivo con la listas de videos con sus respectivos vídeos junto a su título y número de reproducciones del usuario "+usuarioActual.getUsuario()+"."));
	      for(ListaVideos lista: usuarioActual.getListasVideos()) {
	    	  documento.add(new Paragraph(lista.getNombre()+" Contiene los siguientes vídeos: \n"));
	    	  for (Video vi : lista.getListaVideos()) {
	    		  documento.add(new Paragraph("Título: "+vi.getTitulo()+ " Num.Reproducciones: "+vi.getNumRepro()));
			}
	    	  documento.add(new Paragraph("\n\n"));
	      }
	      documento.close();
		
	}

	public String getFiltroActual() {
		return usuarioActual.getFiltro();
	}

	public List<Etiqueta> getEtiquetasDisponibles() {
		return adaptadorEtiqueta.recuperarTodasEtiquetas();
	}
	
	public boolean esEtiquetaRegistrada(Etiqueta e) {
		return catalogoEtiquetas.getEtiqueta(e) != null;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	
	

}
