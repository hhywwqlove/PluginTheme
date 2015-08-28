package com.plugintheme.android.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.plugintheme.android.impl.IThemeUpdater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class PluginManger {

	private PluginManger(){
	}
	
	private static class HolderClass {
		
		private static PluginManger instance = new PluginManger();
		
	}
	
	public static PluginManger getInstance(){
		
		return HolderClass.instance;
		
	}
	
	public static final String SKIN_ACTION = "com.skinupdate.action";
	
	private HashMap<IThemeUpdater, SkinUpdateReceiver> maps = new HashMap<IThemeUpdater, PluginManger.SkinUpdateReceiver>();
	private List<IThemeUpdater> updaters = new ArrayList<IThemeUpdater>();
	
	public void registerUpdateReceiver(Context context,IThemeUpdater impl){
		
		SkinUpdateReceiver receiver = new SkinUpdateReceiver();
		
		maps.put(impl, receiver);
		updaters.add(impl);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(SKIN_ACTION);
		context.registerReceiver(receiver, filter);
		
	}
	
	public void unRegisterUpdateReceiver(Context context,IThemeUpdater impl){
		
		SkinUpdateReceiver receiver = maps.get(impl);
		
		if(receiver!=null){
			context.unregisterReceiver(receiver);
			maps.remove(impl);
			updaters.remove(impl);
		}
		
	}
	
	public void finishAll(){
		
		maps.clear();
		updaters.clear();
		
	}
	
	
	

	
	
	public class SkinUpdateReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			for (IThemeUpdater item : updaters) {
				
				item.onUpdate(PluginConfiger.getInstance().getResource());
				
			}
			
			
		}
		
	}
	
}
