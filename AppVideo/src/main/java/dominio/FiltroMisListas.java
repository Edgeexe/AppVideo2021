package dominio;

public class FiltroMisListas implements Filtro {

	int codigo;
	String nombre = this.getClass().getName();
	
	public FiltroMisListas() {
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
