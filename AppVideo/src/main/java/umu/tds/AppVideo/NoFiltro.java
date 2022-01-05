package umu.tds.AppVideo;

public class NoFiltro implements Filtro {

	int codigo;
	String nombre = this.getClass().getName();
	
	public NoFiltro() {
		this.codigo = 0;
	}

	@Override
	public boolean esVideoOK(Video v, Usuario usuario) {
		return true;
	}

	@Override
	public int getCodigo() {
		return codigo;
	}

	@Override
	public void setCodigo(int codigo) {
		this.codigo=codigo;
		
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

}
