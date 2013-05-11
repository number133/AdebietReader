package kz.adebiet.setting;

import java.io.Serializable;

import kz.adebiet.setting.enums.FontFamily;
import kz.adebiet.setting.enums.FontStyle;

public class Settings implements Serializable{

	private static final long serialVersionUID = 5L;
	private int fontSize;
	private int bgColor;
	private int textColor;
	private FontFamily fontFamily;
	private boolean bold;
	private FontStyle fontStyle;
	
	public Settings(){
		fontSize = 3;
		bgColor = 0xFFFFFFFF;
		textColor = 0x00000000;
		fontFamily = FontFamily.AMERICAN_TYPEWRITER;
		bold = false;
		fontStyle = FontStyle.NORMAL;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public FontFamily getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(FontFamily fontFamily) {
		this.fontFamily = fontFamily;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public FontStyle getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(FontStyle fontStyle) {
		this.fontStyle = fontStyle;
	}		
	
}
