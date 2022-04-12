package dominio;

import java.util.*;

public class Video {
	
	private int codigo;
	private String url;
	private String titulo;
	private int numRepro;
	private HashSet<Etiqueta> etiquetas;
	
	public Video(String url,String titulo,List<String>etiquetas) {
		this.codigo=0;
		this.url=url;
		this.titulo=titulo;
		this.numRepro=0;
		if(etiquetas.isEmpty())this.etiquetas=new HashSet<Etiqueta>();
		else {
			this.etiquetas=new HashSet<Etiqueta>();
			for (String etiqueta : etiquetas) {
				this.etiquetas.add(new Etiqueta(etiqueta));
			}
		}
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


	public String etiquetaToString() {
		String etiquetas= "";
		for(Etiqueta e : this.etiquetas) {
			etiquetas += e.getEtiqueta() + " ";
		}
		return etiquetas.trim();
	}
}
	
