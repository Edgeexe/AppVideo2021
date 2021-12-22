package umu.tds.AppVideo;

import static org.junit.Assert.assertTrue;

import java.awt.EventQueue;

import org.junit.Test;

import umu.tds.AppVideo.GUI.VentanaPrincipal;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
