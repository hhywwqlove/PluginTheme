package com.plugintheme.android.bean;

import android.content.res.Resources;

public class PluginResBean {

	private Resources res;
	private String plugPkg;
	
	public Resources getRes() {
		return res;
	}
	public void setRes(Resources res) {
		this.res = res;
	}
	public String getPlugPkg() {
		return plugPkg;
	}
	public void setPlugPkg(String plugPkg) {
		this.plugPkg = plugPkg;
	}
	
	public int getColor(String colorName){
		
		 int id=res.getIdentifier(colorName, "color", plugPkg);
		 
		 return res.getColor(id);
		
	}
	
	public int getDrawable(String drawableName){
		
		int id=res.getIdentifier(drawableName, "drawable", plugPkg);
		 
		 return res.getColor(id);
	}
	
	
	
}
