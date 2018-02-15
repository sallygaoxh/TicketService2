package com.json.bean;

import java.util.ArrayList;

public class Layout {
	private int screenNum ;
	private int row;
	private int column;
	String space = "";
//	String[] spaceList;
	
	public Layout(){
	}
	public Layout(int screenNum,int row,int column){
		this.screenNum=screenNum;
		this.row=row;
		this.column=column;
	}
	public int getScreenNum() {
		return this.screenNum;
	}
	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}
	public int getRow() {
		return this.row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return this.column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setSpace(String space){
		this.space = space;
//		this.spaceList = space.split(",");
	}
	@Override
	public String toString() {
		return "ScreenNum: " + this.screenNum + ", Row: " + this.row + ", column:" + this.column + ",space: "+ space;
	}
}
