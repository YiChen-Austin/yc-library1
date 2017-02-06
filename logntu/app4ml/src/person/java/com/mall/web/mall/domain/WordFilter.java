package com.mall.web.mall.domain;

public class WordFilter implements java.io.Serializable {
	private int wordId;
	private String name;
	private int level;
	
	public WordFilter() {
	}
	
	public WordFilter(int wordId,String name,int level) {
		this.wordId=wordId;
		this.name=name;
		this.level=level;
	}
	
	
	
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
