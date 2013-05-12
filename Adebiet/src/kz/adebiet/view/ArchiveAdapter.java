package kz.adebiet.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import kz.adebiet.R;

public class ArchiveAdapter extends ArrayAdapter<File> {
	private ArrayList<File> items;
	private Context c = null;

	/**
	 * Standard Data Adapter Construction
	 */
	public ArchiveAdapter(Context context, int textViewResourceId,
			ArrayList<File> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.c = context;
	}

	/**
	 * Code invoked when container notifies data set of change.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.browse_list_row, null);
		}

		TextView filename = null;
		ImageView fileicon = null;

		File f = items.get(position);
		if (f != null) {
			filename = (TextView) v.findViewById(R.id.filename);
			fileicon = (ImageView) v.findViewById(R.id.fileicon);
		}


				filename.setText(f.getName());


				fileicon.setImageResource(R.drawable.browser_file);
		

		Collections.sort(items);
		return v;
	}

}
