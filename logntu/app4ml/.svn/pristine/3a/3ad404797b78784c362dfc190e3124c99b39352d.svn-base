package com.mall.common.jxl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

import com.mall.common.constant.CommonConstant;
import com.mall.common.constant.JXLConstant;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;

/**
 * @功能说明：EXCEL工具类
 * @作者： xgyin
 * @创建日期： 2010-10-23 @
 */
public class JXLUtil {
	private static JXLUtil newInstance;

	private static Logger logger = Logger.getLogger(JXLUtil.class);

	/**
	 * 利用单利模式获取一个JXLUtil对象
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public static synchronized JXLUtil getInstance() throws FrameworkException {
		if (BaseUtil.isEmpty(newInstance))
			newInstance = new JXLUtil();
		return newInstance;
	}

	private JXLUtil() {
	}

	/**
	 * 获取Workbook
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(String fileName) throws Exception {
		File file = new File(fileName);
		return Workbook.getWorkbook(file);
	}

	/**
	 * 获取Workbook
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(File file) throws Exception {
		return Workbook.getWorkbook(file);
	}

	/**
	 * 获取Workbook
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(InputStream is) throws Exception {
		return Workbook.getWorkbook(is);
	}

	/**
	 * 获取工作簿
	 * 
	 * @param fileName
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheet(String fileName, int sheet) throws Exception {
		Workbook book = getWorkbook(fileName);
		return book.getSheet(sheet);

	}

	/**
	 * 获取工作簿
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheet(String fileName) throws Exception {
		Workbook book = getWorkbook(fileName);
		return book.getSheet(0);

	}

	/**
	 * 获取工作簿
	 * 
	 * @param file
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheet(File file, int sheet) throws Exception {
		Workbook book = getWorkbook(file);
		return book.getSheet(sheet);

	}

	/**
	 * 获取工作簿
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheet(File file) throws Exception {
		Workbook book = getWorkbook(file);
		return book.getSheet(0);
	}

	/**
	 * 获取工作簿
	 * 
	 * @param file
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public Map<Workbook, Sheet> getSheet(File file, String sheetName)
			throws Exception {
		Map<Workbook, Sheet> map = null;
		Workbook book = getWorkbook(file);
		Sheet[] sheets = book.getSheets();
		for (Sheet sheet : sheets) {
			if (sheet.getName().indexOf(sheetName) > -1) {
				map = new HashMap<Workbook, Sheet>();
				map.put(book, sheet);
				break;
			}
		}
		return map;

	}

	/**
	 * 获取Sheet
	 * 
	 * @param book
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheetBySheetName(Workbook book, String sheetName)
			throws Exception {
		Sheet[] sheets = book.getSheets();
		for (Sheet sheet : sheets) {
			if (sheet.getName().indexOf(sheetName) > -1) {
				return sheet;
			}
		}
		return null;

	}

	/**
	 * 获取指定名字的SHEEET
	 * 
	 * @param book
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public WritableSheet getSheet(WritableWorkbook book, String sheetName)
			throws Exception {
		WritableSheet[] sheets = book.getSheets();
		for (WritableSheet sheet : sheets) {
			if (sheet.getName().indexOf(sheetName) > -1) {
				return sheet;
			}
		}
		return null;

	}

	/**
	 * 获取工作簿
	 * 
	 * @param file
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public Map<Workbook, Sheet> getFullSheet(File file, String sheetName)
			throws Exception {
		Map<Workbook, Sheet> map = null;
		Workbook book = getWorkbook(file);
		Sheet[] sheets = book.getSheets();
		for (Sheet sheet : sheets) {
			if (sheet.getName().indexOf(sheetName) > -1) {
				map = new HashMap<Workbook, Sheet>();
				map.put(book, sheet);
				break;
			}
		}
		return map;

	}

	/**
	 * 获取工作簿
	 * 
	 * @param is
	 * @param sheet
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheet(InputStream is, int sheet) throws Exception {
		Workbook book = getWorkbook(is);
		return book.getSheet(sheet);

	}

	/**
	 * 获取工作簿
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public Sheet getSheet(InputStream is) throws Exception {
		Workbook book = getWorkbook(is);
		return book.getSheet(0);

	}

	/**
	 * 获取特定单元格内容
	 * 
	 * @param sheet
	 * @param row
	 * @param col
	 * @return
	 * @throws Exception
	 */
	public String getContent(Sheet sheet, int row, int col) throws Exception {
		return sheet.getCell(col, row).getContents();
	}

