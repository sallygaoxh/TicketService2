package com.json.bean;

public class MovieArrangement {
	String name;
	String startTime;
	String screen;
	String duration;
	
	public MovieArrangement(String name, String startTime, String screen, String duration){
		this.name = name;
		this.startTime = startTime;
		this.screen = screen;
		this.duration = duration;
	}
	
	public MovieArrangement(){
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setStartTime(String time){
		this.startTime = time;
	}
	public String getStartTime(){
		return this.startTime;
	}
	public void setScreen(String screen){
		this.screen = screen;
	}
	public String getScreen(){
		return this.screen;
	}
	public String getDuration(){
		return this.duration;
	}
	public void setDuration(String duration){
		this.duration = duration;
	}
	
	public String toString(){
		return "Movie name: "+this.name + " ,screen: "+ this.screen +" ,start time: "+this.startTime + ", duration:"+this.duration;
	}
}


