package kz.adebiet.setting;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class History implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<File> files;
	
	public History(){
		files = new ArrayList<File>();
	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}
	
	
	
}
