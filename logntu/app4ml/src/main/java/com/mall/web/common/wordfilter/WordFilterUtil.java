package com.mall.web.common.wordfilter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.web.common.dao.SysWordFilterDao;
import com.mall.web.mall.domain.WordFilter;

public class WordFilterUtil {
    
	private static Logger logger = Logger.getLogger(WordFilterUtil.class);
	@Resource(name = "sysWordFilterDao")
	private SysWordFilterDao sysWordFilterDao;
	private static Node tree = new Node();
	private static WordFilterUtil instance;
	

	/**
	 * 利用单利模式获取一个SystemResource对象
	 * 
	 * @return SystemResource -SystemResource对象
	 * @throws FrameworkException
	 */
	public static synchronized WordFilterUtil getInstance() throws FrameworkException {
		if (BaseUtil.isEmpty(instance))
			instance = (WordFilterUtil) ContextLoader.getCurrentWebApplicationContext().getBean("wordFilter");
		return instance;
	}
    
	/**
	 * 加载脏字库
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public void loadWordDictionary() throws FrameworkException {
		List<WordFilter> words=sysWordFilterDao.findWords();
		try {
			for(WordFilter word:words){
				Node node = tree;
				for (int i = 0; i < word.getName().length(); i++) {
					node = node.addChar(word.getName().charAt(i));
				}
				node.setIsEnd(true);
				node.setLevel(word.getLevel());
			}
		} catch (Exception e) {
			logger.warn(e);
		}
	}

	/**
	 * 纯文本过滤，不处理html标签，直接将去除所有特殊符号后的字符串拼接后进行过滤，可能会去除html标签内的文字，比如：如果有关键字“fuckfont”，过滤fuck<font>a</font>后的结果为****<****>a</font>
	 * @param originalString 原始需过滤的串
	 * @param replacement 替换的符号
	 * @return
	 */
	public  String filterText(String originalString, char replacement){
		return filter(filterPunctation(originalString), replacement);
	}
	/**
	 * html过滤，处理html标签，不处理html标签内的文字，略有不足，会跳过<>标签内的所有内容，比如：如果有关键字“fuck”，过滤<a title="fuck">fuck</a>后的结果为<a title="fuck">****</a>
	 * @param originalString 原始需过滤的串
	 * @param replacement 替换的符号
	 * @return
	 */
	public  String filterHtml(String originalString, char replacement){
		return filter(filterPunctationAndHtml(originalString), replacement);
	}
	
	private  String filter(PunctuationOrHtmlFilteredResult pohResult, char replacement){
		StringBuffer sentence =pohResult.getFilteredString();
		ArrayList<Integer> charOffsets = pohResult.getCharOffsets();
		StringBuffer resultString = new StringBuffer(pohResult.getOriginalString());
	
		Node node = tree;
		int start=0,end=0;
		for(int i=0;i<sentence.length();i++){
			start=i;
			end=i;
			node = tree;
			for(int j=i;j<sentence.length();j++){
				node = node.findChar(sentence.charAt(j));
				if(node == null){
					break;
				}
				if(node.getIsEnd()){
					end=j;
				}
			}
			if(end>start){
				for(int k=start;k<=end;k++){
					resultString.setCharAt(charOffsets.get(k), replacement);
				}
				i=end;
			}
		}
		return resultString.toString();
	}
	
	
	/**
	 * 标点字符判断
	 */
	private  boolean isPunctuationChar(String c) {
		String regex = "[\\pP\\pZ\\pS\\pM\\pC]";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(c);
		return m.find();
	}
	
	private  PunctuationOrHtmlFilteredResult filterPunctation(String originalString){
		StringBuffer filteredString=new StringBuffer();
		ArrayList<Integer> charOffsets=new ArrayList<Integer>();
		for(int i=0;i<originalString.length();i++){
			String c = String.valueOf(originalString.charAt(i));
			if (!isPunctuationChar(c)) {
				filteredString.append(c);
				charOffsets.add(i);
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString(originalString);
		result.setFilteredString(filteredString);
		result.setCharOffsets(charOffsets);
		return result;
	}

	private  PunctuationOrHtmlFilteredResult filterPunctationAndHtml(String originalString){
		StringBuffer filteredString=new StringBuffer();
		ArrayList<Integer> charOffsets=new ArrayList<Integer>();
		for(int i=0,k=0;i<originalString.length();i++){
			String c = String.valueOf(originalString.charAt(i));
			if (originalString.charAt(i) == '<') {
				for(k=i+1;k<originalString.length();k++) {
					if (originalString.charAt(k) == '<') {
						k = i;
						break;
					}
					if (originalString.charAt(k) == '>') {
						break;
					}
				}
				i = k;
			} else {
				if (!isPunctuationChar(c)) {
					filteredString.append(c);
					charOffsets.add(i);
				}
			}
		}
		PunctuationOrHtmlFilteredResult result = new PunctuationOrHtmlFilteredResult();
		result.setOriginalString(originalString);
		result.setFilteredString(filteredString);
		result.setCharOffsets(charOffsets);
		return result;
	}

	
	
	
	private  class PunctuationOrHtmlFilteredResult {
		private String originalString;
		private StringBuffer filteredString;
		private ArrayList<Integer> charOffsets;
		
		public String getOriginalString() {
			return originalString;
		}
		public void setOriginalString(String originalString) {
			this.originalString = originalString;
		}
		public StringBuffer getFilteredString() {
			return filteredString;
		}
		public void setFilteredString(StringBuffer filteredString) {
			this.filteredString = filteredString;
		}
		public ArrayList<Integer> getCharOffsets() {
			return charOffsets;
		}
		public void setCharOffsets(ArrayList<Integer> charOffsets) {
			this.charOffsets = charOffsets;
		}
	}
}
