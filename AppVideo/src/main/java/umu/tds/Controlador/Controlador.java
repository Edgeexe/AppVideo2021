package umu.tds.Controlador;

import java.io.File;
import java.util.EventObject;
import umu.tds.componente.*;

public class Controlador implements VideosListener {
	private static Controlador unicaInstancia;

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
		  //Añadir los videos ev al Catalogo
		 }
	}
	
	
}
