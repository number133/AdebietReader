package kz.adebiet.setting.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum FontFamily {
	AMERICAN_TYPEWRITER(0), ARIAL_ROUNDED(1), BASKERVILLE(2), BOOK_ANTIQUA(3), BOOKMAN_OLD_STYLE(
			4), BRUSH_SCRIPT_MT(5), CHALKBOARD(6), DIDOT(7), FUTURA(8), GILL_SANS(
			9), HELVETICA_NEUE(10), HOEFLER_TEXT(11), LUCIDA_GRANDE(12), MARKER_FELT(
			13), MYRIAD(14), OPTIMA(15), PALATINO(16), COCHIN(17), GOUDY_OLD_STYLE(
			18);

	private int fontId;
	private static final Map<Integer, String> fontCSS = new LinkedHashMap<Integer, String>();
	private static final Map<Integer, String> fontNames = new LinkedHashMap<Integer, String>();
	static {
		fontCSS.put(0,
				"‘American Typewriter’, ‘Courier New’, Courier, Monaco, mono");
		fontCSS.put(1, "‘Arial Rounded MT Bold’, Helvetica, Arial, sans-serif");
		fontCSS.put(2,
				"Baskerville, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontCSS.put(3, "‘Book Antiqua’, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontCSS.put(4, "‘Bookman Old Style’, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontCSS.put(5, "‘Brush Script MT’, ‘Comic Sans’, sans-serif");
		fontCSS.put(6, "Chalkboard, ‘Comic Sans’, sans-serif");
		fontCSS.put(7, "Didot, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontCSS.put(8, "Futura, Impact, Helvetica, Arial, sans-serif");
		fontCSS.put(9, "‘Gill Sans’, ‘Lucida Grande’, ‘Lucida Sans Unicode’, Verdana, Helvetica, Arial, sans-serif");
		fontCSS.put(10, "‘Helvetica Neue’, Helvetica, Arial, sans-serif");
		fontCSS.put(11, "‘Hoefler Text’, Garamond, Georgia, ‘Times New Roman’, Times, serif");
		fontCSS.put(12, "‘Lucida Grande’, ‘Lucida Sans Unicode’, Lucida, Verdana, Helvetica, Arial, sans-serif");
		fontCSS.put(13, "‘Marker Felt’, ‘Comic Sans’ sans-serif");
		fontCSS.put(14, "Myriad, Helvetica, Arial, sans-serif");
		fontCSS.put(15, "Optima, ‘Lucida Grande’, ‘Lucida Sans Unicode’, Verdana, Helvetica, Arial, sans-serif");
		fontCSS.put(16, "Palatino, ‘Book Antiqua’, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontCSS.put(17, "Cochin, Georgia, Garamond, ‘Times New Roman’, Times, serif");
		fontCSS.put(18, "‘Goudy Old Style’, garamond, ‘book antiqua’, ‘Times New Roman’, Times, serif sequence");
		fontNames.put(0, "American Typewriter");
		fontNames.put(1, "Arial Rounded MT Bold");
		fontNames.put(2, "Baskerville");
		fontNames.put(3, "Book Antiqua");
		fontNames.put(4, "Bookman Old Style");
		fontNames.put(5, "Brush Script MT");
		fontNames.put(6, "Chalkboard");
		fontNames.put(7, "Didot");
		fontNames.put(8, "Futura");
		fontNames.put(9, "Gill Sans");
		fontNames.put(10, "Helvetica Neue");
		fontNames.put(11, "Hoefler Text");
		fontNames.put(12, "Lucida Grande");
		fontNames.put(13, "Marker Felt");
		fontNames.put(14, "Myriad");
		fontNames.put(15, "Optima");
		fontNames.put(16, "Palatino");
		fontNames.put(17, "Cochin");
		fontNames.put(18, "Goudy Old Style");
	}

	FontFamily(int fontId) {
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
