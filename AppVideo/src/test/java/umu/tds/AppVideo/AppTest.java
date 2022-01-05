package umu.tds.AppVideo;

import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;
import dominio.*;
import umu.tds.AppVideo.Persistencia.DAOException;




/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) throws DAOException {
		CatalogoUsuarios u=new CatalogoUsuarios();
		@SuppressWarnings("deprecation")
		Usuario u1=new Usuario("Javi","Jimenez Hernandez",new Date(101,10,13),"javitenista46@gmail.com","chof16","12345678");
		@SuppressWarnings("deprecation")
		Usuario u2=new Usuario("Pepe","Martinez Hernandez",new Date(101,0,1),"mskha46@gmail.com","manolo","ddd");
		u.addUsuario(u1);
		u.addUsuario(u2);
		System.out.println(u.getUsuario(0));
		u.removeUsuario(u1);
	}
	

}
