package kz.adebiet.setting.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum FontStyle {
	NORMAL(0), ITALIC(1), OBLIQUE(2);

	private int fontStyleId;
	private static final Map<Integer, String> fontStyleCSS = new LinkedHashMap<Integer, String>();
	private static final Map<Integer, String> fontStyleNames = new LinkedHashMap<Integer, String>();
	static {
		fontStyleCSS.put(0, "normal");
		fontStyleCSS.put(1, "italic");
		fontStyleCSS.put(2, "oblique");
		fontStyleNames.put(0, "Қарапайым");
		fontStyleNames.put(1, "Курсивті");
		fontStyleNames.put(2, "Көлбеу");
	}

	FontStyle(int fontStyleId) {
		this.fontStyleId = fontStyleId;
	}

	public int getFontStyleId() {
		return fontStyleId;
	}

	public String getCssFontStyle() {
		return fontStyleCSS.get(fontStyleId);
	}
	
	public static String[] getNames() {

		int size = fontStyleNames.values().size();
		String[] arr = new String[size];
		for (int i = 0; i < size; i++) {
			arr[i] = fontStyleNames.get(i);
		}
		return arr;
	}
}
