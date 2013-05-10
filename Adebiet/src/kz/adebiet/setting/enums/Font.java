package kz.adebiet.setting.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Font {
	AMERICAN_TYPEWRITER(0), ARIAL_ROUNDED(1), BASKERVILLE(2);

	private int fontId;
	private static final Map<Integer, String> fontCSS = new LinkedHashMap<Integer, String>();
	private static final Map<Integer, String> fontNames = new LinkedHashMap<Integer, String>();
	static {
		fontCSS.put(0,
				"‘American Typewriter’, ‘Courier New’, Courier, Monaco, mono");
		fontCSS.put(1, "‘Arial Rounded MT Bold’, Helvetica, Arial, sans-serif");
		fontCSS.put(2,
				"Baskerville, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontNames.put(0, "American Typewriter");
		fontNames.put(1, "Arial Rounded MT Bold");
		fontNames.put(2, "Baskerville");
	}

	Font(int fontId) {
		this.fontId = fontId;
	}

	public int getFontId() {
		return fontId;
	}

	public String getCssFont() {
		return fontCSS.get(fontId);
	}

	public String getName(int id) {
		return fontNames.get(id);
	}

	public static String[] getNames() {

		int size = fontNames.values().size();
		String[] arr = new String[size];
		for (int i = 0; i < size; i++) {
			arr[i] = fontNames.get(i);
		}
		return arr;
	}

}
