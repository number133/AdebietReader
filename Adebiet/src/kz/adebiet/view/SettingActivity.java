package kz.adebiet.view;

import kz.adebiet.setting.Settings;
import kz.adebiet.setting.StorageHelper;
import kz.adebiet.setting.enums.Font;
import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import app.seamolec.siebenreader.R;

public class SettingActivity extends Activity {
	private StorageHelper storageHelper = null;
	private SeekBar fontSizeSeekBar = null;
	private TextView fontSizeNameTextView = null;
	private Settings appSettings;
	private Button bgColorButton = null;
	private Button bgColorViewButton = null;
	private Button fontColorButton = null;
	private Button fontColorViewButton = null;
	private Spinner fontFamilySpinner = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		setTitle(R.string.title_activity_setting);
		storageHelper = new StorageHelper(this);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	private void init() {
		initSettings();
		initFontSize();
		initBgColor();
		initFontColor();
		initFontFamily();
	}
	
	private void initSettings(){
		appSettings = storageHelper.readSettings();
	}

	private void initFontSize() {
		fontSizeNameTextView = (TextView) findViewById(R.id.fontSizeName);
		fontSizeSeekBar = (SeekBar) findViewById(R.id.fontSizeSeekBar);

		fontSizeSeekBar.setMax(4);

		fontSizeSeekBar.setProgress(appSettings.getFontSize());

		updateFontSizeNameView(fontSizeNameTextView, appSettings.getFontSize());

		try {
			fontSizeSeekBar
					.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
						public void onStopTrackingTouch(SeekBar arg0) {
						}

						public void onStartTrackingTouch(SeekBar arg0) {
						}

						public void onProgressChanged(SeekBar arg0,
								int progress, boolean arg2) {
							updateFontSizeNameView(fontSizeNameTextView,
									progress);
							appSettings.setFontSize(progress);
							storageHelper.writeSettings(appSettings);
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateFontSizeNameView(TextView textView, int size) {
		switch (size) {
		case 0:
			textView.setText("Өте кіші");
			break;
		case 1:
			textView.setText("Кішілеу");
			break;
		case 2:
			textView.setText("Орташа");
			break;
		case 3:
			textView.setText("Үлкендеу");
			break;
		case 4:
			textView.setText("Үлкен");
			break;
		}
	}

	private void initBgColor() {
		bgColorViewButton = (Button) findViewById(R.id.bgColorViewButton);
		bgColorViewButton.setBackgroundColor(appSettings.getBgColor());
		bgColorButton = (Button) findViewById(R.id.bgColorButton);
		bgColorButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pickBgColor();				
			}
		});
	}
	
	private void pickBgColor(){
		 AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, appSettings.getBgColor(), new OnAmbilWarnaListener() {
			 
	            public void onCancel(AmbilWarnaDialog dialog){
	            }
	 
	            public void onOk(AmbilWarnaDialog dialog, int color) {
	            	appSettings.setBgColor(color);
	            	bgColorViewButton.setBackgroundColor(color);
	            	storageHelper.writeSettings(appSettings);
	            }
	        });
	        dialog.show();
	}
	
	private void initFontColor() {
		fontColorViewButton = (Button) findViewById(R.id.fontColorViewButton);
		fontColorViewButton.setBackgroundColor(appSettings.getTextColor());
		fontColorButton = (Button) findViewById(R.id.fontColorButton);
		fontColorButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pickFontColor();				
			}
		});
	}
	
	private void pickFontColor(){
		 AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, appSettings.getTextColor(), new OnAmbilWarnaListener() {
			 
	            public void onCancel(AmbilWarnaDialog dialog){
	            }
	 
	            public void onOk(AmbilWarnaDialog dialog, int color) {
	            	appSettings.setTextColor(color);
	            	fontColorViewButton.setBackgroundColor(color);
	            	storageHelper.writeSettings(appSettings);
	            }
	        });
	        dialog.show();
	}

	private void initFontFamily(){
		fontFamilySpinner = (Spinner) findViewById(R.id.fontFamilySpinner);
		String[] data = Font.getNames();
		Log.i("font names: ", Font.getNames().toString());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontFamilySpinner.setAdapter(adapter);
        fontFamilySpinner.setPrompt("Title");
        fontFamilySpinner.setSelection(appSettings.getFontFamily().getFontId());
        fontFamilySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                int position, long id) {
            	appSettings.setFontFamily(Font.values()[position]);
            	storageHelper.writeSettings(appSettings);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
          });
	}
}