	/**
	 * 获取指定区域的单元格内容
	 * 
	 * @param sheet
	 * @param fromRow
	 * @param toRow
	 * @param fromCol
	 * @param toCol
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getContents(Sheet sheet, int fromRow, int toRow,
			int fromCol, int toCol) throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();
		for (int i = fromRow; i <= toRow; i++) {
			contents.add(getContents(sheet, i, fromCol, toCol));
		}
		return contents;

	}

	/**
	 * 获取特定行单元格的内容
	 * 
	 * @param sheet
	 * @param row
	 * @param fromCol
	 * @param toCol
	 * @return
	 * @throws Exception
	 */
	public List<String> getContents(Sheet sheet, int row, int fromCol, int toCol)
			throws Exception {
		List<String> contents = new ArrayList<String>();
		for (int j = fromCol; j <= toCol; j++) {
			contents.add(sheet.getCell(j, row).getContents());
		}
		return contents;
	}

	public int getContents(Sheet sheet, int row, int fromCol, int toCol,
			List<String> list) throws Exception {
		int length = 0;
		String content;
		for (int j = fromCol; j <= toCol; j++) {
			content = sheet.getCell(j, row).getContents();
			list.add(content);
			if (content != null && content.length() > 0) {
				length++;
			}
		}
		return length;
	}

