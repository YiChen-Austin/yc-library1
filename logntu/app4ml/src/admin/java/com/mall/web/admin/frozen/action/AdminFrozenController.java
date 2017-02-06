package com.mall.web.admin.frozen.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mall.common.constant.CommonConstant;
import com.mall.common.poi.PoiExcelUtils;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.util.HqlUtil;
import com.mall.common.vo.PageBean4UI;
import com.mall.web.admin.frozen.service.AdminFrozenService;
import com.mall.web.admin.frozen.util.FrozenPoiUtil;
import com.mall.web.admin.frozen.vo.AdminFrozenVo;

/**
 * @Description(描述) : 商品冻结
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:22:30
 */

@Controller()
@RequestMapping("/admin/frozen/*")
public class AdminFrozenController {
	private static Logger logger = Logger
			.getLogger(AdminFrozenController.class);

	// 冻结
	@Resource(name = "adminFrozenService")
	private AdminFrozenService adminFrozenService;

	/**
	 * @Description(描述) : 下单列表
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object frozenList(PageBean4UI pageBean, AdminFrozenVo frozenVo,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (frozenVo.getStatus() == null) {
				frozenVo.setStatus(1);
			}
			BaseUtil.decodeObject(frozenVo);
			Map<String, Object> params = HqlUtil.beanToMap(frozenVo,
					AdminFrozenVo.class);
			pageBean.init(adminFrozenService.getAllFrozenRow(params));
			List<AdminFrozenVo> list = adminFrozenService.findAllFrozens(
					params, pageBean);
			result.put("rows", list);
			result.put("total", pageBean.getTotalRecords());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/**
	 * @Description(描述) : 下单列表导出
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("export")
	public void frozenExp(PageBean4UI pageBean, AdminFrozenVo frozenVo,
			String isReturn, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			BaseUtil.decodeObject(frozenVo);
			Map<String, Object> params = HqlUtil.beanToMap(frozenVo,
					AdminFrozenVo.class);
			pageBean.setRows(1000);
			pageBean.init(adminFrozenService.getAllFrozenRow(params));

			FileInputStream fis = null;
			XSSFWorkbook xwb = null;
			try {
				String filePath = request.getServletContext().getRealPath("/")
						+ "WEB-INF/classes/admin/template/frozen_list_all.xlsx";

				fis = new FileInputStream(filePath);
				xwb = new XSSFWorkbook(fis); // 构造 XSSFWorkbook 传入文件路径

				XSSFCellStyle cellStyle = xwb.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				XSSFSheet sheet = xwb.getSheetAt(0); // 读取第一章表格内容
				// 定义 row、cell
				// XSSFRow row = sheet.getRow(1);
				int rowNum = 0;
				do {
					List<AdminFrozenVo> list = adminFrozenService
							.findAllFrozens(params, pageBean);
					for (int i = 0; i < list.size(); i++) {
						AdminFrozenVo frozen = list.get(i);

						rowNum++;
						XSSFRow row = sheet.getRow(rowNum);
						row = (row == null ? sheet.createRow(rowNum) : row);

						FrozenPoiUtil.Frozen2Row(frozen, row, cellStyle);
					}
					pageBean.setPage(pageBean.getPage() + 1);
				} while (pageBean.getPage() <= pageBean.getTotalPages());

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Type", "application/force-download");
				response.setHeader("Content-Type", "application/vnd.ms-excel");
				String attachment = "attachment; filename=frozen_"
						+ DateUtil.dateToString(new Date(),
								CommonConstant.DATE_SHORT_FORMAT) + ".xlsx";
				// System.out.println(attachment);
				response.setHeader("Content-disposition", attachment);// 设定输出文件头
				xwb.write(response.getOutputStream());
				xwb.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.warn(e);
			} finally {
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
	}

	/**
	 * @Description(描述) : 导入处理结果（提现）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("impFrozen")
	public ModelAndView impFrozen(@RequestParam MultipartFile[] frozen,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("frozen/frozen_exp");
		try {
			if (frozen == null || frozen.length <= 0)
				return mav;
			List<AdminFrozenVo> list = new LinkedList<AdminFrozenVo>();
			String fileName = frozen[0].getOriginalFilename();
			String filepathtemp = request.getServletContext().getRealPath("/")
					+ "/b2b/tmp";// 缓存文件目录
			String tmpFileName = System.currentTimeMillis() + "."
					+ PoiExcelUtils.getExtensionName(fileName);
			File filelist = new File(filepathtemp);
			if (!filelist.exists() && !filelist.isDirectory()) {
				filelist.mkdir();
			}
			String filePath = filepathtemp
					+ System.getProperty("file.separator") + tmpFileName;
			// 拷贝文件到服务器缓存目录（在项目下）
			PoiExcelUtils.copy(frozen[0], filepathtemp, tmpFileName);// spring
																		// mvc用的方法

			FileInputStream fis = null;
			XSSFWorkbook xwb = null;
			try {
				fis = new FileInputStream(filePath);
				xwb = new XSSFWorkbook(fis); // 构造 XSSFWorkbook// 对象，strPath
												// 传入文件路径
				XSSFSheet sheet = xwb.getSheetAt(0); // 读取第一章表格内容
				// 定义 row、cell
				XSSFRow row;
				for (int i = sheet.getFirstRowNum() + 1; i <= sheet
						.getPhysicalNumberOfRows(); i++) {
					row = sheet.getRow(i);
					if (row == null)
						continue;
					XSSFCell cell_1 = row.getCell(0);
					if (cell_1 == null || BaseUtil.isEmpty(cell_1.toString())) {
						continue;
					}
					XSSFCell cell_2 = row.getCell(1);
					if (cell_2 == null || BaseUtil.isEmpty(cell_2.toString())) {
						continue;
					}
					XSSFCell cell_13 = row.getCell(13);
					if (cell_13 == null || BaseUtil.isEmpty(cell_13.toString())
							|| !"成功".equalsIgnoreCase(cell_13.toString())) {
						continue;
					}
					AdminFrozenVo rvo = new AdminFrozenVo();
					rvo.setFrozen_id((int) Double.parseDouble(cell_1.toString()));
					rvo.setUser_id((int) Double.parseDouble(cell_2.toString()));
					list.add(rvo);
				}

			} catch (IOException e) {
				e.printStackTrace();
				logger.warn(e);
			} finally {
				xwb.close();
				fis.close();
			}
			// 删除缓存文件
			// tmpfile.delete();
			if (list.size() > 0) {
				adminFrozenService.batchFrozen(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return mav;
	}
}
