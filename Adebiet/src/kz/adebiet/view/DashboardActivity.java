package kz.adebiet.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import kz.adebiet.R;
import kz.adebiet.util.Utils;

public class DashboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dashboard_layout);

		// dashboard open book button
		Button btnOpenBook = (Button) findViewById(R.id.btn_open_book);

		// dashboard setting button
		Button btnSetting = (Button) findViewById(R.id.btn_setting);

		// dashboard help button
		Button btnHelp = (Button) findViewById(R.id.btn_help);

		// dashboard about button
		Button btnAbout = (Button) findViewById(R.id.btn_about);

		// dashboard history button
		Button btnHistory = (Button) findViewById(R.id.btn_history);

		// listener when button open book clicked
		btnOpenBook.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						BrowseEpubActivity.class);
				startActivity(i);

			}
		});

		// listener when button setting clicked
		btnSetting.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						SettingActivity.class);
				startActivity(i);

			}
		});

		// listener when button help clicked
		btnHelp.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						HelpActivity.class);
				startActivity(i);
			}
		});

		// listener when button about clicked
		btnAbout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						AboutActivity.class);
				startActivity(i);
			}
		});

		// listener when button history clicked
		btnHistory.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						ArchiveActivity.class);
				startActivity(i);

			}
		});
		
	}

}