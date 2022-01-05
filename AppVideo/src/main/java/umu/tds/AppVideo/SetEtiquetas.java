package umu.tds.AppVideo;

import java.util.Collection;
import java.util.HashMap;

public enum SetEtiquetas {
	INSTANCE;
	private HashMap<String, Etiqueta> etiquetas;
	
	private SetEtiquetas(){
		etiquetas = new HashMap<String, Etiqueta>();
	}
	
	public Etiqueta get(String etiqueta)
	{
		if(etiquetas.get(etiqueta)!=null) {
			return etiquetas.get(etiqueta);
		}
		Etiqueta e = new Etiqueta(etiqueta); 
		etiquetas.put(etiqueta, e);
		return e;
	}
	
	public int getNumEtiquetas()
	{
		return etiquetas.size();
	}
	
	public Collection<Etiqueta> getAll()
	{
		return etiquetas.values();
	}
	
}
