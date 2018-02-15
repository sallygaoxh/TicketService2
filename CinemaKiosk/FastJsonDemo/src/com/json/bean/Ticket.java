package com.json.bean;

public class Ticket {
	String movieName;
	String ticketType;
	double price = 16.0;
	
	public Ticket(String movieName,String ticketType){
		this.movieName = movieName;
		this.ticketType = ticketType;
	}
	
	public String getMovieName(){
		return this.movieName;
	}
	
	public void setPrice(){
		if(this.ticketType.equals("1")){
			//student ticket
			this.price = 16.0 *0.85;
		}else if(this.ticketType.equals("2")){
			//child ticket
			this.price = 16.0 *0.5;
		}
		else if(this.ticketType.equals("3")){
			//senior ticket
			this.price = 16.0 *0.8;
		}
	}
	
	public String getTicketType(){
		return this.ticketType;
	}
	public double getPrice(){
		return this.price;
	}
	
	public String toString(){
		return this.movieName+","+this.ticketType+","+this.price;
	}
}
