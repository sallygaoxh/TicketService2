package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.alibaba.fastjson.JSON;
import com.json.bean.MovieArrangement;
import com.json.bean.Ticket;
import com.json.bean.Arrangement;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Manager extends JFrame implements ActionListener{
	ArrayList<MovieArrangement> movieList = new ArrayList<MovieArrangement>();
	JButton btnExport = new JButton("Export Statistics");
	JButton btnBack;
	private JLabel imgLabel;
	private Container con;
	
	//Constructor
	public Manager(){			
		movieList = this.loadData();
		String[][] data = new String [15][4];
		
		this.setTitle("Managing Movie List");  //创建顶级容器  
		ImageIcon img = new ImageIcon(".//image//BG.jpg");
		imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = this.getContentPane();
		((JPanel)con).setOpaque(false);
		
    	String ColumnNames[] = {"MovieName", "Starting Time","Screen Assignment","Duration(min)"};
    	
        for (int i = 0;i<movieList.size();i++){
        	data[i][0] = movieList.get(i).getName();
        	data[i][1] = movieList.get(i).getStartTime();
        	data[i][2] = movieList.get(i).getScreen();
        	data[i][3] = movieList.get(i).getDuration(); 			
        }
    	DefaultTableModel tableModel = new DefaultTableModel(data, ColumnNames) ;

        final JTable table = new JTable(tableModel);
        table.setEnabled(false);
        
        table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setRowHeight(30);
        TableColumnModel columns = table.getColumnModel(); 
        TableColumn column = columns.getColumn(0); 
        column.setPreferredWidth(200);
        column = columns.getColumn(1);
        column.setPreferredWidth(100);
        column = columns.getColumn(2);
        column.setPreferredWidth(150);      
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setOpaque(false);
        JScrollPane JSP= new JScrollPane(table);     
        JSP.setBounds(20,80,650,500);
        JSP.setOpaque(false);
        JSP.getViewport().setOpaque(false);
        JSP.setBorder(BorderFactory.createEmptyBorder());
        this.getLayeredPane().add(JSP);
    	
    	JLabel lblNewLabel = new JLabel("Self-service ticketing kiosk");
    	lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
    	lblNewLabel.setBounds(200,0,300,80);
    	this.getLayeredPane().add(lblNewLabel);
    	JButton btnIniti = new JButton("Initiate Screen Arrangement");
    	btnIniti.addActionListener(new ActionListener() {              
            public void actionPerformed (ActionEvent arg1) { 
            	for(int i =0 ;i<movieList.size();i++){
            		String fwrite = ".//movies//"+movieList.get(i).getName()+"_"+movieList.get(i).getScreen()
            				+"_"+movieList.get(i).getStartTime()+".txt";
            		fwrite = fwrite.replaceAll(":","-");
//            		System.out.println(fwrite);
            		File fread = null;
					
            		String input,output;
            		
            		if(movieList.get(i).getScreen().equals("Screen1")){
            			fread = new File(".//Reset//Screen1.txt");
            		}else if(movieList.get(i).getScreen().equals("Screen2")){
            			fread = new File(".//Reset//Screen2.txt");
            		}else{
            			fread = new File(".//Reset//Screen3.txt");
            		}
            		BufferedReader reader1 = null;
            		BufferedWriter out = null;
            		try {
            			reader1 = new BufferedReader(new FileReader(fread));
            			out =new BufferedWriter(new FileWriter(fwrite));
						while((input=reader1.readLine())!=null){
//							System.out.println(input);
							out.write(input);
							out.newLine();
						}
						reader1.close();
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}         		
            	}
            	JOptionPane.showMessageDialog(null, "OK.");
            }  
        });
    	
    	btnExport.addActionListener(this);
    	btnBack = new JButton("Back");
    	btnBack.addActionListener(this);
    	
		this.getLayeredPane().add(btnExport, new Integer(Integer.MAX_VALUE));
		this.getLayeredPane().add(btnBack, new Integer(Integer.MAX_VALUE));
		this.getLayeredPane().add(btnIniti, new Integer(Integer.MAX_VALUE));
		
		btnExport.setBounds(450,600,200,30);
		btnIniti.setBounds(450,560,200,30);
		btnBack.setBounds(70,560,100,30);

        this.setSize(700, 700);
        this.setVisible(true);
	}
	
	public ArrayList<MovieArrangement> loadData(){
//		File file = new File(".//arrangement.json");
//		BufferedReader reader = null;
//		String input;
		ArrayList<MovieArrangement> movieList = new ArrayList<MovieArrangement>();
		
		String[] arrangement = new Arrangement().arrangement;
		for (int i = 0;i<arrangement.length;i++){
			MovieArrangement movie = new MovieArrangement();
			String arrange[] = arrangement[i].split(",");
			String name = arrange[0].split(":")[1];
			movie.setName(name);
			String screen = arrange[1].split(":")[1];
			movie.setScreen(screen);
			String startTime = arrange[2].split(":")[1]+":"+arrange[2].split(":")[2];
			movie.setStartTime(startTime);
			String duration = arrange[3].split(":")[1];
			movie.setDuration(duration);
			movieList.add(movie);
		}
		
//		try {
//			reader = new BufferedReader(new FileReader(file));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		
//		try {
//			while((input=reader.readLine())!=null){	
//				movie = JSON.parseObject(input,MovieArrangement.class);
//				movieList.add(movie);
//				}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			reader.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		System.out.println(movieList.get(0).toString());
		return movieList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		int studentTicket = 0,childTicket = 0,seniorTicket = 0,adultTicket = 0;
		double sale1 = 0, sale2 = 0, sale3 = 0, sale4 = 0, sale5 = 0; 
		//1-BEAUTY AND THE BEAST 2-KONG: SKULL ISLAND 3-MOONLIGHT 4-LOGAN 5-LA LA LAND
		
		if(e.getSource() == this.btnExport){
			for (int i = 0; i<movieList.size();i++){
				String fileName = ".//movies//" +movieList.get(i).getName()+"_"+movieList.get(i).getScreen()
        				+"_"+movieList.get(i).getStartTime()+".txt";
				fileName = fileName.replaceAll(":", "-");
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				String input = null;
				int width;
				String seatStatus;
				try {
					while((input=reader.readLine())!=null){	
						width = input.length();
						for(int j = 0;j < width; j++){
							seatStatus = input.substring(j,j+1);
							if (seatStatus.equals("0")||seatStatus.equals("x"))
								;
							else{
								Ticket ticket = new Ticket (movieList.get(i).getName(),seatStatus);
								ticket.setPrice();
								ticketList.add(ticket);
							}
						}
					}
					reader.close();	
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			//different ticket type
			for (int i1 = 0;i1<ticketList.size();i1++){
				System.out.println(ticketList.get(i1).toString());
				if (ticketList.get(i1).getTicketType().equals("1")){
					studentTicket++;
				}else if (ticketList.get(i1).getTicketType().equals("2")){
					childTicket++;
				}else if (ticketList.get(i1).getTicketType().equals("3")){
					seniorTicket++;
				}else if (ticketList.get(i1).getTicketType().equals("4")){
					adultTicket++;
				}else
					;
				
				if (ticketList.get(i1).getMovieName().equals("BEAUTY AND THE BEAST"))
					sale1+=ticketList.get(i1).getPrice();
				else if (ticketList.get(i1).getMovieName().equals("KONG: SKULL ISLAND"))
					sale2+=ticketList.get(i1).getPrice();
				else if (ticketList.get(i1).getMovieName().equals("MOONLIGHT"))
					sale3+=ticketList.get(i1).getPrice();
				else if (ticketList.get(i1).getMovieName().equals("LOGAN"))
					sale4+=ticketList.get(i1).getPrice();
				else if (ticketList.get(i1).getMovieName().equals("LA LA LAND"))
					sale5+=ticketList.get(i1).getPrice();
				else
					;
			}
			
			String fileName = ".//output.txt";
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
				out.write("Total sale of BEAUTY AND THE BEAST: "+sale1);
				out.newLine();
				out.write("Total sale of KONG: SKULL ISLAND: "+sale2);
				out.newLine();
				out.write("Total sale of MOONLIGHT: "+sale3);
				out.newLine();
				out.write("Total sale of LOGAN: "+sale4);
				out.newLine();
				out.write("Total sale of LA LA LAND: "+sale5);
				out.newLine();
				out.write("Total number of tickets sold: "+ticketList.size());
				out.newLine();
				out.write("Total sale of student tickets: "+ studentTicket);
				out.newLine();
				out.write("Total sale of child tickets: "+childTicket);
				out.newLine();
				out.write("Total sale of senior tickets: "+seniorTicket);
				out.newLine();
				out.write("Total sale of adult tickets: "+adultTicket);
				out.newLine();
				
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "OK.");
		}
		
		if(e.getSource()==btnBack){
			this.dispose();
		}
		
	}
}
