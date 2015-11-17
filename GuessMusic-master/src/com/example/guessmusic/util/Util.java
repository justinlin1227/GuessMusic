package com.example.guessmusic.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.guessmusic.R;
import com.example.guessmusic.data.Const;
import com.example.guessmusic.model.IAlertDialogButtonListener;
import com.example.guessmusic.ui.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Util {
	
	private static AlertDialog mAlertDialog;
	
	public static View getView(Context context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(layoutId, null);

		return layout;
	}

	/**
	 * ������ת
	 * 
	 * @param context
	 * @param desti
	 */
	public static void startActivity(Context context, Class desti) {
		Intent intent = new Intent(context, desti);
		context.startActivity(intent);

		// �رյ�ǰ��Activity
		((Activity) context).finish();

	}

	/**
	 * ��ʾ�Զ���Ի���
	 * 
	 * @param context
	 * @param message
	 * @param listaner
	 */
	public static void showDialog(final Context context, String message,
			final IAlertDialogButtonListener listaner) {

		View dialogView = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Theme_Transparent);
		dialogView = getView(context, R.layout.dialog_view);

		ImageButton btnOkView = (ImageButton) dialogView
				.findViewById(R.id.btn_dialog_ok);
		ImageButton btnCalcelView = (ImageButton) dialogView
				.findViewById(R.id.btn_dialog_cancel);
		TextView txtMessageView = (TextView) dialogView
				.findViewById(R.id.text_dialog_message);

		txtMessageView.setText(message);

		btnOkView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//�رնԻ���
				if(mAlertDialog != null){
					mAlertDialog.cancel();
				}
				
				//�¼��ص�
				if(listaner != null){
					listaner.onClick();
				}
				
				//������Ч
				MyPlayer.playTone(context, MyPlayer.INDEX_STONE_ENTER);
			}
		});

		btnCalcelView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//�رնԻ���
				if(mAlertDialog != null){
					mAlertDialog.cancel();
				}
				
				//������Ч
				MyPlayer.playTone(context, MyPlayer.INDEX_STONE_CANCEL);
			}
		});
		
		//Ϊdialog����view
		builder.setView(dialogView);
		mAlertDialog = builder.create();
		
		//��ʾ�Ի���
		mAlertDialog.show();
	}
	
	/**
	 * ��Ϸ���ݱ��棬�ļ��ķ�ʽ����
	 * @param context
	 * @param stageIndex
	 * @param coins
	 */
	public static void saveData(Context context,int stageIndex,int coins){
		FileOutputStream fis = null;
		
		try {
			fis = context.openFileOutput(Const.FILE_NAME_SAVE_DATA, Context.MODE_PRIVATE);
			
			DataOutputStream dos = new DataOutputStream(fis);
			dos.write(stageIndex);
			dos.write(coins);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ��������
	 * @param context
	 * @return
	 */
	public static int[] loadData(Context context){
		FileInputStream fis = null;
		int[] datas = {-1,Const.TOTAL_COINS};
		try {
			fis = context.openFileInput(Const.FILE_NAME_SAVE_DATA);
			
			DataInputStream dis = new DataInputStream(fis);
			//��ȡ���ǹؿ��ͽ������
			datas[0] = dis.readInt();
			datas[1] = dis.readInt();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return datas;
	}
	
}















