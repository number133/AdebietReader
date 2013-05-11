package kz.adebiet.setting;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BookInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> books;
	
	public BookInfo(){
		books = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getBooks() {
		return books;
	}

	public void setBooks(Map<String, Integer> books) {
		this.books = books;
	}
	
	
}
