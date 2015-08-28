package com.study.them;

import com.plugintheme.android.act.ThemeActivity;
import com.plugintheme.android.bean.PluginResBean;
import com.plugintheme.android.loader.PluginConfiger;
import com.plugintheme.android.loader.PluginResourceLoader;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ThemeActivity  implements OnClickListener{

	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		if(!PluginConfiger.getInstance().isDefaultSkin(this)){
			updataTheme();
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		updataTheme();
		
	}
	
	
	private void updataTheme(){
		
		PluginResourceLoader loader = new PluginResourceLoader(this);
		loader.startLoad("/sdcard/theme.apk");
		
	}

	@Override
	public void onUpdate(PluginResBean resBean) {
		// TODO Auto-generated method stub
      btn.setBackgroundColor(resBean.getColor("buttonColor"));
	}

}
