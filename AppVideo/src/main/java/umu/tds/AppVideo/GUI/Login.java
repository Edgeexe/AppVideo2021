package umu.tds.AppVideo.GUI;

import javax.swing.*;
import javax.swing.border.Border;

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
	private JTextField textField;
	
	public Login() {
		setBounds(new Rectangle(100, 100, 1280, 720));
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
		
		JButton btnSignUp = new JButton("Sign up");
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
		txtUsername.setText("Username");
		txtUsername.setBounds(734, 317, 477, 52);
		panel.add(txtUsername);
		txtUsername.setColumns(10);	
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(SystemColor.inactiveCaptionBorder);
		separator_1.setBounds(734, 449, 477, 2);
		panel.add(separator_1);
		
		textField = new HintTextField("Password");
		textField.setOpaque(false);
		textField.setMargin(new Insets(2, 20, 2, 2));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Lato", Font.BOLD, 13));
		textField.setDisabledTextColor(Color.WHITE);
		textField.setColumns(10);
		textField.setCaretColor(Color.GRAY);
		textField.setBorder(null);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(734, 399, 477, 52);
		panel.add(textField);
		
		
		
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

		        JFrame frame = new Login(); // GUI gui = new GUI() as well
		        // default value JFrame.HIDE_ON_CLOSE
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

class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
