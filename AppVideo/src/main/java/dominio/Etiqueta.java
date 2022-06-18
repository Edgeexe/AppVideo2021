package dominio;

import java.util.Objects;

public class Etiqueta {
	
	private int codigo;
	private String etiqueta;
	
	public Etiqueta(String etiqueta) {
		codigo=0;
		this.etiqueta = etiqueta;
	}

	public String getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(etiqueta);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Etiqueta other = (Etiqueta) obj;
		return Objects.equals(etiqueta, other.etiqueta);
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo=codigo;
	}
		

}