	/**
	 * 获取不连续的多行数据
	 * 
	 * @param sheet
	 * @param rows
	 * @param fromCol
	 * @param toCol
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getContents(Sheet sheet, int[] rows, int fromCol,
			int toCol) throws Exception {
		List<List<String>> contents = new ArrayList<List<String>>();
		int len = rows.length;
		for (int i = 0; i < len; i++) {
			contents.add(getContents(sheet, rows[i], fromCol, toCol));
		}
		return contents;

	}

	/**
	 * 设置字体样式，参数顺序任意，但一定要注意参数的类型 字体(String)、大小(Integer)、加粗(Boolean)
	 * 
	 * @param param
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public WritableFont setFont(Object... param) throws FrameworkException,
			Exception {
		// 默认字体
		String fontName = JXLConstant.FONT_NAME_DEFAULT;
		for (Object _obj : param)
			// 设置字体
			if (_obj instanceof String)
				fontName = (String) _obj;
		WritableFont wFont = new WritableFont(WritableFont.createFont(fontName));
		for (int i = 0; i < param.length; i++) {
			Object _obj = param[i];
			// 设置字体颜色
			if (_obj instanceof Colour)
				wFont.setColour((Colour) _obj);
			// 设置字体大小
			if (_obj instanceof Integer)
				wFont.setPointSize((Integer) _obj);
			// 设置是否加粗
			if (_obj instanceof Boolean)
				if ((Boolean) _obj)
					wFont.setBoldStyle(jxl.write.WritableFont.BOLD);
		}
		return wFont;
	}

	/**
	 * 设置对齐方式
	 * 
	 * @param alignment
	 *            : 对齐方式
	 * @param param
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public WritableCellFormat setFormat(Alignment alignment, Object... param)
			throws FrameworkException, Exception {
		WritableCellFormat format = new WritableCellFormat(setFont(param));
		if (!BaseUtil.isEmpty(alignment))
			format.setAlignment(alignment);
		// 设置边框
		setBorder(format);
		return format;
	}

	/**
	 * 设置边框
	 * 
	 * @param format
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public void setBorder(WritableCellFormat format) throws FrameworkException,
			Exception {
		format.setBorder(JXLConstant.BORDER_ALL, JXLConstant.BORDER_LINE_THIN);
	}

	/**
	 * 创建Excel文件
	 * 
	 * @param textFont
	 * @param textBackground
	 * @param headerFont
	 * @param headerBackground
	 * 
	 * @param labels
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void createWorkbook(OutputStream path, String name,
			List<?> dataSource, Map<String, String[]> param,
			Colour headerBackground, Colour headerFont, Colour textBackground,
			Colour textFont) throws FrameworkException, Exception {
		WritableWorkbook wwBook = Workbook.createWorkbook(path);
		WritableSheet wSheet = wwBook.createSheet(name, 0);
		Map<String, Object> result = dataToLabel(dataSource, param,
				textBackground, textFont);
		List<Label> labels = (List<Label>) result.get("labels");
		List<Number> numbers = (List<Number>) result.get("numbers");
		List<DateTime> dateTimes = (List<DateTime>) result.get("dateTimes");
		// 加入列单元头
		labels.addAll(dataToLabelHead(param, headerBackground, headerFont));
		// String类型元素
		for (Label label : labels)
			wSheet.addCell(label);
		// Number类型元素
		for (Number number : numbers)
			wSheet.addCell(number);
		// datetTime类型元素
		for (DateTime datetTime : dateTimes)
			wSheet.addCell(datetTime);
		List<String[]> values = new ArrayList<String[]>(param.values());
		for (int i = 0; i < param.size(); i++) {
			String[] vals = values.get(i);
			// 参数2存在且为纯数字
			if ((vals.length >= 2) && isInteger(vals[1]))
				// 根据用户传入参数设置宽度
				wSheet.setColumnView(i, Integer.parseInt(vals[1]));
			if ((vals.length >= 2) && !isInteger(vals[1]))
				logger.info("长度参数请用纯数字的String类型!");
		}
		// 如果不写 能创建 但sheet名字为xls名字
		wwBook.write();
		wwBook.close();
	}

	/**
	 * 设置Excel列单元头
	 * 
	 * @param param
	 * @param headerFont
	 * @param headerBackground
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public List<Label> dataToLabelHead(Map<String, String[]> param,
			Colour headerBackground, Colour headerFont)
			throws FrameworkException, Exception {
		List<Label> labels = new ArrayList<Label>();
		Label label = null;
		List<String[]> values = new ArrayList<String[]>(param.values());
		for (int i = 0; i < values.size(); i++) {
			WritableCellFormat lableFormat = setFormat(JXLConstant.CENTER,
					JXLConstant.FONT_SIZE, true);
			if (!BaseUtil.isEmpty(headerFont))
				lableFormat = setFormat(JXLConstant.CENTER,
						JXLConstant.FONT_SIZE, true, headerFont);
			if (!BaseUtil.isEmpty(headerBackground))
				lableFormat.setBackground(headerBackground);
			// 设置风格 此风格是居中、11号字、加粗
			label = new Label(i, 0, values.get(i)[0], lableFormat);
			labels.add(label);
		}
		return labels;
	}

	/**
	 * 将数据源格式化为Label或Number或DateTime (Integer Long Float Double封装为Number)
	 * 
	 * @param dataSource
	 * @param columnNames
	 * @param fromRow
	 * @param fromColumn
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> dataToLabel(Map<String, Object> result,
			List<?> dataSource, List<String> columnNames, int fromRow,
			int fromColumn) throws FrameworkException, Exception {
		if (BaseUtil.isEmpty(dataSource))
			return result;
		if (BaseUtil.isEmpty(result))
			result = new HashMap<String, Object>();
		// Strings
		List<Label> labels = (List<Label>) result.get("labels");
		if (BaseUtil.isEmpty(labels))
			labels = new ArrayList<Label>();
		// Numbers
		List<Number> numbers = (List<Number>) result.get("numbers");
		if (BaseUtil.isEmpty(numbers))
			numbers = new ArrayList<Number>();
		// DateTimes
		List<DateTime> dateTimes = (List<DateTime>) result.get("dateTimes");
		if (BaseUtil.isEmpty(dateTimes))
			dateTimes = new ArrayList<DateTime>();
		Label label = null;
		Number number = null;
		DateTime dateTime = null;
		Object val = null;
		for (int i = 0; i < dataSource.size(); i++) {
			for (int j = 0; j < columnNames.size(); j++) {
				val = doGetMethod(dataSource.get(i), columnNames.get(j));
				if (val instanceof Date) {
					WritableCellFormat dateTimeFormat = new WritableCellFormat(
							new DateFormat(
									CommonConstant.DATE_WITHSECOND_FORMAT));
					dateTimeFormat.setAlignment(JXLConstant.CENTER);
					// 设置边框
					setBorder(dateTimeFormat);
					// DateTime
					dateTime = new DateTime(fromColumn + j, fromRow + i,
							getDate(val), dateTimeFormat);
					dateTimes.add(dateTime);
				}
				// 不为Integer、Long、Float且不为Double
				else if (!(val instanceof Integer) && !(val instanceof Double)
						&& !(val instanceof Long) && !(val instanceof Float)) {
					WritableCellFormat lableFormat = setFormat(null);
					label = new Label(fromColumn + j, fromRow + i,
							String.valueOf(val), lableFormat);
					labels.add(label);
				} else {
					WritableCellFormat integerFormat = new WritableCellFormat(
							new NumberFormat(JXLConstant.INTEGER_NUM_FORMAT));
					integerFormat.setAlignment(JXLConstant.CENTER);
					WritableCellFormat doubleFormat = new WritableCellFormat(
							new NumberFormat(JXLConstant.DOUBLE_NUM_FORMAT));
					doubleFormat.setAlignment(JXLConstant.CENTER);
					// 设置边框
					setBorder(integerFormat);
					setBorder(doubleFormat);
					// Integer Long
					if ((val instanceof Integer) || (val instanceof Long)) {
						number = new Number(fromColumn + j, fromRow + i,
								getLong(val), integerFormat);
						numbers.add(number);
					}
					// Double Float
					if ((val instanceof Double) || (val instanceof Float)) {
						number = new Number(fromColumn + j, fromRow + i,
								getDouble(val), doubleFormat);
						numbers.add(number);
					}
				}
			}
		}
		result.put("labels", labels);
		result.put("numbers", numbers);
		result.put("dateTimes", dateTimes);
		return result;
	}

	/**
	 * 将数据源格式化为Label或Number或DateTime (Integer Long Float Double封装为Number)
	 * 
	 * @param dataSource
	 * @param param
	 * @param textFont
	 * @param textBackground
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public Map<String, Object> dataToLabel(List<?> dataSource,
			Map<String, String[]> param, Colour textBackground, Colour textFont)
			throws FrameworkException, Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		// Strings
		List<Label> labels = new ArrayList<Label>();
		// Numbers
		List<Number> numbers = new ArrayList<Number>();
		// DateTimes
		List<DateTime> dateTimes = new ArrayList<DateTime>();
		Label label = null;
		Number number = null;
		DateTime dateTime = null;
		Object val = null;
		for (int i = 0; i < dataSource.size(); i++) {
			List<String> keys = new ArrayList<String>(param.keySet());
			for (int j = 0; j < keys.size(); j++) {
				val = doGetMethod(dataSource.get(i), keys.get(j));
				if (val instanceof Date) {
					WritableCellFormat dateTimeFormat = new WritableCellFormat(
							new DateFormat(
									CommonConstant.DATE_WITHSECOND_FORMAT));
					dateTimeFormat.setAlignment(JXLConstant.CENTER);
					if (!BaseUtil.isEmpty(textBackground))
						dateTimeFormat.setBackground(textBackground);
					// 设置边框
					setBorder(dateTimeFormat);
					// DateTime
					dateTime = new DateTime(j, i + 1, getDate(val),
							dateTimeFormat);
					dateTimes.add(dateTime);
				}
				// 不为Integer、Long、Float且不为Double
				else if (!(val instanceof Integer) && !(val instanceof Double)
						&& !(val instanceof Long) && !(val instanceof Float)) {
					WritableCellFormat lableFormat = setFormat(null);
					if (!BaseUtil.isEmpty(textFont))
						lableFormat = setFormat(JXLConstant.CENTER, textFont);
					if (!BaseUtil.isEmpty(textBackground))
						lableFormat.setBackground(textBackground);
					label = new Label(j, i + 1, String.valueOf(val),
							lableFormat);
					labels.add(label);
				} else {
					WritableCellFormat integerFormat = new WritableCellFormat(
							new NumberFormat(JXLConstant.INTEGER_NUM_FORMAT));
					integerFormat.setAlignment(JXLConstant.CENTER);
					WritableCellFormat doubleFormat = new WritableCellFormat(
							new NumberFormat(JXLConstant.DOUBLE_NUM_FORMAT));
					doubleFormat.setAlignment(JXLConstant.CENTER);
					if (!BaseUtil.isEmpty(textBackground)) {
						doubleFormat.setBackground(textBackground);
						integerFormat.setBackground(textBackground);
					}
					// 设置边框
					setBorder(integerFormat);
					setBorder(doubleFormat);
					// Integer Long
					if ((val instanceof Integer) || (val instanceof Long)) {
						number = new Number(j, i + 1, getLong(val),
								integerFormat);
						numbers.add(number);
					}
					// Double Float
					if ((val instanceof Double) || (val instanceof Float)) {
						number = new Number(j, i + 1, getDouble(val),
								doubleFormat);
						numbers.add(number);
					}
				}
			}
		}
		result.put("labels", labels);
		result.put("numbers", numbers);
		result.put("dateTimes", dateTimes);
		return result;
	}

	/**
	 * 通过成员名调用get成员值并返回其值
	 * 
	 * @param instance
	 * @param methodName
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
	private Object doGetMethod(Object instance, String methodName)
			throws FrameworkException, Exception {
		String getMethodName = new StringBuffer("get").append(
				methodName.toLowerCase()).toString();
		Method[] methods = instance.getClass().getMethods();
		for (Method _method : methods)
			if (getMethodName.equals(_method.getName().toLowerCase()))
				return BaseUtil.isEmpty(_method.invoke(instance)) ? ""
						: _method.invoke(instance);
		return null;
	}

	/**
	 * 导出Excel
	 * 
	 * @param fileName
	 *            : 文件名，sheet名
	 * @param dataSource
	 *            : 需要显示的bean数据源
	 * @param param
	 *            : LinkedHashMap<String,
	 *            String[]>类型，String[]里第1个参数为单元头显示的中文，第2个参数为单元格长度，如果无第2个参数，会默认长度
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public void toStandardExcel(String fileName, List<?> dataSource,
			LinkedHashMap<String, String[]> param) throws FrameworkException,
			Exception {
		toStandardExcel(fileName, dataSource, param, null, null, null, null);
	}

	/**
	 * 导出Excel
	 * 
	 * @param fileName
	 *            : 文件名，sheet名
	 * @param headerBackground
	 *            :表头背景色
	 * @param headerFont
	 *            ：表头字体颜色
	 * @param dataSource
	 *            : 需要显示的bean数据源
	 * @param param
	 *            : LinkedHashMap<String,
	 *            String[]>类型，String[]里第1个参数为单元头显示的中文，第2个参数为单元格长度，如果无第2个参数，会默认长度
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public void toStandardExcel(String fileName, List<?> dataSource,
			LinkedHashMap<String, String[]> param, Colour headerBackground,
			Colour headerFont) throws FrameworkException, Exception {
		toStandardExcel(fileName, dataSource, param, headerBackground,
				headerFont, null, null);
	}

	/**
	 * 导出Excel
	 * 
	 * @param fileName
	 *            : 文件名，sheet名
	 * @param headerBackground
	 *            :表头背景色
	 * @param headerFont
	 *            ：表头字体颜色
	 * @param textBackground
	 *            ：正文背景色
	 * @param textFont
	 *            ：正文字体颜色
	 * @param dataSource
	 *            : 需要显示的bean数据源
	 * @param param
	 *            : LinkedHashMap<String,
	 *            String[]>类型，String[]里第1个参数为单元头显示的中文，第2个参数为单元格长度，如果无第2个参数，会默认长度
	 * @throws FrameworkException
	 * @throws Exception
	 */
	public void toStandardExcel(String fileName, List<?> dataSource,
			LinkedHashMap<String, String[]> param, Colour headerBackground,
			Colour headerFont, Colour textBackground, Colour textFont)
			throws FrameworkException, Exception {
		// sheet的名字，fileName会改变编码格式，所以先复制个
//		String sheetName = new String(fileName);
//		// 转换为当前浏览器支持的中文
//		fileName = getEncodeText(fileName);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding(JXLConstant.UTF_ENCODING);
//		response.setContentType(JXLConstant.CONTENT_TYPE);
//		response.setHeader(
//				JXLConstant.HEADER,
//				new StringBuffer(JXLConstant.OUTPUTSTREAM_DOWN)
//						.append(";filename=").append(fileName)
//						.append(JXLConstant.EXCEL_SUFFIX).toString());
//		createWorkbook(response.getOutputStream(), sheetName, dataSource,
//				param, headerBackground, headerFont, textBackground, textFont);
	}
	
