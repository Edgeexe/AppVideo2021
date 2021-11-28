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
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public static void main(String[] args) {

        JFrame frame = new VentanaPrincipal(); // GUI gui = new GUI() as well
        // default value JFrame.HIDE_ON_CLOSE
        frame.setVisible(true);
}
	
	public VentanaPrincipal() {
		setBounds(new Rectangle(250, 250, 810, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel_cabecera = new JPanel();
		panel_cabecera.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_cabecera.setBounds(10, 0, 774, 67);
		getContentPane().add(panel_cabecera);
		panel_cabecera.setLayout(null);
		
		JButton boton_Registro = new JButton("Registro");
		boton_Registro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame registro=new SignUp();
					registro.setVisible(true);
					dispose();
				}
			}
		});
		boton_Registro.setBounds(381, 11, 90, 29);
		panel_cabecera.add(boton_Registro);
		
		JButton boton_Login = new JButton("Login");
		boton_Login.setBounds(469, 11, 71, 29);
		panel_cabecera.add(boton_Login);
		boton_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame login=new Login();
					login.setVisible(true);
					dispose();
				}
			}
		});
		
		JButton boton_Logout = new JButton("Logout");
		boton_Logout.setBounds(569, 11, 79, 29);
		panel_cabecera.add(boton_Logout);
		
		JButton boton_Premium = new JButton("Premium");
		boton_Premium.setBounds(679, 11, 90, 29);
		boton_Premium.setForeground(Color.RED);
		panel_cabecera.add(boton_Premium);
		
		textField = new JTextField();
		textField.setBounds(165, 3, 223, 37);
		textField.setText("Hola + nombre de usuario");
		textField.setSelectionColor(SystemColor.menu);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(SystemColor.menu);
		panel_cabecera.add(textField);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setBorder(null);
		panel_botones.setBounds(0, 69, 794, 29);
		getContentPane().add(panel_botones);
		panel_botones.setLayout(null);
		
		JButton boton_Explorar = new JButton("Explorar");
		boton_Explorar.setBounds(10, 1, 105, 26);
		panel_botones.add(boton_Explorar);
		
		JButton boton_mis_listas = new JButton("Mis Listas");
		boton_mis_listas.setBounds(115, 1, 105, 26);
		panel_botones.add(boton_mis_listas);
		
		JButton boton_Recientes = new JButton("Recientes");
		boton_Recientes.setBounds(220, 1, 105, 26);
		panel_botones.add(boton_Recientes);
		
		JButton boton_Nueva_Lista = new JButton("Nueva Lista");
		boton_Nueva_Lista.setBounds(325, 1, 105, 26);
		panel_botones.add(boton_Nueva_Lista);
		
		JPanel panel_explorar = new JPanel();
		panel_explorar.setLayout(null);
		panel_explorar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_explorar.setBackground(SystemColor.menu);
		panel_explorar.setBounds(10, 99, 774, 554);
		getContentPane().add(panel_explorar);
		panel_explorar.setVisible(true);
		
		JPanel panel_mis_listas = new JPanel();
		panel_mis_listas.setLayout(null);
		panel_mis_listas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_mis_listas.setBackground(SystemColor.menu);
		panel_mis_listas.setBounds(10, 99, 774, 554);
		getContentPane().add(panel_mis_listas);
		panel_mis_listas.setVisible(false);
		
		JPanel panel_recientes = new JPanel();
		panel_recientes.setLayout(null);
		panel_recientes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_recientes.setBackground(SystemColor.menu);
		panel_recientes.setBounds(10, 99, 774, 554);
		getContentPane().add(panel_recientes);
		panel_recientes.setVisible(false);
		
		JPanel panel_nueva_lista = new JPanel();
		panel_nueva_lista.setLayout(null);
		panel_nueva_lista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_nueva_lista.setBackground(SystemColor.menu);
		panel_nueva_lista.setBounds(10, 99, 774, 554);
		getContentPane().add(panel_nueva_lista);
		panel_nueva_lista.setVisible(false);
	}
}
