package UI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.json.bean.MovieArrangement;
import com.json.bean.Seat;

public class Payment extends JFrame implements ActionListener{
	private JTextField cardNum;
	private JPasswordField password;
	private JTextField total;
	JButton btnBack = new JButton("Back");
	JButton btnConfirm = new JButton("Confirm");
	String output;
	String filename;
	ArrayList<Seat> selectedList = new ArrayList<Seat>();
	MovieArrangement selMovie = null;
	
	public Payment(double amount, String filename, String output, ArrayList selectedSeatList, MovieArrangement selMovie){	
		this.filename = filename;
		this.output = output;
		this.selectedList = selectedSeatList;
		this.selMovie = selMovie;
		this.setTitle("Payment");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp=this.getContentPane();
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		
		JLabel lblCardType = new JLabel("Card Type:");
		
		JComboBox comboBox = new JComboBox();
		
		cardNum = new JTextField();
		cardNum.setColumns(10);
		cardNum.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });  
		
		JLabel lblPassword = new JLabel("Password:");
		
		password = new JPasswordField();
		password.setColumns(10);
		password.addKeyListener(new KeyAdapter(){  
            public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }  
        });  

		btnConfirm.addActionListener((ActionListener) this);
		
		JLabel lblTotalAmount = new JLabel("Total Amount:");
		
		total = new JTextField();
		total.setText("$"+amount);
		total.setBackground(SystemColor.window);
		total.setEditable(false);
		total.setColumns(10);
		
		btnBack.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalAmount)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCardType)
								.addComponent(lblCardNumber)
								.addComponent(lblPassword)
								.addComponent(btnBack))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cardNum)
								.addComponent(password)
								.addComponent(btnConfirm, Alignment.TRAILING)
								.addComponent(total)
								.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCardType)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCardNumber)
						.addComponent(cardNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalAmount)
						.addComponent(total, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(128)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirm)
						.addComponent(btnBack))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		this.getContentPane().setLayout(groupLayout);
		
		comboBox.addItem("Visa");
		comboBox.addItem("Master");
		comboBox.addItem("Union Pay");		
		
		this.setSize(300,600);
		this.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBack){
			LoadMovie a = new LoadMovie();
			this.dispose();
		}
		if (e.getSource()==btnConfirm){
	    	if (cardNum.getText().length()==0||password.getPassword().length==0){
	    		JOptionPane.showMessageDialog(null, "Fill in the form to continue payment.");
	    	}else{
	    		JOptionPane.showMessageDialog(null, "Successfully paid.");
	    		System.out.println(output);	
		    	BufferedWriter out;
				try {
					out = new BufferedWriter(new FileWriter(filename));
					out.write(output);
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				for(int i = 0;i<selectedList.size();i++){
					String file = ".//tickets//"+selectedList.get(i).getTicketId()+".txt";
					BufferedWriter out1;
					String ticketType = null;
					if (selectedList.get(i).getType().equals("1"))
						ticketType = "student ticket";
					else if (selectedList.get(i).getType().equals("2"))
						ticketType = "child ticket";
					else if (selectedList.get(i).getType().equals("3"))
						ticketType = "senior ticket";
					else if (selectedList.get(i).getType().equals("4"))
						ticketType = "adult ticket";
					String ticketInfo = "Movie: "+ selMovie.getName() 
							+ "\r\nStarting Time:" + selMovie.getStartTime()
							+ "\r\nSeat: "+ selectedList.get(i).getRow()+selectedList.get(i).getColumn() 
							+ "\r\nType:" + ticketType
			        		+ "\r\nPrice: $"+ selectedList.get(i).getTicketPrice() 
			        		+ "\r\nTicket ID:" + selectedList.get(i).getTicketId()+"\r\n";
					if (ticketType.equals("student ticket"))
						ticketInfo += ("Student ID: "+selectedList.get(i).getStudentID()+"\r\n");
					try {
						out1 = new BufferedWriter(new FileWriter(file));
						out1.write(ticketInfo);
						out1.flush();
						out1.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
	    		this.dispose();
//	    		Welcome a = new Welcome();
	    	}	
		}	
	}
}
