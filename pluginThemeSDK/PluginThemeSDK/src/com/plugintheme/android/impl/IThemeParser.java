package com.plugintheme.android.impl;

import android.content.res.Resources;

public interface IThemeParser {

	public void onStartParser();
	public void onParseSuccess(Resources res);
	public void onParseFail();
	
}
