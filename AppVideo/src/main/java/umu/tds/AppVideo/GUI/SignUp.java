package umu.tds.AppVideo.GUI;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.k33ptoo.components.KButton;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private int posX=0;
	private int	posY=0;
	private JPasswordField passwordField;

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
		
		setBounds(new Rectangle(250, 250, 514, 671));
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 514, 671);
		getContentPane().add(panel);
		panel.setLayout(null);		
		
		KButton button = new KButton();
		button.setkHoverForeGround(new Color(36,36,36));
		button.kFillButton = true;
		button.kBackGroundColor = SystemColor.desktop;
		button.kBorderRadius = 15;	
		button.setkForeGround(new Color(252,252,252));
		button.setBounds(155, 585, 185, 45);
		button.setText("Sign Up");
		button.setFont(new Font("Lato", Font.BOLD, 13));
		button.setBorder(null);
		button.setkStartColor(new Color(0,105,242));
		button.setkEndColor(new Color(36,36,36));
		button.setkHoverEndColor(new Color(0, 49, 112));
		button.setkHoverStartColor(new Color(0,105,242));
		panel.add(button);
		
		
		
		JTextField textField_nombre_4 = new HintTextField("Nombre de usuario");
		textField_nombre_4.setForeground(Color.white);
		textField_nombre_4.setFont(new Font("Lato", Font.BOLD, 13));
		textField_nombre_4.setBackground(Color.decode("#171717"));
		textField_nombre_4.setBorder(null);
		textField_nombre_4.setBounds(74, 262, 374, 36);
		panel.add(textField_nombre_4);
		
		
		JTextField textField_nombre_3 = new HintTextField("Correo electrónico");
		textField_nombre_3.setForeground(Color.white);
		textField_nombre_3.setFont(new Font("Lato", Font.BOLD, 13));
		textField_nombre_3.setBackground(Color.decode("#171717"));
		textField_nombre_3.setBorder(null);
		textField_nombre_3.setBounds(74, 215, 374, 36);
		panel.add(textField_nombre_3);
		
		JTextField textField_nombre = new HintTextField("Nombre");
		textField_nombre.setForeground(Color.white);
		textField_nombre.setFont(new Font("Lato", Font.BOLD, 13));
		textField_nombre.setBackground(Color.decode("#171717"));
		textField_nombre.setBorder(null);
		textField_nombre.setBounds(74, 80, 374, 36);
		panel.add(textField_nombre);
		
		JTextField textField_nombre_1 = new HintTextField("Apellidos");
		textField_nombre_1.setForeground(Color.white);
		textField_nombre_1.setFont(new Font("Lato", Font.BOLD, 13));
		textField_nombre_1.setBackground(Color.decode("#171717"));
		textField_nombre_1.setBorder(null);
		textField_nombre_1.setBounds(74, 125, 374, 36);
		panel.add(textField_nombre_1);
		
		JTextField textField_nombre_2 = new HintTextField("Fecha de nacimiento");
		textField_nombre_2.setForeground(Color.white);
		textField_nombre_2.setFont(new Font("Lato", Font.BOLD, 13));
		textField_nombre_2.setBackground(Color.decode("#171717"));
		textField_nombre_2.setBorder(null);
		textField_nombre_2.setBounds(74, 168, 374, 36);
		panel.add(textField_nombre_2);
		
		JSeparator separator = new JSeparator();
		separator.setFocusTraversalKeysEnabled(false);
		separator.setEnabled(false);
		separator.setRequestFocusEnabled(false);
		separator.setBackground(new Color(0, 153, 255));
		separator.setBounds(74, 115, 374, 7);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setFocusTraversalKeysEnabled(false);
		separator_1.setEnabled(false);
		separator_1.setRequestFocusEnabled(false);
		separator_1.setBackground(new Color(0, 153, 255));
		separator_1.setBounds(74, 160, 374, 7);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setFocusTraversalKeysEnabled(false);
		separator_2.setEnabled(false);
		separator_2.setRequestFocusEnabled(false);
		separator_2.setBackground(new Color(0, 153, 255));
		separator_2.setBounds(74, 203, 374, 7);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setRequestFocusEnabled(false);
		separator_3.setFocusTraversalKeysEnabled(false);
		separator_3.setEnabled(false);
		separator_3.setBackground(new Color(0, 153, 255));
		separator_3.setBounds(74, 250, 374, 7);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setRequestFocusEnabled(false);
		separator_4.setFocusTraversalKeysEnabled(false);
		separator_4.setEnabled(false);
		separator_4.setBackground(new Color(0, 153, 255));
		separator_4.setBounds(74, 297, 374, 7);
		panel.add(separator_4);		

		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setOpaque(true);
		separator_4_1.setForeground(new Color(0, 153, 255));
		separator_4_1.setRequestFocusEnabled(false);
		separator_4_1.setFocusTraversalKeysEnabled(false);
		separator_4_1.setBorder(null);
		separator_4_1.setEnabled(false);
		separator_4_1.setBackground(new Color(0, 153, 255));
		separator_4_1.setBounds(74, 344, 374, 1);
		panel.add(separator_4_1);
		
		
		JPasswordField pwdPassword = new JPasswordField();
		pwdPassword.setOpaque(false);
		pwdPassword.setBorder(null);
		pwdPassword.setForeground(Color.WHITE);
		pwdPassword.setFont(new Font("Lato", Font.BOLD, 13));
		pwdPassword.setBounds(74, 309, 374, 36);
		panel.add(pwdPassword);	
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lato", Font.BOLD, 13));
		lblNewLabel_1.setBounds(74, 309, 374, 36);
		lblNewLabel_1.setBorder(null);
		panel.add(lblNewLabel_1);
		
		pwdPassword.addFocusListener(new FocusListener() { // Text hint para la contraseña

		  @Override
		  public void focusGained(FocusEvent e) {
			   lblNewLabel_1.setVisible(false);
		  }
		  @Override
		  
		  public void focusLost(FocusEvent e) {
			  if(pwdPassword.getPassword().length == 0) {
				  lblNewLabel_1.setVisible(true);
			  }
		  }
			
			
		});
		
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
		btnCancelar.setBounds(422, 0, 92, 41);
		panel.add(btnCancelar);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setRequestFocusEnabled(false);
		separator_6.setOpaque(true);
		separator_6.setForeground(new Color(0, 153, 255));
		separator_6.setFocusTraversalKeysEnabled(false);
		separator_6.setEnabled(false);
		separator_6.setBorder(null);
		separator_6.setBackground(new Color(0, 153, 255));
		separator_6.setBounds(74, 392, 374, 1);
		panel.add(separator_6);
		
		passwordField = new JPasswordField();
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Lato", Font.BOLD, 13));
		passwordField.setBorder(null);
		passwordField.setBounds(74, 357, 374, 36);
		panel.add(passwordField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Confirmar contraseña");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Lato", Font.BOLD, 13));
		lblNewLabel_1_1.setBorder(null);
		lblNewLabel_1_1.setBounds(74, 357, 374, 36);
		panel.add(lblNewLabel_1_1);
		
		passwordField.addFocusListener(new FocusListener() { // Text hint para la contraseña

			  @Override
			  public void focusGained(FocusEvent e) {
				   lblNewLabel_1_1.setVisible(false);
			  }
			  @Override
			  
			  public void focusLost(FocusEvent e) {
				  if(pwdPassword.getPassword().length == 0) {
					  lblNewLabel_1_1.setVisible(true);
				  }
			  }
				
				
			});
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 153, 255));
		lblNewLabel.setIcon(new ImageIcon(SignUp.class.getResource("/src/main/resources/SignUpBG.png")));
		lblNewLabel.setBounds(0, 0, 514, 671);
		panel.add(lblNewLabel);
		
			
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
