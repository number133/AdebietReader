package kz.adebiet.setting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RemoteLibrary implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<RemoteBook> books;	

	public RemoteLibrary() {
		super();
		this.books = new ArrayList<RemoteBook>();
	}

	public List<RemoteBook> getBooks() {
		return books;
	}

	public void setBooks(List<RemoteBook> books) {
		this.books = books;
	}
	
	
	

}
