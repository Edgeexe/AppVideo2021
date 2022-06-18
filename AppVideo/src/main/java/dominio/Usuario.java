package dominio;

import java.util.Date;
import java.util.*;

public class Usuario {
	
	private int codigo;
	private String nombre;
	private String apellidos;
	private String usuario;
	private String email;
	private String contraseña;
	private Date fecha;
	private boolean premium;
	private List<ListaVideos> listasVideos;
	private List<Video> recientes;
	private Filtro filtro;
	
	public Usuario(String nombre, String apellidos, Date fecha, String email, String usuario,
			String contrasena) {
		this.codigo = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario= usuario;
		this.contraseña = contrasena;
		this.fecha = fecha;
		this.premium=false;
		this.listasVideos=new LinkedList<ListaVideos>();
		this.recientes=new LinkedList<Video>();
		this.filtro=new NoFiltro();
	}


	public List<ListaVideos> getListasVideos() {
		return listasVideos;
	}



	public void setListasVideos(List<ListaVideos> listasVideos) {
		this.listasVideos = listasVideos;
	}



	public List<Video> getRecientes() {
		return recientes;
	}



	public void setRecientes(List<Video> recientes) {
		this.recientes = recientes;
	}



	public int getCodigo() {
		return this.codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public String getApellidos() {
		return apellidos;
	}



	public String getEmail() {
		return email;
	}


	public String getContrasena() {
		return contraseña;
	}


	public Date getFecha() {
		return fecha;
	}



	public boolean isPremium() {
		// TODO Auto-generated method stub
		return this.premium;
	}


	public void setCodigo(int id) {
		this.codigo=id;
		
	}

	public String getUsuario() {
		return this.usuario;
	}


	public void setPremium(boolean premium) {
		this.premium=premium;
	}


	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public Filtro getFiltros() {
		return filtro;
	}
	
	public String getFiltro() {
		if(filtro instanceof NoFiltro)
			return "No Filtro";
		
		else if(filtro instanceof FiltroAdultos)
			return "Filtro Adultos";
		
		else return "Filtro Mis Listas";
	}

	public void setFiltros(Filtro filtros) {
		this.filtro = filtros;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public void addListaVideos(ListaVideos lv) {
		this.listasVideos.add(lv);
		
	}


	public void addVideoRecientes(Video v) {
		int index=-1;
		for (Video video : recientes) {
			if(video.getUrl().equals(v.getUrl())) {
				index=recientes.indexOf(video);
				break;
			}
		}
		if(index!=-1) recientes.remove(index);
		if(this.recientes.size()<5) this.recientes.add(0,v);
		else {
			this.recientes.remove(4);
			this.recientes.add(0,v);
		}
		v.sumarNumRepro();
	}
	
	public String ListaVideosToString() {
		String listaVideos = "";
		for(ListaVideos lista : this.listasVideos) {
			listaVideos += lista.getCodigo() + " ";
		}
		return listaVideos;
	}
	
	public String videosRecienetsToString() {
		String recientes= "";
		for(Video video : this.recientes) {
			recientes += video.getCodigo() + " ";
		}
		return recientes.trim();
	}


	public ListaVideos tienePlaylist(String playlist) {
		for (ListaVideos listaVideo : listasVideos) {
			if(listaVideo.getNombre().equals(playlist))
				return listaVideo;
		}
		return null;
	}


	public void borrarListaVideos(ListaVideos playlist) {
		this.listasVideos.remove(playlist);	
	}


	public void actualizarListaVideos(ListaVideos antigua, ListaVideos nueva) {
		this.listasVideos.remove(antigua);
		this.listasVideos.add(nueva);
	}


	public ListaVideos getListaVideos(String nombre_playlist) {
		for (ListaVideos listaVideos : listasVideos) {
			if(listaVideos.getNombre().equals(nombre_playlist))
				return listaVideos;
		}
		return null;
	}


	public void anadirVideoaPlaylist(ListaVideos playlist, Video video) {
		playlist.addVideo(video);
		
	}


	public ListaVideos crearPlaylist(String nombre) {
		ListaVideos v=new ListaVideos(nombre);
		return v;
		
	}


	public boolean comprobarVideo(Video vid) {
		return filtro.esVideoOk(vid, this);
	}

}
