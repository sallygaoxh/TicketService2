package com.json.bean;

import javax.swing.JLabel;

public class Seat{
	int column;
	char row;
	String type = "0"; //0-empty 1-student 2-child 3-senior 4-adult,x-no seat
	String studentID; //only valid for student
	String ticketId = "";//only valid when seat is occupied
 	JLabel label;
 	double ticketPrice = 16.0;
	
	public Seat(){
		
	}
	
	public void setColumn(int c){
		this.column = c;
	}
	public int getColumn(){
		return this.column;
	}
	public void setRow(char row){
		this.row = row;
	}
	public char getRow(){
		return this.row;
	}
	public void setLabel(JLabel label){
		this.label = label;
	}
	public JLabel getLabel(){
		return this.label;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setTicketId(String ID){
		this.ticketId = ID;
	}
	public String getTicketId(){
		return this.ticketId;
	}
	public void initTickId(){
		int lastNum;
		for (int i = 0;i<8;i++){
			lastNum = (int)(1+Math.random()*(4-1+1));//(数据类型)(最小值+Math.random()*(最大值-最小值+1))
			this.ticketId+=lastNum;
		}
	}
	public void setStudentID (String studentID){
		this.studentID = studentID;
	}
	public String getStudentID(){
		return this.studentID;
	}
	public void setTicketPrice(double price){
		this.ticketPrice = price;
	}
	public double getTicketPrice(){
		return this.ticketPrice;
	}

	public String toString(){
		return ""+this.row+this.column;
	}
}
