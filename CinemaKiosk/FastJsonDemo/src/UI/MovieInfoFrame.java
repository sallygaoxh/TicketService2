package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.json.bean.MovieArrangement;
import com.json.bean.MovieInfo;
import javax.swing.SwingConstants;

public class MovieInfoFrame extends JFrame{
	String moviename;
	private JLabel imgLabel;
	private Container con;
	
	public MovieInfoFrame (MovieArrangement movie){
		getContentPane().setLayout(null);
		moviename = movie.getName();
		init();
	}

	public void init(){
		ImageIcon img = new ImageIcon(".//image//BG.jpg");
		imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		con = this.getContentPane();
		((JPanel)con).setOpaque(false);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(53, 0, 194, 325);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 182, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setText(moviename);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(6, 337, 288, 235);
		getContentPane().add(textArea);
		
		String info=null;
		MovieInfo a = new MovieInfo();
		for(int i = 0;i<a.movieInfo.length;i++){
			if(a.movieInfo[i][0].equals(moviename))
				info = a.movieInfo[i][1];
		}
		textArea.setLineWrap(true);
		textArea.setText(info);
		textArea.setOpaque(false);
		JLabel label = new JLabel("");
		label.setBounds(53, 35, 194, 290);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(".//image//"+moviename+".png"));
		
		this.setSize(300,600);
		this.setVisible(true);
	}
}
