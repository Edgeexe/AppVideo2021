package umu.tds.AppVideo.GUI;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private int posX=0;
	private int	posY=0;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JTextField textField_nombre;
	private JTextField textField_apellidos;
	private JTextField textField_fechaNacimiento;
	private JTextField textField_CorreoElectronico;
	private JTextField textField_Usuario;
	private JTextField textField_Contraseña;
	private JTextField textField_Confirmar_Contraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		
		setBounds(new Rectangle(100, 100, 750, 500));
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 750, 500);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:\r\n");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNombre.setBounds(37, 51, 66, 14);
		panel.add(lblNombre);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Dialog", Font.BOLD, 13));
		lblFechaDeNacimiento.setBounds(37, 121, 153, 14);
		panel.add(lblFechaDeNacimiento);
		
		JLabel lblApellidos = new JLabel("Apellidos\r\n:\r\n");
		lblApellidos.setFont(new Font("Dialog", Font.BOLD, 13));
		lblApellidos.setBounds(298, 51, 66, 14);
		panel.add(lblApellidos);
		
		JLabel lblCorreoElectrnico = new JLabel("Correo electrónico:");
		lblCorreoElectrnico.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCorreoElectrnico.setBounds(37, 186, 135, 14);
		panel.add(lblCorreoElectrnico);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNombreDeUsuario.setBounds(37, 239, 135, 14);
		panel.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Dialog", Font.BOLD, 13));
		lblContrasea.setBounds(37, 285, 90, 14);
		panel.add(lblContrasea);
		
		JLabel lblConfirmarcontrasea = new JLabel("Confirmar_Contraseña:");
		lblConfirmarcontrasea.setFont(new Font("Dialog", Font.BOLD, 13));
		lblConfirmarcontrasea.setBounds(37, 340, 155, 14);
		panel.add(lblConfirmarcontrasea);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(99, 49, 162, 20);
		panel.add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_apellidos = new JTextField();
		textField_apellidos.setBounds(371, 49, 311, 20);
		panel.add(textField_apellidos);
		textField_apellidos.setColumns(10);
		
		textField_fechaNacimiento = new JTextField();
		textField_fechaNacimiento.setBounds(190, 117, 289, 20);
		panel.add(textField_fechaNacimiento);
		textField_fechaNacimiento.setColumns(10);
		
		textField_CorreoElectronico = new JTextField();
		textField_CorreoElectronico.setBounds(168, 185, 311, 20);
		panel.add(textField_CorreoElectronico);
		textField_CorreoElectronico.setColumns(10);
		
		textField_Usuario = new JTextField();
		textField_Usuario.setBounds(168, 238, 133, 20);
		panel.add(textField_Usuario);
		textField_Usuario.setColumns(10);
		
		textField_Contraseña = new JTextField();
		textField_Contraseña.setBounds(120, 283, 222, 20);
		panel.add(textField_Contraseña);
		textField_Contraseña.setColumns(10);
		
		textField_Confirmar_Contraseña = new JTextField();
		textField_Confirmar_Contraseña.setBounds(191, 339, 256, 20);
		panel.add(textField_Confirmar_Contraseña);
		textField_Confirmar_Contraseña.setColumns(10);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(209, 423, 92, 41);
		panel.add(btnSignUp);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame login=new Login();
					login.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setBounds(387, 423, 92, 41);
		panel.add(btnCancelar);
		
			
		this.addMouseListener(new MouseAdapter()			//Para arrastrar la ventana
				{
				   public void mousePressed(MouseEvent e)
				   {
				      posX = e.getX();
				      posY = e.getY();
				   }
				});
				
		this.addMouseMotionListener(new MouseAdapter()
				{
				     public void mouseDragged(MouseEvent evt)
				     {	
						setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);				
				     }
				});
		
	}
}
