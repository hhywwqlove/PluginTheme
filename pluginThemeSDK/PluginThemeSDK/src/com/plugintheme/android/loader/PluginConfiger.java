package com.plugintheme.android.loader;

import com.plugintheme.android.bean.PluginResBean;

import android.content.Context;
import android.content.SharedPreferences;


public class PluginConfiger {
	
	private PluginConfiger(){
	}
	
	private static class HolderClass {
		
		private static PluginConfiger instance = new PluginConfiger();
		
	}
	
	public static PluginConfiger getInstance(){
		
		return HolderClass.instance;
		
	}

	private PluginResBean resBean;
	
	private SharedPreferences sp;
	
	public void setResource(Context mCtx,PluginResBean bean){
		
		this.resBean = bean;
		this.sp = getSharedPreferences(mCtx);
		this.sp.edit().putString("skinName", resBean.getPlugPkg()).commit();
	}
	
	private SharedPreferences getSharedPreferences(Context mCtx){
		
		if(sp==null){
			sp = mCtx.getSharedPreferences("plugskin", Context.MODE_PRIVATE);
		}
		return sp;
		
	}
	
	public PluginResBean getResource(){
		
		return resBean;
	}
	
	public boolean isDefaultSkin(Context mCtx){
		
		sp = getSharedPreferences(mCtx);
		String skinName = sp.getString("skinName", "");
		
		return skinName.equals("")?true:false;
	}
	
}
