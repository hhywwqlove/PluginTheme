package com.plugintheme.android.act;

import com.plugintheme.android.impl.IThemeUpdater;
import com.plugintheme.android.loader.PluginManger;

import android.app.Activity;
import android.os.Bundle;

public abstract class ThemeActivity extends Activity implements IThemeUpdater{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		PluginManger.getInstance().registerUpdateReceiver(this, this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		PluginManger.getInstance().unRegisterUpdateReceiver(this, this);
	}
	
}
