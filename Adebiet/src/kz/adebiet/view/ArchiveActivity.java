package kz.adebiet.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import kz.adebiet.io.EpubExtractor;
import kz.adebiet.setting.History;
import kz.adebiet.setting.StorageHelper;
import kz.adebiet.util.Utils;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import kz.adebiet.R;

public class ArchiveActivity extends ListActivity {
	private ArchiveAdapter mAdapter = null;
	private ArrayList<File> mFiles = new ArrayList<File>();
	private ProgressDialog progressDialog;
	private Utils utils = null;
	private AlertDialog.Builder adb = null;
	private StorageHelper storageHelper;
	private History history; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adb = new AlertDialog.Builder(this);
		storageHelper = new StorageHelper(this);
		history = storageHelper.readHistory();
		mFiles = history.getFiles();
		setContentView(R.layout.browse_main);
		mAdapter = new ArchiveAdapter(this, R.layout.browse_list_row, mFiles);
		setListAdapter(mAdapter);
		utils = new Utils();
		File dir = new File(utils.DIR_OUTPUT);
		utils.deleteDirectory(dir, ArchiveActivity.this);
		utils.createDirectory(utils.DIR_OUTPUT, ArchiveActivity.this);

	}

	@SuppressLint("NewApi")
	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {
		final File f = (File) parent.getItemAtPosition(position);
		// Log.i("info", f.getParent()); // /mnt/sdcard/siebenreader
		// Log.i("info", f.getName()); // epub file name from sd card
		// Log.i("info", utils.DIR_OUTPUT + File.separator); //
		// /mnt/sdcard/siebenreader/
		File test = new File(f.getParent() + File.separator + f.getName());
		if (!test.exists()) {
			mFiles.remove(f);
			history.setFiles(mFiles);
			storageHelper.writeHistory(history);
			AlertDialog ad = adb.create();
			ad.setMessage("Кітап табылмады");
			ad.show();
			recreate();			
		} else {
			progressDialog = ProgressDialog.show(ArchiveActivity.this,
					"Жүктелуде", "Күте тұрыңыз");
			new Thread() {
				public void run() {
					try {

						utils.copyFile(f.getParent() + File.separator,
								f.getName(), utils.DIR_OUTPUT + File.separator); // rename
																					// epub
																					// file
																					// to
																					// sieben.epub
																					// and
																					// copy
																					// to
																					// /mnt/sdcard/siebenreader/

						EpubExtractor epubExtract = new EpubExtractor();
						epubExtract.unzip(utils.DIR_OUTPUT + File.separator
								+ utils.OUTPUT_EPUB_FILE, utils.DIR_OUTPUT
								+ File.separator); // extract file
													// /mnt/sdcard/siebenreader/sieben.epub
						// Log.i("info", "file : " + utils.DIR_OUTPUT +
						// File.separator + utils.OUTPUT_EPUB_FILE);
						// Log.i("info", "dest : " + utils.DIR_OUTPUT +
						// File.separator);

						Intent i = new Intent(getApplicationContext(),
								EpubViewerActivity.class);
						startActivity(i);

					} catch (Exception e) {

					}
					progressDialog.dismiss();
				}
			}.start();
		}
	}
}
