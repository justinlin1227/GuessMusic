package com.example.guessmusic.model;

import android.widget.Button;

/**
 * ʵ���࣬����ÿ��Button��Word
 * @author Administrator
 *
 */
public class WordButton {
	//�ڼ���Word
	private int mIndex;
	//�Ƿ�ɼ�
	private boolean mIsVisiable;
	//Ҫ��ʾ����
	private String mWordString;
	private Button mViewButton;
	
	public WordButton() {
		mIsVisiable = true;
		mWordString = "";
	}

	public int getIndex() {
		return mIndex;
	}

	public void setIndex(int index) {
		this.mIndex = index;
	}

	public boolean isIsVisiable() {
		return mIsVisiable;
	}

	public void setIsVisiable(boolean isVisiable) {
		this.mIsVisiable = isVisiable;
	}

	public String getWordString() {
		return mWordString;
	}

	public void setWordString(String wordString) {
		this.mWordString = wordString;
	}

	public Button getViewButton() {
		return mViewButton;
	}

	public void setViewButton(Button viewButton) {
		this.mViewButton = viewButton;
	}
}
