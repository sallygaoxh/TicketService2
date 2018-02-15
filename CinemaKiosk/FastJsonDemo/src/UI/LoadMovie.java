package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.alibaba.fastjson.JSON;
import com.json.bean.Arrangement;
import com.json.bean.Layout;
import com.json.bean.MovieArrangement;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class LoadMovie extends JFrame implements ActionListener{
	private static JTable table;
	JButton btn, infoBtn;
	private JLabel imgLabel;
	private Container con;
	
	public LoadMovie() {
		this.setTitle("Movie List");
		ImageIcon img = new ImageIcon(".//image//BG.jpg");
		imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = this.getContentPane();
		((JPanel)con).setOpaque(false);
		
		//reading files
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
			
			long timemillis = System.currentTimeMillis();   
	         //转换日期显示格式   
	         SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	         String str = df.format(new Date(timemillis));
	        
	         String[] timeNow, timeMovie;
	         timeNow = str.split(":");
	         timeMovie = startTime.split(":");
	         if(Integer.parseInt(timeNow[0])<Integer.parseInt(timeMovie[0])){
	        		 movieList.add(movie);
	         }else if (Integer.parseInt(timeNow[0])==Integer.parseInt(timeMovie[0])){
	        	 if(Integer.parseInt(timeNow[1])<=Integer.parseInt(timeMovie[1]))
	        		 movieList.add(movie);
	         }
		}
//		try {
//			reader = new BufferedReader(new FileReader(file));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
//		try {
//			while((input=reader.readLine())!=null){	
//				movie = JSON.parseObject(input,MovieArrangement.class);
//				long timemillis = System.currentTimeMillis();   
//		         //转换日期显示格式   
//		         SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//		         String str = df.format(new Date(timemillis));
//		        
//		         String[] timeNow, timeMovie;
//		         timeNow = str.split(":");
//		         timeMovie = movie.getStartTime().split(":");
//		         if(Integer.parseInt(timeNow[0])<Integer.parseInt(timeMovie[0])){
//		        		 movieList.add(movie);
//		         }else if (Integer.parseInt(timeNow[0])==Integer.parseInt(timeMovie[0])){
//		        	 if(Integer.parseInt(timeNow[1])<=Integer.parseInt(timeMovie[1]))
//		        		 movieList.add(movie);
//		         }
//		        	 
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

		String[][] data = new String [movieList.size()][4];     	
    	String ColumnNames[] = {"MovieName", "Starting Time","Screen Assignment","Duration(min)"};
    	
        for (int i = 0;i<movieList.size();i++){
        	data[i][0] = movieList.get(i).getName();
        	data[i][1] = movieList.get(i).getStartTime();
        	data[i][2] = movieList.get(i).getScreen();
        	data[i][3] = movieList.get(i).getDuration(); 			
        }
    	DefaultTableModel tableModel = new DefaultTableModel(data, ColumnNames) ;

        table = new JTable(tableModel){
        	public boolean isCellEditable(int row, int column){
        		return false;
        	}
        };        
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
    	
    	btn = new JButton("Book");
    	btn.addActionListener(this);  
    	this.getLayeredPane().add(btn);
    	btn.setBounds(450,600,100,30);
    	
    	infoBtn = new JButton("information");
    	infoBtn.addActionListener(this);  
    	this.getLayeredPane().add(infoBtn);
    	infoBtn.setBounds(150,600,100,30);
    	
        this.setSize(700, 700);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn){
			int sel = table.getSelectedRow();
            if(sel == -1){
            	JOptionPane.showMessageDialog(null, "Please select a movie you want to watch.");
            }else{
                MovieArrangement selMovie = new MovieArrangement((String)table.getValueAt(sel, 0), (String)table.getValueAt(sel, 1),
                		(String)table.getValueAt(sel, 2),(String)table.getValueAt(sel, 3));      
                String s = selMovie.getName()+"_"+selMovie.getScreen()+"_"+selMovie.getStartTime()+".txt";
                s = s.replaceAll(":","-");
                System.out.println(""+sel);
                JOptionPane.showMessageDialog(null, "You have select the movie "+selMovie.getName()+"_"+selMovie.getScreen()+" at "+selMovie.getStartTime());
//                LoadScreen screen = new LoadScreen(selMovie);
                BookingList bookingList = new BookingList(".//movies//"+s,selMovie);
                this.dispose();
            }
		}
		
		if(e.getSource()==infoBtn){
			int sel = table.getSelectedRow();
            if(sel == -1){
            	JOptionPane.showMessageDialog(null, "Please select a movie you want to watch.");
            }else{
                MovieArrangement selMovie = new MovieArrangement((String)table.getValueAt(sel, 0), (String)table.getValueAt(sel, 1),
                		(String)table.getValueAt(sel, 2),(String)table.getValueAt(sel, 3));      
    			MovieInfoFrame info = new MovieInfoFrame(selMovie);
            }
		}
	}
}
