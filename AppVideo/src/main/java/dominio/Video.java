package dominio;

import java.util.*;

public class Video {
	
	private int codigo;
	private String url;
	private String titulo;
	private int numRepro;
	private HashSet<Etiqueta> etiquetas;
	
	public Video(String url,String titulo) {
		this.codigo=0;
		this.url=url;
		this.titulo=titulo;
		this.numRepro=0;
		this.etiquetas=new HashSet<Etiqueta>();
	}
	
	
	
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public HashSet<Etiqueta> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas( HashSet<Etiqueta> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitiulo() {
		return titulo;
	}
	public void setTitiulo(String titiulo) {
		this.titulo = titiulo;
	}
	public int getNumRepro() {
		return numRepro;
	}
	public void setNumRepro(int numRepro) {
		this.numRepro = numRepro;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}



	public void anadirEtiqueta(Etiqueta e) {
		etiquetas.add(e);
	}
	
	
	

}
