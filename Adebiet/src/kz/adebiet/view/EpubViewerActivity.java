package kz.adebiet.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import kz.adebiet.parser.EpubScanner;
import kz.adebiet.setting.BookInfo;
import kz.adebiet.setting.Settings;
import kz.adebiet.setting.StorageHelper;
import kz.adebiet.util.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.epub.EpubReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import kz.adebiet.R;

public class EpubViewerActivity extends Activity {

	private Utils utils;
	private Book book;
	private Spine spine;
	private EpubScanner epubScan;
	private HTML5WebView mWebView;
	private String epubVersion;
	private int currentPage, maxPage;
	private StorageHelper storageHelper = null;
	private BookInfo bookInfo = null;
	MenuDialog customMenuDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		utils = new Utils();
		epubScan = new EpubScanner();
		currentPage = 0;

		try {
			book = (new EpubReader())
					.readEpub(new FileInputStream(utils.DIR_OUTPUT
							+ File.separator + utils.OUTPUT_EPUB_FILE));
			setTitle(book.getTitle().toString());

			spine = book.getSpine();
			List<SpineReference> spineList = spine.getSpineReferences();
			maxPage = spineList.size();

			epubScan.getTOC(book.getTableOfContents().getTocReferences(), 0);
			mWebView = new HTML5WebView(this);
			mWebView.setGestureDetector(new GestureDetector(
					new CustomeGestureDetector()));

			epubVersion = epubScan.getEpubVersion();

			storageHelper = new StorageHelper(this);
			mWebView.update(storageHelper.readSettings());
			bookInfo = storageHelper.readBookInfo();
			if (!bookInfo.getBooks().containsKey(book.getTitle().toString())) {
				bookInfo.getBooks().put(book.getTitle().toString(), 0);
				storageHelper.writeBookInfo(bookInfo);
			}

			if (savedInstanceState != null) {
				mWebView.restoreState(savedInstanceState);
			} else {
				// changeDoc(epubVersion
				// + spine.getResource(currrentPage).getHref());
				mWebView.loadUrl("file:///" + epubVersion
						+ spine.getResource(currentPage).getHref());
			}

			setContentView(mWebView.getLayout());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("Error", e.getMessage());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.menu.menu_ebook, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// if (item.getItemId() == R.id.menu_toc) {
		// startActivity(new Intent(this, TOCActivity.class));
		// } else if (item.getItemId() == R.id.menu_dashboard) {
		// startActivity(new Intent(this, DashboardActivity.class));
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mWebView.saveState(outState);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (bookInfo.getBooks().containsKey(book.getTitle().toString())) {
			bookInfo.getBooks().put(book.getTitle().toString(), currentPage);
			storageHelper.writeBookInfo(bookInfo);
		}
		mWebView.stopLoading();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mWebView.inCustomView()) {
				mWebView.hideCustomView();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	public class CustomeGestureDetector extends SimpleOnGestureListener {
		int count = 0;

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1 == null || e2 == null)
				return false;
			if (e1.getPointerCount() > 1 || e2.getPointerCount() > 1)
				return false;
			else {
				try {
					// go to next page
					if (e1.getX() - e2.getX() > 100
							&& Math.abs(velocityX) > 800) {

						Log.i("swipe", "right to left");
						Animation anim = AnimationUtils.loadAnimation(
								getApplicationContext(), R.anim.right_to_left);
						mWebView.startAnimation(anim);

						if (count < maxPage) {
							count++;
							currentPage++;
							changeDoc(epubVersion
									+ spine.getResource(currentPage).getHref());
							mWebView.loadUrl("file:///" + epubVersion
									+ spine.getResource(currentPage).getHref());
							setContentView(mWebView.getLayout());

							Log.i("buka halaman kanan : " + (currentPage),
									spine.getResource(currentPage).getHref());
						}
						return true;
					}
					// go to prev page
					else if (e2.getX() - e1.getX() > 100
							&& Math.abs(velocityX) > 800) {

						Log.i("swipe", "left to right");
						Animation anim = AnimationUtils.loadAnimation(
								getApplicationContext(), R.anim.left_to_right);
						mWebView.startAnimation(anim);

						if (count > 0) {
							count--;
							currentPage--;
							changeDoc(epubVersion
									+ spine.getResource(currentPage).getHref());
							mWebView.loadUrl("file:///" + epubVersion
									+ File.separator
									+ spine.getResource(currentPage).getHref());
							setContentView(mWebView.getLayout());
							Log.i("buka halaman kiri : " + (currentPage), spine
									.getResource(currentPage).getHref());
						}
						return true;
					}
				} catch (Exception e) {

				}
				return false;
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (bookInfo.getBooks().containsKey(book.getTitle().toString())) {
			currentPage = bookInfo.getBooks().get(book.getTitle().toString());
		}
		if (storageHelper != null) {
			mWebView.update(storageHelper.readSettings());
		}
		if (currentPage != 0) {
			changeDoc(epubVersion + spine.getResource(currentPage).getHref());
			mWebView.loadUrl("file:///" + epubVersion
					+ spine.getResource(currentPage).getHref());
		}
	}

	public void refresh() {
		changeDoc(epubVersion + spine.getResource(currentPage).getHref());
		mWebView.loadUrl("file:///" + epubVersion
				+ spine.getResource(currentPage).getHref());
	}

	private void changeDoc(String in) {
		Document doc;
		Settings s = storageHelper.readSettings();
		StringBuilder style = new StringBuilder();
		// if(s.getTextColor() > 0){
		String textColor = String
				.format("#%06X", (0xFFFFFF & s.getTextColor()));
		style.append("color: ").append(textColor).append(";");
		String bgColor = String.format("#%06X", (0xFFFFFF & s.getBgColor()));
		style.append("background-color: ").append(bgColor).append(";");
		style.append("font-family: ").append(s.getFontFamily().getCssFont())
				.append(";");
		style.append("font-style: ").append(s.getFontStyle().getCssFontStyle())
				.append(";");
		if (s.isBold()) {
			style.append("font-weight: ").append("bold;");
		}
		// }
		try {
			File input = new File(in);
			doc = Jsoup.parse(input, "UTF-8");
			Log.i("changeDoc", "parsing doc: " + in);
			Elements styles = doc.select("style");
			if (styles.size() == 0) {
				for (Element e : styles) {
					e.remove();
				}
				Log.i("changeDoc", "remove atrr style");
			}
			Elements body = doc.select("body");
			body.attr("style", style.toString());
			Log.i("changeDoc", "set bg color");

			FileWriter fileWriter;
			fileWriter = new FileWriter(in);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(doc.toString());
			Log.i("changeDoc", doc.toString());
			bufferedWriter.close();
			Log.i("changeDoc", "wrote to file: " + in);
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (customMenuDialog == null) {
				customMenuDialog = new MenuDialog(this);
				customMenuDialog.setCanceledOnTouchOutside(false);
				customMenuDialog
						.setOnDismissListener(new DialogInterface.OnDismissListener() {
							@Override
							public void onDismiss(DialogInterface dialog) {
								refresh();
							}
						});
			}
			customMenuDialog.update();
			customMenuDialog.show();
			return true;
		}
		return super.onKeyUp(keyCode, event);

	}

	private class MenuDialog extends AlertDialog {
		private Context cxt;
		private ProgressBar progressBar;
		private TextView progressText;
		private Button firtPageButton;
		private Button lastPageButton;
		private EditText pageNumberEditText;
		private TextView pageCountTextView;
		private Button homeButton;
		private Button settingButton;
		private DigitalClock dClock;		

		public MenuDialog(Context context) {
			super(context);
			cxt = context;
			View cus_menu = getLayoutInflater().inflate(
					R.layout.activity_custom_options, null);
			setView(cus_menu);
			progressBar = (ProgressBar) cus_menu.findViewById(R.id.progressBar);
			progressText = (TextView) cus_menu
					.findViewById(R.id.progressTextView);
			firtPageButton = (Button) cus_menu
					.findViewById(R.id.firstPageButton);
			lastPageButton = (Button) cus_menu
					.findViewById(R.id.LastPageButton);
			homeButton = (Button) cus_menu.findViewById(R.id.homeButton);
			settingButton = (Button) cus_menu.findViewById(R.id.settingButton);
			homeButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
							DashboardActivity.class);
					startActivity(i);
					dismiss();
				}
			});
			settingButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
							SettingActivity.class);
					startActivity(i);
					dismiss();
				}
			});

			pageNumberEditText = (EditText) cus_menu
					.findViewById(R.id.pageNumberEditText);
			pageNumberEditText.setText(Integer.toString(currentPage));
			pageCountTextView = (TextView) cus_menu
					.findViewById(R.id.pageCountTextView);
			pageCountTextView.setText("/" + (maxPage - 1));
			firtPageButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					currentPage = 1;
					dismiss();
				}
			});
			lastPageButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					currentPage = maxPage - 1;
					dismiss();
				}
			});
			update();

		}

		private void update() {
			float c = currentPage;
			float m = maxPage;
			String percent = String.format("%.2f", ((c) / (m - 1) * 100.00f))
					+ "%";
			if (progressBar != null) {
				progressBar.setMax(maxPage);
				progressBar.setProgress(currentPage);
			}
			if (progressText != null) {
				progressText.setText(percent);
			}
			pageNumberEditText.setText(Integer.toString(currentPage));
		}

		@Override
		public boolean onKeyUp(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_MENU) {
				int page = Integer.parseInt(pageNumberEditText.getText()
						.toString());
				if (page > 0 && page < maxPage) {
					currentPage = page;
				}
				dismiss();
				return true;
			}
			return super.onKeyUp(keyCode, event);
		}
	}
}