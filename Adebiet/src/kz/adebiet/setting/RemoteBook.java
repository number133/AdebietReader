package kz.adebiet.setting;

import java.io.Serializable;

public class RemoteBook implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private String size;
	private String path;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
