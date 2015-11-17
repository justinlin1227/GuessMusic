package com.example.dsy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	private EditText etRows;
	private EditText etColumns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etRows = (EditText) findViewById(R.id.et_rows);
		etColumns = (EditText) findViewById(R.id.et_columns);
	}

	public void doClick(View v) {
		try {
			int rows=Integer.valueOf(etRows.getText().toString().trim());
			int columns=Integer.valueOf(etColumns.getText().toString().trim());
			createImage(rows, columns,10);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void createImage(int rows, int columns,int margin){
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		//params用户控制Imageview的大小
		int width=initWandH(columns,margin);
		System.out.println(">>>>width"+width);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(     
				width,width); 
		for(int i=0;i<rows;i++){
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			for(int j=0;j<columns;j++){
				//根据不同位置，控制不同margin
				params.setMargins(10, 10, 10, 10);
				if(j==0){
					params.setMargins(10, 10, 0, 0);
				}else if(j==columns-1){
					params.setMargins(0, 10, 10, 0);
				}
				ImageView iv=new ImageView(MainActivity.this);
				iv.setImageResource(R.drawable.ic_launcher);
				iv.setScaleType(ScaleType.FIT_XY);
				linearLayout.addView(iv,params);
			}
			layout.addView(linearLayout);
		}
	}

	//计算控件的宽度
	public int initWandH(int columnNum,int margin){
		DisplayMetrics dm = new DisplayMetrics();
		//获取屏幕信息
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int screenHeigh = dm.heightPixels;
		int ivWidth=screenWidth-(columnNum+1)*margin;
		return ivWidth/columnNum;
	}

}
