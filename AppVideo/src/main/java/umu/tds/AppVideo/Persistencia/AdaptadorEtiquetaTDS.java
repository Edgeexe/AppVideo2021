package umu.tds.AppVideo.Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import dominio.Etiqueta;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorEtiquetaTDS implements IAdaptadorEtiquetaDAO {

	private static AdaptadorEtiquetaTDS instancia;
	private static ServicioPersistencia servPersistencia;
	
	AdaptadorEtiquetaTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public static AdaptadorEtiquetaTDS getInstance() {
		if (instancia == null) {
			return new AdaptadorEtiquetaTDS();
		}
		return instancia;
	}
	
	@Override
	public void registrarEtiqueta(Etiqueta etiqueta) {
		Entidad eEtiqueta=null;
		try {
			eEtiqueta=servPersistencia.recuperarEntidad(etiqueta.getCodigo());			
		} catch (NullPointerException e) {}
		if(eEtiqueta !=null) return;
		eEtiqueta=new Entidad();
		eEtiqueta.setNombre("etiqueta");
		eEtiqueta.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(
						  new Propiedad("nombre",String.valueOf(etiqueta.getEtiqueta())
						)
					)
				));
		eEtiqueta=servPersistencia.registrarEntidad(eEtiqueta);
		etiqueta.setCodigo(eEtiqueta.getId());
		
	}

	@Override
	public void borrarEtiqueta(Etiqueta e) {
		Entidad eEtiqueta;
		eEtiqueta = servPersistencia.recuperarEntidad(e.getCodigo());
		servPersistencia.borrarEntidad(eEtiqueta);
	}

	@Override
	public Etiqueta recuperarEtiqueta(int codigo) {
		Entidad eEtiqueta = servPersistencia.recuperarEntidad(codigo);
		Etiqueta e = null;
		String nombre = servPersistencia.recuperarPropiedadEntidad(eEtiqueta, "nombre");
		e=new Etiqueta(nombre);
		e.setCodigo(eEtiqueta.getId());
		return e;
	}

	@Override
	public List<Etiqueta> recuperarTodasEtiquetas() {
		List<Etiqueta> etiquetas = new LinkedList<Etiqueta>();
		List<Entidad> eEtiquetas = servPersistencia.recuperarEntidades("etiqueta");

		for (Entidad eEtiqueta : eEtiquetas) {
			etiquetas.add(recuperarEtiqueta(eEtiqueta.getId()));
		}
		return etiquetas;
	}

}
