package kz.adebiet.setting;

import java.io.Serializable;

import kz.adebiet.setting.enums.Font;

public class Settings implements Serializable{

	private static final long serialVersionUID = 3L;
	private int fontSize;
	private int bgColor;
	private int textColor;
	private Font fontFamily;
	
	public Settings(){
		fontSize = 3;
		bgColor = 0xFFFFFFFF;
		textColor = 0x00000000;
		fontFamily = Font.AMERICAN_TYPEWRITER;
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

	public Font getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(Font fontFamily) {
		this.fontFamily = fontFamily;
	}
		
}
