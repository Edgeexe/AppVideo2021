package umu.tds.AppVideo.Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.ListaVideos;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaVideosTDS implements IAdaptadorListaVideosDAO{
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaVideosTDS unicaInstancia;
		
	public static AdaptadorListaVideosTDS getInstance() {
		if (unicaInstancia == null)
			 return new AdaptadorListaVideosTDS();
		else return unicaInstancia;
	}
	
	private AdaptadorListaVideosTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void registrarListaVideos(ListaVideos lv) {
		Entidad eLv = null;		
		eLv = new Entidad();
		eLv.setNombre("listaVideos");
		eLv.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", String.valueOf(lv.getNombre())),
						new Propiedad("videos",lv.videosToString()))));
		eLv = servPersistencia.registrarEntidad(eLv);
		lv.setCodigo(eLv.getId());
	}

	public void borrarListaVideos(ListaVideos lv) {
		Entidad eLv;
		eLv = servPersistencia.recuperarEntidad(lv.getCodigo());
		servPersistencia.borrarEntidad(eLv);

	}

	public void modificarListaVideos(ListaVideos lv) {
		Entidad eLv = servPersistencia.recuperarEntidad(lv.getCodigo());

		for (Propiedad prop : eLv.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(lv.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(String.valueOf(lv.getNombre()));
			} else if (prop.getNombre().equals("videos")) {
				prop.setValor(lv.videosToString());
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public ListaVideos recuperarListaVideos(int codigo) {
		Entidad eLv = servPersistencia.recuperarEntidad(codigo);
		String nombre = String.valueOf(servPersistencia.recuperarPropiedadEntidad(eLv, "nombre"));
		String v=  servPersistencia.recuperarPropiedadEntidad(eLv,"videos");
		String[] videos=v.split(" ");
		ListaVideos l=new ListaVideos(nombre);
		if (v.equals("")) {
			l.setCodigo(eLv.getId());
			return l;
		}
		for (String video : videos) {
			l.addVideo(AdaptadorVideoTDS.getInstance().recuperarVideo(Integer.parseInt(video)));
		}
		l.setCodigo(eLv.getId());
		return l;
	}

	public List<ListaVideos> recuperarTodasListasVideos() {
		List<ListaVideos> listasVideos = new LinkedList<ListaVideos>();
		List<Entidad> eListasVideos = servPersistencia.recuperarEntidades("listaVideos");

		for (Entidad eListaVideo : eListasVideos) {
			listasVideos.add(recuperarListaVideos(eListaVideo.getId()));
		}
		return listasVideos;
	}


}
