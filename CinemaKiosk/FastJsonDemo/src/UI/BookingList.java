package UI;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.json.bean.MovieArrangement;
import com.json.bean.Seat;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class BookingList extends JFrame implements ActionListener {
	JComboBox comboBox_seat = new JComboBox();	
	JComboBox comboBox_type = new JComboBox();
	private JTextField textScreen;
	JButton btnBack = new JButton("Back");
	JButton addbtn = new JButton("Add one");
	private JTextArea l_message; //the area showing the ticket information	
	private final JButton btnSubmit = new JButton("Submit");
	private final JTextField textTickNum = new JTextField();
	private final JButton btnConfirm = new JButton("Confirm");
	private JLabel imgLabel;
	private Container con;
	JScrollPane JSP;
	
	String s;
	ArrayList<Seat> seatList = new ArrayList<Seat>(); //all seats of that movie
	ArrayList<Seat> selectedSeatList = new ArrayList<Seat>(); //selected seats by the customer
	int ticketNum = 0;
	String studentID;
	double discount = 1.0;
	String ticketInfo = "";
	int width;
	int height = 0;
	boolean click = false;
	boolean close = false;
	double total = 0;
	String filename = null;
	MovieArrangement selMovie = new MovieArrangement();
	
	public BookingList(final String fileName, MovieArrangement selMovie){
		this.selMovie = selMovie;
		filename = fileName;
		init();
	}
	
	public void init(){
		this.setTitle(selMovie.getName()+","+selMovie.getScreen()+" at "+selMovie.getStartTime());
		ImageIcon img = new ImageIcon(".//image//BG.jpg");
		imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = this.getContentPane();
		((JPanel)con).setOpaque(false);
		
		textTickNum.setText("Ticket 1");
		textTickNum.setBackground(SystemColor.window);
		textTickNum.setEditable(false);
		textTickNum.setColumns(10);
		this.setTitle(selMovie.getName()+" "+selMovie.getStartTime()+" "+selMovie.getScreen());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cp=this.getContentPane();
		
		l_message = new JTextArea();
		l_message.setEditable(false);
		l_message.setColumns(10);
		JSP = new JScrollPane(l_message);
		
		textScreen = new JTextField();
		textScreen.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textScreen.setHorizontalAlignment(SwingConstants.CENTER);
		textScreen.setText("SCREEN");
		textScreen.setEditable(false);
		textScreen.setBackground(Color.GRAY);
		textScreen.setColumns(10);
		
		JLabel lblSelectASeat = new JLabel("Select a seat:");	
		JLabel lblSelect = new JLabel("Select a type:");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(838, Short.MAX_VALUE)
					.addComponent(btnConfirm)
					.addGap(267))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBack)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textScreen, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textTickNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSelectASeat)
										.addComponent(addbtn)
										.addComponent(lblSelect))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnSubmit, Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(47)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(comboBox_type, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(comboBox_seat, Alignment.LEADING, 0, 126, Short.MAX_VALUE)))))
								.addComponent(l_message, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE))
							.addGap(227))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(textTickNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelectASeat)
								.addComponent(comboBox_seat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelect))
							.addGap(24)
							.addComponent(btnConfirm)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(l_message, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSubmit)
								.addComponent(addbtn)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(textScreen, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addGap(96))
		);
		comboBox_type.addItem("Adult");
		comboBox_type.addItem("Child");
		comboBox_type.addItem("Senior");
		comboBox_type.addItem("Student");
		btnBack.addActionListener(this);
		btnConfirm.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) { 
		    	String ticketType = "";
		    	Seat selectedSeat = new Seat();
		    	ticketNum++;
		    	
		    	if (!click){
		    		if (comboBox_type.getSelectedItem() != null&&comboBox_type.getSelectedItem().equals("Student")){
			        	while(true){
			        		studentID = JOptionPane.showInputDialog("Please input your ID:");
			        		if (studentID.length()>0){
			        			break;
			        		}
			        	}
			        	ticketType = "1";
			        	discount = 1-0.15;
			        }
			        else if (comboBox_type.getSelectedItem() != null&&comboBox_type.getSelectedItem().equals("Child")){
			        	s = JOptionPane.showInputDialog("Please input your age:");
			        	while(true){
			        		try{
			        			int age = Integer.parseInt(s);
			        			if(Integer.parseInt(s)<17&&Integer.parseInt(s)>2){
					        		JOptionPane.showMessageDialog(null,"OK.");
						        	ticketType = "2";
						        	discount = 1-0.5;
					        		break;
					        	}else{
					        		JOptionPane.showMessageDialog(null,"You are not allow to buy a child ticket.");
					        		ticketType = "-1";
					        		break;
					        	}
			        		}catch(Exception e1){
			        			s = JOptionPane.showInputDialog("Invalid input. Please input your age again:");
			        		}
			        	}
			        }
			        else if (comboBox_type.getSelectedItem() != null&&comboBox_type.getSelectedItem().equals("Senior")){
			        	s = JOptionPane.showInputDialog("Please input your age:");
			        	while(true){
			        		try{
			        			int age = Integer.parseInt(s);
			        			if(Integer.parseInt(s)<100&&Integer.parseInt(s)>55){
					        		JOptionPane.showMessageDialog(null,"OK.");
						        	ticketType = "3";
						        	discount = 1-0.2;
					        		break;
					        	}else{
					        		JOptionPane.showMessageDialog(null,"You are not allow to buy a senior ticket.");
					        		ticketType = "-1";
					        		break;
					        	}
			        		}catch(Exception e1){
			        			s = JOptionPane.showInputDialog("Invalid input. Please input your age again:");
			        		}
			        	}
			        }
			        else{
			        	ticketType = "4";
			        }
			        //Select the selected seat from the available seat list
		    		if (!(ticketType.equals("-1"))){
		    			for (int i = 0;i<seatList.size();i++){
				        	if((""+seatList.get(i).getRow()+seatList.get(i).getColumn()).equals(comboBox_seat.getSelectedItem())){
				        		seatList.get(i).setType(ticketType);
				        		seatList.get(i).initTickId();
				        		selectedSeat = seatList.get(i);
				        		if (ticketType.equals("1")){ //Student ticket
				        			selectedSeat.setStudentID(studentID);
				        			selectedSeat.setTicketPrice(16*discount);
				        		}else if (ticketType.equals("2")){//child ticket
				        			selectedSeat.setTicketPrice(16*discount);
				        		}else if (ticketType.equals("3")){//senior ticket
				        			selectedSeat.setTicketPrice(16*discount);
				        		}
				        		total+=selectedSeat.getTicketPrice();
				        		selectedSeatList.add(selectedSeat);
				        		break;
				        	}
				        }
				        ticketInfo +="Seat: "+ comboBox_seat.getSelectedItem() + "\r\nType:" + comboBox_type.getSelectedItem() 
				        		+ "\r\nPrice: "+ selectedSeat.getTicketPrice() 
				        		+ "\r\nTicket ID:" + selectedSeat.getTicketId()+"\r\n\r\n";
				        l_message.setText(ticketInfo);	
				        selectedSeat.getLabel().setBorder(new LineBorder(Color.blue, 150));
				        click = true;
				        comboBox_type.setEnabled(false);
				        comboBox_seat.setEnabled(false);
		    		}       
			    }
		    	else
		    		JOptionPane.showMessageDialog(null,"Confirmed.");
		    }
		    	
		        
		});
		
		addbtn.addActionListener(this);
		    
		btnSubmit.addActionListener(this);
		
		btnConfirm.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) { 
		    	close = true;
		    }
		});
		
		getContentPane().setLayout(groupLayout);
		((JPanel)cp).setOpaque(false);
		ImageIcon icon = new ImageIcon(".//image//seat.jpeg");
		ImageIcon icon2 = new ImageIcon(".//image//selected.jpg");

		File file = new File(filename);
		BufferedReader reader = null;
		String input;
		int x=50;
		int y=100;
		String seatStatus;
		char row = 'A';
		int column = 0;
		JLabel label;
		Seat seat;

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		JLabel rowLabel;
		
		try {
			while((input=reader.readLine())!=null){	
//				height ++;
				width = input.length();
				x=30;
				column = 0;
				rowLabel = new JLabel(""+row);
				rowLabel.setToolTipText("row:"+row);
				rowLabel.setBounds(20,y+5,10,20);
				this.getLayeredPane().add(rowLabel);
				
				for(int i = 0;i < width;i++){
					seatStatus = input.substring(i,i+1);
					if(seatStatus.equals("0")){
						x=x+45;
						column ++;
						seat = new Seat();
						seat.setColumn(column);
						seat.setRow(row);
						label = new JLabel(icon);
						seat.setLabel(label);
						seat.getLabel().setBounds(x,y,icon.getIconWidth(), icon.getIconHeight());
						seat.getLabel().setToolTipText(""+row+column+" available");
						this.getLayeredPane().add(seat.getLabel(), new Integer(Integer.MAX_VALUE)); 
//						System.out.println(seat.toString());
						seatList.add(seat);
						comboBox_seat.addItem(""+row+column);
 
					}else if (seatStatus.equals("x")){
						x=x+45;
						seat = new Seat();
						seat.setType(seatStatus);
						seatList.add(seat);
					}else{
						x=x+45;
						column ++;
						seat = new Seat();
						seat.setColumn(column);
						seat.setRow(row);
						seat.setType(seatStatus);
						label = new JLabel(icon2);
						seat.setLabel(label);
						seat.getLabel().setBounds(x,y,icon.getIconWidth(), icon.getIconHeight());
						seat.getLabel().setToolTipText(""+row+column+" selected");
						this.getLayeredPane().add(seat.getLabel(), new Integer(Integer.MAX_VALUE)); 
						seatList.add(seat);
					}
					
				}
				y = y+80;
				row++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
//		this.pack();
		this.setSize(1200,800);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.addbtn){
			if (ticketNum>0){
		    	textTickNum.setText("Ticket "+(ticketNum+1));
		    	for (int i = 0;i<seatList.size();i++){
		    		comboBox_seat.removeItem(""+selectedSeatList.get(selectedSeatList.size()-1).getRow()+selectedSeatList.get(selectedSeatList.size()-1).getColumn());
		    	}
		    	comboBox_type.setEnabled(true);
		        comboBox_seat.setEnabled(true);
		    	click = false;
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null,"Please confirm the first ticket you want.");
	    	}
		}
		
		
		if(e.getSource() == this.btnBack){
			LoadMovie a = new LoadMovie();
			this.dispose();
		}
		if(e.getSource()==this.btnSubmit){
			if (ticketNum>0){
		    	String output = "";
		    	int count = 0;
		    	for(int i = 0;i<seatList.size();i++){
		    		output+=seatList.get(i).getType();
		    		count++;
		    		if(count == width){
		    			output+="\r\n";
		    			count = 0;
		    		}
		    	}
				
				Payment p = new Payment(total, filename, output, selectedSeatList, selMovie);
				this.dispose();
	    	}
	    	else{
	    		JOptionPane.showMessageDialog(null,"Please confirm the first ticket you want.");
	    	}
		}
	}
}
