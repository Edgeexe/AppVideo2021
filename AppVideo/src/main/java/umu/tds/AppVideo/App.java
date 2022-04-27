package umu.tds.AppVideo;

import java.awt.EventQueue;

import umu.tds.AppVideo.GUI.Login;

public class App{
	
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login ventana = new Login();
					ventana.mostarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}
}
