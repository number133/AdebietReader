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
import kz.adebiet.setting.FTPDownloader;
import kz.adebiet.setting.FTPUploader;
import kz.adebiet.setting.RemoteBook;
import kz.adebiet.setting.RemoteLibrary;
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
				new AlertDialog.Builder(DashboardActivity.this)
						.setTitle("Көмек")
						.setMessage(
								"- Кітап ашу : дисктен.epub extention бар файлды ашыңыз\n"
										+ "- Көмек : бұл қосымшаны қалай пайдалану қажет\n"
										+ "> келесі бетке өту үшін оңнан солға жүргізіңіз\n"
										+ "> алдыңғы бетке өту үшін солдан оңға жүргізіңіз\n"
										+ "- Біз туралы : осы қосымшаны жасаушы")
						.setPositiveButton("Жабу", null).show();
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
		
//		try{
//			RemoteLibrary lib = new RemoteLibrary();
//			RemoteBook book = new RemoteBook();
//			book.setTitle("Abay");
//			book.setDescription("Some desc");
//			book.setSize("2");
//			book.setPath("book");
//			lib.getBooks().add(book);
//			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//			ObjectOutputStream oos = new ObjectOutputStream(buffer);
//			oos.writeObject(lib);
//			oos.close();
//			byte[] rawData = buffer.toByteArray();
//			InputStream myInputStream = new ByteArrayInputStream(rawData); 
//			FTPUploader ftp = new FTPUploader();
//			ftp.execute(myInputStream, "myfile.txt");
//			
//		}catch(Exception ex){
//			Log.i("FTP", "Error");
//		}
	}

}