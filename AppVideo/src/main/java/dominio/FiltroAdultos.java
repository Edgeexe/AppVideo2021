package dominio;

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
		// TODO Auto-generated method stub
		return false;
	}

}

