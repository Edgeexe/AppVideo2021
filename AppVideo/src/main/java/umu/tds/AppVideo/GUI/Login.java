package umu.tds.AppVideo.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private int posX=0;
	private int	posY=0;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	
	public Login() {
		setBounds(new Rectangle(100, 100, 1280, 720));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setBackground(new Color(0,0,0,0));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1280, 720);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setOpaque(false);
		
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lato", Font.BOLD, 13));
		lblNewLabel_1.setBounds(734, 417, 66, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame ventana2=new SignUp();
					ventana2.setVisible(true);
					setVisible(false);
				}
			}
		});
		btnSignUp.setMargin(new Insets(20, 2, 20, 2));
		btnSignUp.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSignUp.setFont(new Font("Lato", Font.BOLD, 17));
		btnSignUp.setFocusable(false);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setBorder(null);
		btnSignUp.setBackground(new Color(0, 153, 255));
		btnSignUp.setBounds(978, 479, 180, 64);
		panel.add(btnSignUp);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setMargin(new Insets(20, 2, 20, 2));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setFont(new Font("Lato", Font.BOLD, 17));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(0, 153, 255));
		btnNewButton.setBounds(759, 479, 180, 64);
		btnNewButton.setFocusable(false);
		panel.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					JFrame ventana=new VentanaPrincipal();
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		
		JButton boton_X = new JButton("");
		boton_X.setIcon(new ImageIcon(Login.class.getResource("/src/main/resources/Close button.png")));
		boton_X.setBackground(Color.BLACK);
		boton_X.setForeground(Color.WHITE);
		boton_X.setFont(new Font("Dialog", Font.BOLD, 30));
		boton_X.setBorder(null);
		boton_X.setBounds(1240, 0, 40, 26);
		boton_X.setFocusable(false);
		panel.add(boton_X);
		
		boton_X.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evento) {
				if(evento.getButton()==MouseEvent.BUTTON1)
					System.exit(0);
			}
			public void mouseEntered(MouseEvent evt) {
				boton_X.setContentAreaFilled(true);
				boton_X.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
				boton_X.setContentAreaFilled(false);		    
		    }
		});
		
		
		
		JButton boton_Minimizar = new JButton("");
		boton_Minimizar.setIcon(new ImageIcon(Login.class.getResource("/src/main/resources/Minimize.png")));
		boton_Minimizar.setForeground(Color.WHITE);
		boton_Minimizar.setFont(new Font("Dialog", Font.BOLD, 30));
		boton_Minimizar.setFocusable(false);
		boton_Minimizar.setBorder(null);
		boton_Minimizar.setBackground(Color.BLACK);
		boton_Minimizar.setBounds(1200, 0, 40, 26);
		panel.add(boton_Minimizar);
		
		boton_Minimizar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evento) {
				if(evento.getButton()==MouseEvent.BUTTON1)
					setState(JFrame.ICONIFIED);
			}
			
			public void mouseEntered(MouseEvent evt) {
				boton_Minimizar.setContentAreaFilled(true);
				boton_Minimizar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
				boton_Minimizar.setContentAreaFilled(false);		    
		    }
		});
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/src/main/resources/LogoText.png")));
		lblNewLabel.setBounds(835, 31, 250, 250);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.inactiveCaptionBorder);
		separator.setBounds(734, 367, 477, 2);
		panel.add(separator);
		
		txtUsername = new HintTextField("Username");
		txtUsername.setMargin(new Insets(2, 20, 2, 2));
		txtUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtUsername.setFont(new Font("Lato", Font.BOLD, 13));
		txtUsername.setForeground(Color.WHITE);
		txtUsername.setOpaque(false);
		txtUsername.setCaretColor(Color.GRAY);
		txtUsername.setDisabledTextColor(Color.WHITE);
		txtUsername.setBorder(null);
		txtUsername.setBackground(Color.DARK_GRAY);
		txtUsername.setBounds(734, 317, 477, 52);
		panel.add(txtUsername);
		txtUsername.setColumns(10);	
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.inactiveCaptionBorder);
		separator_1.setBounds(734, 449, 477, 2);
		panel.add(separator_1);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBorder(null);
		pwdPassword.setForeground(Color.WHITE);
		pwdPassword.setFont(new Font("Lato", Font.BOLD, 13));
		pwdPassword.setOpaque(false);
		pwdPassword.setBounds(734, 399, 477, 52);
		panel.add(pwdPassword);	
		
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/src/main/resources/AppLogin.png")));
		label.setBounds(0, 0, 1280, 720);
		panel.add(label);
		
		
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
	
	
	public static void main(String[] args) {

		        JFrame frame = new Login();
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        frame.setVisible(true);
	}
}

class HintTextField extends JTextField implements FocusListener {

	private static final long serialVersionUID = 1L;
	private final String hint;
	private boolean showingHint;
	
	public HintTextField(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    super.addFocusListener(this);
	}
	
	 @Override
	 public void focusGained(FocusEvent e) {
	   if(this.getText().isEmpty()) {
	     super.setText("");
	      showingHint = false;
	    }
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	    }
	  }
	
	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
}