	/**
	 * 通过浏览器类型，返回浏览器支持的中文 (目前常用内核，个人觉得就这三大类)
	 * 
	 * @param text
	 * @return
	 * @throws FrameworkException
	 * @throws Exception
	 */
//	public String getEncodeText(String text) throws FrameworkException,
//			Exception {
//		String agent = ServletActionContext.getRequest()
//				.getHeader(JXLConstant.USER_AGENT).toUpperCase();
//		// IE
//		if (!BaseUtil.isEmpty(agent)
//				&& -1 != agent.indexOf(JXLConstant.BROWSER_MSIE))
//			return URLEncoder.encode(text, JXLConstant.UTF_ENCODING);
//		// Mozilla
//		else if (!BaseUtil.isEmpty(agent)
//				&& -1 != agent.indexOf(JXLConstant.BROWSER_MOZILLA))
//			return new String(text.getBytes(), JXLConstant.ISO_ENCODING);
//		// Opera
//		else if (!BaseUtil.isEmpty(agent)
//				&& -1 != agent.indexOf(JXLConstant.BROWSER_OPERA))
//			return new String(text.getBytes(), JXLConstant.ISO_ENCODING);
//		return text;
//	}

	/**
	 * 判断是否为Integer
	 * 
	 * @param value
	 * @return
	 */
	public boolean isInteger(String value) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile(JXLConstant.NUMBER_KEY);
		java.util.regex.Matcher match = pattern.matcher(value);
		if (match.matches() == false)
			return false;
		return true;
	}

	/**
	 * 返回double
	 * 
	 * @param obj
	 * @return
	 */
	public double getDouble(Object obj) {
		return Double.valueOf(String.valueOf(obj));
	}

	/**
	 * 返回日期
	 * 
	 * @param obj
	 * @return
	 */
	public Date getDate(Object obj) {
		if (obj instanceof Date)
			return (Date) obj;
		return null;
	}

	/**
	 * 返回long
	 * 
	 * @param obj
	 * @return
	 */
	public long getLong(Object obj) {
		return Long.valueOf(String.valueOf(obj));
	}

	public static WritableWorkbook getWritableWorkbook(Workbook workBook,
			ServletOutputStream outputStream) throws IOException {
		// 复制workbook并保存到outputStream
		WritableWorkbook copy = Workbook.createWorkbook(outputStream, workBook,
				getWorkbookSettings());
		return copy;
	}

	public static WorkbookSettings getWorkbookSettings() {
		WorkbookSettings settings = new WorkbookSettings();
		settings.setWriteAccess(null);
		return settings;
	}

	public static ServletOutputStream getRespOutputStream(
			HttpServletResponse response, String fileName) throws IOException {
		response.setCharacterEncoding(JXLConstant.UTF_ENCODING);
		response.setContentType(JXLConstant.CONTENT_TYPE);
		response.setHeader(
				JXLConstant.HEADER,
				new StringBuffer(JXLConstant.OUTPUTSTREAM_DOWN)
						.append(";filename=").append(fileName)
						.append(JXLConstant.EXCEL_SUFFIX).toString());
		return response.getOutputStream();
	}

	public static WritableCellFormat getWcFormat() throws WriteException {
		WritableCellFormat wcFormat = new WritableCellFormat();
		wcFormat.setBorder(JXLConstant.BORDER_ALL, JXLConstant.BORDER_LINE_THIN);
		wcFormat.setAlignment(Alignment.CENTRE);
		return wcFormat;
	}
}
