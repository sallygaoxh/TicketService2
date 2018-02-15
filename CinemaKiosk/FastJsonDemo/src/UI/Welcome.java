package UI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class Welcome extends JFrame implements ActionListener{
	JButton manageBtn, customerBtn;
	private JLabel imgLabel;
	private Container con;
	public Welcome(){		
		init();	
	}
	
	public void init(){
		this.setTitle("Welcome to the self-service ticket kiosk! ");
		ImageIcon img = new ImageIcon(".//image//BG.jpg");
		imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		
		manageBtn = new JButton("Log In as A Manager");
		manageBtn.setBounds(150,200,155,44);
		manageBtn.addActionListener(this);  
		customerBtn = new JButton("Log In as A Customer");
		customerBtn.setBounds(150,350,155,44);
		customerBtn.addActionListener(this); 
		
		con = this.getContentPane();
		((JPanel)con).setOpaque(false);
		this.getLayeredPane().add(manageBtn, new Integer(Integer.MAX_VALUE));
		this.getLayeredPane().add(customerBtn, new Integer(Integer.MAX_VALUE));
		
		this.pack();
	    this.setSize(500, 800);
	    this.setVisible(true); 
	    
	}
	
    public static void main(String[] args) {
        Welcome a = new Welcome();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==manageBtn){
			Manager manager = new Manager();
		}
		if(e.getSource()==customerBtn){
			LoadMovie loadMovie = new LoadMovie(); 
		}	   
	}
}
