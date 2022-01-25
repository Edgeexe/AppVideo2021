package dominio;

import java.util.*;

public class ListaVideos {
	
	private int codigo;
	private String nombre;
	private List<Video> videos;
	
	
	public ListaVideos(String nombre) {
		this.codigo=0;
		this.nombre = nombre;
		this.videos=new LinkedList<Video>();
	}
	
	
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Video> getListaVideos() {
		return videos;
	}
	
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public int getCodigo() {
		return this.codigo;
	}



	public void addVideo(Video video) {
		this.videos.add(video);
	}
	
	public String videosToString() {		// método para la construcción de propiedades del método DAO
		String videos = "";
		for(Video video : this.videos) {
			videos += video.getCodigo() + " ";
		}
		return videos;
	}
	
	

}
