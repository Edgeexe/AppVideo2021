package umu.tds.AppVideo.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;


public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private int posX=0;
	private int	posY=0;
	private JTextField escribir_busqueda;

	public static void main(String[] args) {

        JFrame frame = new VentanaPrincipal();
        frame.setVisible(true);
}
	
	public VentanaPrincipal() {
		setBounds(new Rectangle(100, 100, 1280, 720));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel_Explorar = new JPanel();
		panel_Explorar.setBounds(207, 96, 1073, 624);
		getContentPane().add(panel_Explorar);
		panel_Explorar.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_este = new JPanel();
		panel_este.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_este.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_este, BorderLayout.EAST);
		panel_este.setLayout(new BoxLayout(panel_este, BoxLayout.Y_AXIS));
		
		JList<String> list = new JList<String>();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = -7605725966827348811L;
			String[] values = new String[] {"Agfsgsbs", "Bsfsaavv", "vzvzbbsC", "Dcdssca", "EcsCCvf", "cscscjxF", "aXXACdfG"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		panel_este.add(list);
		
		panel_este.add(Box.createRigidArea(new Dimension(90,90)));
		
		JTextPane textPane = new JTextPane();
		panel_este.add(textPane);
		
		JPanel panel_busqueda = new JPanel();
		panel_busqueda.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_busqueda.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_busqueda, BorderLayout.NORTH);
		panel_busqueda.setLayout(new BoxLayout(panel_busqueda, BoxLayout.X_AXIS));
		
		JLabel lbl_busqueda = new JLabel("Búsqueda: ");
		panel_busqueda.add(lbl_busqueda);
		panel_busqueda.add(Box.createRigidArea(new Dimension(20,30)));
		
		escribir_busqueda = new JTextField();
		panel_busqueda.add(escribir_busqueda);
		escribir_busqueda.setColumns(10);
		
		JButton boton_buscar = new JButton("Buscar");
		panel_busqueda.add(boton_buscar);
		
		panel_busqueda.add(Box.createRigidArea(new Dimension(30,30)));
		
		JButton btn_Reset = new JButton("Reset");
		panel_busqueda.add(btn_Reset);
		
		JPanel panel_resultado = new JPanel();
		panel_resultado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_resultado.setBackground(Color.LIGHT_GRAY);
		panel_Explorar.add(panel_resultado, BorderLayout.CENTER);
		panel_resultado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton boton_X = new JButton("");
		boton_X.setIcon(new ImageIcon(Login.class.getResource("/src/main/resources/Close button.png")));
		boton_X.setBackground(Color.BLACK);
		boton_X.setForeground(Color.WHITE);
		boton_X.setFont(new Font("Dialog", Font.BOLD, 30));
		boton_X.setBorder(null);
		boton_X.setBounds(1240, 0, 40, 26);
		boton_X.setFocusable(false);
		getContentPane().add(boton_X);
		
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
		getContentPane().add(boton_Minimizar);
		
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
		
		JPanel panel_cabecera = new JPanel();
		panel_cabecera.setOpaque(false);
		panel_cabecera.setBorder(null);
		panel_cabecera.setBounds(0, 0, 1280, 95);
		getContentPane().add(panel_cabecera);
		panel_cabecera.setLayout(null);
		
		JButton boton_Logout = new JButton("Logout");
		boton_Logout.setBounds(1081, 33, 79, 29);
		panel_cabecera.add(boton_Logout);
		
		JButton boton_Premium = new JButton("Premium");
		boton_Premium.setBounds(1170, 33, 90, 29);
		boton_Premium.setForeground(Color.RED);
		panel_cabecera.add(boton_Premium);
		
		textField = new JTextField();
		textField.setBounds(848, 29, 223, 37);
		textField.setText("Hola + nombre de usuario");
		textField.setSelectionColor(SystemColor.menu);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(SystemColor.menu);
		panel_cabecera.add(textField);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/src/main/resources/LogoNoText.png")));
		label_1.setBounds(56, 11, 100, 95);
		panel_cabecera.add(label_1);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setOpaque(false);
		panel_botones.setBorder(null);
		panel_botones.setBounds(0, 106, 206, 614);
		getContentPane().add(panel_botones);
		panel_botones.setLayout(null);
		
		JButton boton_Explorar = new JButton("        Explorar");
		boton_Explorar.setMargin(new Insets(2, 25, 2, 14));
		boton_Explorar.setRequestFocusEnabled(false);
		boton_Explorar.setHorizontalAlignment(SwingConstants.LEFT);
		boton_Explorar.setContentAreaFilled(false);
		boton_Explorar.setBorderPainted(false);
		boton_Explorar.setOpaque(false);
		boton_Explorar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/src/main/resources/HomeIcon.png")));
		boton_Explorar.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Explorar.setForeground(Color.WHITE);
		boton_Explorar.setBounds(0, 139, 206, 46);
		panel_botones.add(boton_Explorar);
		
		boton_Explorar.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_Explorar.setContentAreaFilled(true);
		    	boton_Explorar.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_Explorar.setContentAreaFilled(false);		    
		    	}
		    
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					panel_Explorar.setVisible(true);
				}
			}
		});
		
		JButton boton_mis_listas = new JButton("        Mis Listas");
		boton_mis_listas.setMargin(new Insets(2, 25, 2, 14));
		boton_mis_listas.setOpaque(false);
		boton_mis_listas.setRequestFocusEnabled(false);
		boton_mis_listas.setHorizontalAlignment(SwingConstants.LEFT);
		boton_mis_listas.setContentAreaFilled(false);
		boton_mis_listas.setBorderPainted(false);
		boton_mis_listas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/src/main/resources/LibraryIcon.png")));
		boton_mis_listas.setForeground(Color.WHITE);
		boton_mis_listas.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_mis_listas.setBounds(0, 185, 206, 46);
		panel_botones.add(boton_mis_listas);
		
		boton_mis_listas.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_mis_listas.setContentAreaFilled(true);
		    	boton_mis_listas.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_mis_listas.setContentAreaFilled(false);		    
		    }
		    
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					panel_Explorar.setVisible(false);
				}
			}
		});
		
		JButton boton_Recientes = new JButton("        Recientes");
		boton_Recientes.setMargin(new Insets(2, 25, 2, 14));
		boton_Recientes.setOpaque(false);
		boton_Recientes.setRequestFocusEnabled(false);
		boton_Recientes.setHorizontalAlignment(SwingConstants.LEFT);
		boton_Recientes.setContentAreaFilled(false);
		boton_Recientes.setBorderPainted(false);
		boton_Recientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/src/main/resources/RecentsIcon.png")));
		boton_Recientes.setForeground(Color.WHITE);
		boton_Recientes.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Recientes.setBounds(0, 230, 206, 46);
		panel_botones.add(boton_Recientes);
		
		boton_Recientes.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_Recientes.setContentAreaFilled(true);
		    	boton_Recientes.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_Recientes.setContentAreaFilled(false);		    
		    }
		});
		
		JButton boton_Nueva_Lista = new JButton("        Nueva Lista");
		boton_Nueva_Lista.setMargin(new Insets(2, 25, 2, 14));
		boton_Nueva_Lista.setOpaque(false);
		boton_Nueva_Lista.setRequestFocusEnabled(false);
		boton_Nueva_Lista.setHorizontalAlignment(SwingConstants.LEFT);
		boton_Nueva_Lista.setContentAreaFilled(false);
		boton_Nueva_Lista.setBorderPainted(false);
		boton_Nueva_Lista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/src/main/resources/NewListIcon.png")));
		boton_Nueva_Lista.setForeground(Color.WHITE);
		boton_Nueva_Lista.setFont(new Font("Lato Black", Font.BOLD, 15));
		boton_Nueva_Lista.setBounds(0, 275, 206, 46);
		panel_botones.add(boton_Nueva_Lista);
		
		boton_Nueva_Lista.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	boton_Nueva_Lista.setContentAreaFilled(true);
		    	boton_Nueva_Lista.setBackground(new Color(41,41,41));
		    }

		    public void mouseExited(MouseEvent evt) {
		    	boton_Nueva_Lista.setContentAreaFilled(false);		    
		    }
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/src/main/resources/AppBG.png")));
		label.setBounds(0, 0, 1280, 720);
		getContentPane().add(label);
		
		
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
