package com.plugintheme.android.loader;

import java.lang.reflect.Method;

import com.plugintheme.android.bean.PluginResBean;
import com.plugintheme.android.impl.IThemeParser;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;

public class PluginResourceLoader{

	private Context mContext;
	private IThemeParser mParser;
	private PluginResBean mResBean;
	
	public PluginResourceLoader(Context mContext) {
		super();
		this.mContext = mContext;
		this.mParser = new MySkinParser();
		mResBean = new PluginResBean();
	}


	public void startLoad(String resPath){
		
		SkinLoadAysn async = new SkinLoadAysn();
		async.execute(resPath);
		
	};
	
	
	private class SkinLoadAysn extends AsyncTask<String, Void, Resources>{

		
		@Override
		protected Resources doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			if(mParser!=null){
				mParser.onStartParser();
			}
			AssetManager manager = getAssetManager(params[0]);
			Resources res = getResources(manager);
			
			return res;
		}
		
		@Override
		protected void onPostExecute(Resources result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(mParser==null){
				return;
			}
			
			if(result!=null){
				mParser.onParseSuccess(result);
			}else{
				mParser.onParseFail();
			}
			
		}
		
	}
	
	
	private AssetManager getAssetManager(String resPath){
		AssetManager assetManager =null;
        try {
        	assetManager = AssetManager.class.newInstance();
			Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
			addAssetPath.invoke(assetManager, resPath);
			 PackageManager mPm=mContext.getPackageManager();
			PackageInfo mInfo=mPm.getPackageArchiveInfo(resPath,PackageManager.GET_ACTIVITIES);
			mResBean.setPlugPkg(mInfo.packageName);
		} catch (Exception e) {
		} 
        
        return assetManager;
	}
	
	private Resources getResources(AssetManager manager){
		
			if(manager==null){
				return null;
			}
		
		   Resources superRes = mContext.getResources();
           Resources skinResource=new Resources(manager, superRes.getDisplayMetrics(), superRes.getConfiguration());
           return skinResource;
	}
	
	private class MySkinParser implements IThemeParser{

		@Override
		public void onStartParser() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onParseSuccess(Resources res) {
			// TODO Auto-generated method stub
			
			mResBean.setRes(res);
			PluginConfiger.getInstance().setResource(mContext, mResBean);
			mContext.sendBroadcast(new Intent(PluginManger.SKIN_ACTION));
		}

		@Override
		public void onParseFail() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	


	
}
