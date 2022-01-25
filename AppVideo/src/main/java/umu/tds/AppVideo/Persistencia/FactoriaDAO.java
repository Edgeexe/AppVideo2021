package umu.tds.AppVideo.Persistencia;

public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia=null;
	
	public static final String DAO_TDS = "umu.tds.AppVideo.Persistencia.TDSFactoriaDAO";
	
	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { unicaInstancia=(FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
			} 
		return unicaInstancia;
	}


	public static FactoriaDAO getInstancia() throws DAOException{
			if (unicaInstancia == null) return getInstancia (FactoriaDAO.DAO_TDS);
					else return unicaInstancia;
		}

	/* Constructor */
	protected FactoriaDAO (){}
		
		
	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
	public abstract IAdaptadorVideoDAO getVideoDAO();
	public abstract IAdaptadorListaVideosDAO getListaVideosDAO();
}
