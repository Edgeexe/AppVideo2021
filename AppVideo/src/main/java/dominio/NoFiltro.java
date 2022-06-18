package dominio;

public class NoFiltro implements Filtro {
	int codigo;
	String nombre = this.getClass().getName();
	
	public NoFiltro() {
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
		return true;
	}
}
