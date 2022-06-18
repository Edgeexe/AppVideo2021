package dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class FiltroAdultos implements Filtro {

	int codigo;
	String nombre = this.getClass().getName();
	
	public FiltroAdultos() {
		this.codigo = 0;
	}

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo=codigo;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean esVideoOk(Video video, Usuario usuario) {
		Period años=Period.between(usuario.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
		if(años.getYears()>=18) return true;
		else return !video.getEtiquetas().contains(new Etiqueta("Adulto"));
	}

}

