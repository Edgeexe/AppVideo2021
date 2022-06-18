package umu.tds.AppVideo.Persistencia;

import java.util.List;

import dominio.*;

public interface IAdaptadorEtiquetaDAO {
	public void registrarEtiqueta(Etiqueta etiqueta);
	public void borrarEtiqueta(Etiqueta e);
	public Etiqueta recuperarEtiqueta(int codigo);
	public List<Etiqueta> recuperarTodasEtiquetas();
}
