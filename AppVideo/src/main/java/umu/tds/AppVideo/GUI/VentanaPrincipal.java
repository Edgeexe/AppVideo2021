package umu.tds.AppVideo.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtHola;

	public static void main(String[] args) {

        JFrame frame = new VentanaPrincipal(); // GUI gui = new GUI() as well
        // default value JFrame.HIDE_ON_CLOSE
        frame.setVisible(true);
}
	
	public VentanaPrincipal() {
		setBounds(new Rectangle(250, 250, 800, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 120, 767, 537);
		getContentPane().add(panel);
		
		JButton boton_Registro = new JButton("Registro");
		boton_Registro.setBounds(366, 10, 90, 30);
		getContentPane().add(boton_Registro);
		boton_Registro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame ventana2=new SignUp();
					ventana2.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		JButton boton_Login = new JButton("Login");
		boton_Login.setBounds(453, 10, 90, 30);
		getContentPane().add(boton_Login);
		boton_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame ventana2=new Login();
					ventana2.setVisible(true);
					setVisible(false);
				}
			}
		});
		
		JButton boton_Logout = new JButton("Logout");
		boton_Logout.setBounds(575, 10, 90, 30);
		getContentPane().add(boton_Logout);
		
		JButton boton_Premium = new JButton("Premium");
		boton_Premium.setForeground(Color.RED);
		boton_Premium.setBounds(694, 10, 90, 30);
		getContentPane().add(boton_Premium);
		
		JButton boton_Explorar = new JButton("Explorar\r\n\r\n");
		boton_Explorar.setBackground(UIManager.getColor("Button.background"));
		boton_Explorar.setForeground(Color.BLACK);
		boton_Explorar.setBounds(10, 95, 100, 20);
		getContentPane().add(boton_Explorar);
		
		JButton boton_Mis_Listas = new JButton("Mis Listas");
		boton_Mis_Listas.setForeground(Color.BLACK);
		boton_Mis_Listas.setBackground(UIManager.getColor("Button.background"));
		boton_Mis_Listas.setBounds(110, 95, 100, 20);
		getContentPane().add(boton_Mis_Listas);
		
		JButton boton_Recientes = new JButton("Recientes\r\n");
		boton_Recientes.setBounds(210, 95, 100, 20);
		getContentPane().add(boton_Recientes);
		
		JButton boton_NuevaLista = new JButton("Nueva Lista");
		boton_NuevaLista.setBounds(310, 95, 105, 20);
		getContentPane().add(boton_NuevaLista);
		
		txtHola = new JTextField();
		txtHola.setSelectionColor(SystemColor.menu);
		txtHola.setEditable(false);
		txtHola.setBackground(SystemColor.menu);
		txtHola.setText("Hola + nombre de usuario");
		txtHola.setBounds(171, 16, 167, 19);
		txtHola.setBorder(null);
		getContentPane().add(txtHola);
		txtHola.setColumns(10);
	}
}
