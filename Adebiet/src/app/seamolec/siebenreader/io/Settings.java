package app.seamolec.siebenreader.io;

import java.io.Serializable;

public class Settings implements Serializable{

	private static final long serialVersionUID = 1L;
	private int fontSize;
	private int bgColor;
	
	public Settings(){
		fontSize = 3;
		bgColor = 0xFFFFFFFF;
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
	
	
}
