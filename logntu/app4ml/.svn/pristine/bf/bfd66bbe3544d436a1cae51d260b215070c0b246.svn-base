package com.mall.web.admin.order.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.mall.web.admin.order.service.AdminOrderService;
import com.mall.web.admin.order.util.OrderPoiUtil;
import com.mall.web.admin.order.vo.AdminOrderGoodsVo;
import com.mall.web.admin.order.vo.AdminOrderVo;

/**
 * @Description(描述) : 商品订单
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:22:30
 */

@Controller()
@RequestMapping("/admin/order/*")
public class AdminOrderController {
	private static Logger logger = Logger.getLogger(AdminOrderController.class);

	// 商品订单
	@Resource(name = "adminOrderService")
	private AdminOrderService adminOrderService;

	/**
	 * @Description(描述) : 下单列表
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object orderList(PageBean4UI pageBean, AdminOrderVo orderVo,
			String isReturn, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			BaseUtil.decodeObject(orderVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderVo,
					AdminOrderVo.class);
			pageBean.init(adminOrderService.getAllOrderRow(
					BaseUtil.isEmpty(isReturn) ? false : true, params));
			List<AdminOrderVo> list = adminOrderService
					.findAllOrders(BaseUtil.isEmpty(isReturn) ? false : true,
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
	 * @Description(描述) : 下单列表(状态)
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("orderStat")
	@ResponseBody
	public Object orderStat(AdminOrderVo orderVo, String isReturn,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			BaseUtil.decodeObject(orderVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderVo,
					AdminOrderVo.class);
			List<AdminOrderVo> list = adminOrderService.findAllOrders4stat(
					(BaseUtil.isEmpty(isReturn) ? false : true), params);
			AdminOrderVo aovo = adminOrderService.findAllOrders4statSum(
					(BaseUtil.isEmpty(isReturn) ? false : true), params);
			if (!BaseUtil.isEmpty(aovo)) {
				aovo.setSeller_name("合计");
			}
			//list.add(aovo);
			ArrayList<AdminOrderVo> footers = new ArrayList<AdminOrderVo>();
			footers.add(aovo);
			result.put("footer", footers);
			
			result.put("rows", list);
			result.put("total", list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/**
	 * @Description(描述) : 下单列表(退货退款)
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 * 
	 * @param rtype
	 *            (1--待退款，2--退款成功)
	 */
	@RequestMapping("refunds")
	@ResponseBody
	public Object orderRefunds(PageBean4UI pageBean, String rtype,
			AdminOrderVo orderVo, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			BaseUtil.decodeObject(orderVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderVo,
					AdminOrderVo.class);
			if (BaseUtil.isEmpty(rtype) || "1".equalsIgnoreCase(rtype)) {
				pageBean.init(adminOrderService.getAllUnRefundOrderRow(params));
				List<AdminOrderVo> list = adminOrderService
						.findAllUnRefundOrders(params, pageBean);
				result.put("rows", list);
				result.put("total", pageBean.getTotalRecords());
			} else if ("2".equalsIgnoreCase(rtype)) {
				pageBean.init(adminOrderService.getAllRefundOrderRow(params));
				List<AdminOrderVo> list = adminOrderService
						.findAllRefundOrders(params, pageBean);
				result.put("rows", list);
				result.put("total", pageBean.getTotalRecords());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return result;
	}

	/**
	 * @Description(描述) : 下单产品列表
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("lgoods")
	@ResponseBody
	public Object orderGoodsList(PageBean4UI pageBean,
			AdminOrderGoodsVo orderGoodsVo, String isReturn,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			BaseUtil.decodeObject(orderGoodsVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderGoodsVo,
					AdminOrderGoodsVo.class);
			pageBean.init(adminOrderService.getAllOrderGoodsRow(
					BaseUtil.isEmpty(isReturn) ? false : true, params));
			List<AdminOrderGoodsVo> list = adminOrderService
					.findAllOrderGoods(BaseUtil.isEmpty(isReturn) ? false
							: true, params, pageBean);
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
	public void orderExp(PageBean4UI pageBean, AdminOrderVo orderVo,
			String isReturn, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			BaseUtil.decodeObject(orderVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderVo,
					AdminOrderVo.class);
			pageBean.setRows(1000);
			pageBean.init(adminOrderService.getAllOrderRow(
					BaseUtil.isEmpty(isReturn) ? false : true, params));

			FileInputStream fis = null;
			XSSFWorkbook xwb = null;
			try {
				String filePath = request.getServletContext().getRealPath("/")
						+ "WEB-INF/classes/admin/template/order_list_all.xlsx";

				fis = new FileInputStream(filePath);
				xwb = new XSSFWorkbook(fis); // 构造 XSSFWorkbook 传入文件路径

				XSSFCellStyle cellStyle = xwb.createCellStyle();
				// 设置这些样式
				// cellStyle.setFillBackgroundColor(new XSSFColor(Color.WHITE));
				// cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				// cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
				// cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				// cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
				// cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);

				// 设置单元格边框颜色
				// cellStyle.setBottomBorderColor(new XSSFColor(Color.BLACK));
				// cellStyle.setTopBorderColor(new XSSFColor(Color.BLACK));
				// cellStyle.setLeftBorderColor(new XSSFColor(Color.BLACK));
				// cellStyle.setRightBorderColor(new XSSFColor(Color.BLACK));

				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				// cellStyle.setFillForegroundColor(XSSFColor.toXSSFColor(Color.black));
				// cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				// cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				// cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				// cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				// cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				// cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

				XSSFSheet sheet = xwb.getSheetAt(0); // 读取第一章表格内容
				// 定义 row、cell
				// XSSFRow row = sheet.getRow(1);
				int rowNum = 0;
				do {
					List<AdminOrderVo> list = adminOrderService.findAllOrders(
							BaseUtil.isEmpty(isReturn) ? false : true, params,
							pageBean);
					for (int i = 0; i < list.size(); i++) {
						AdminOrderVo order = list.get(i);

						rowNum++;
						XSSFRow row = sheet.getRow(rowNum);
						row = (row == null ? sheet.createRow(rowNum) : row);

						OrderPoiUtil.Order2Row(order, row, cellStyle);
					}
					pageBean.setPage(pageBean.getPage() + 1);
				} while (pageBean.getPage() <= pageBean.getTotalPages());

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Type", "application/force-download");
				response.setHeader("Content-Type", "application/vnd.ms-excel");
				String attachment = "attachment; filename=order_"
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
	 * @Description(描述) : 下单列表导出
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("expGoods")
	public void orderGoodsExp(PageBean4UI pageBean,
			AdminOrderGoodsVo orderGoodsVo, String isReturn,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			BaseUtil.decodeObject(orderGoodsVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderGoodsVo,
					AdminOrderGoodsVo.class);
			pageBean.setRows(1000);
			pageBean.init(adminOrderService.getAllOrderGoodsRow(
					BaseUtil.isEmpty(isReturn) ? false : true, params));

			FileInputStream fis = null;
			XSSFWorkbook xwb = null;
			try {
				String filePath = request.getServletContext().getRealPath("/")
						+ "WEB-INF/classes/admin/template/order_goods_list_all.xlsx";

				fis = new FileInputStream(filePath);
				xwb = new XSSFWorkbook(fis); // 构造 XSSFWorkbook 传入文件路径

				XSSFCellStyle cellStyle = xwb.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				XSSFSheet sheet = xwb.getSheetAt(0); // 读取第一章表格内容
				// 定义 row、cell
				// XSSFRow row = sheet.getRow(1);
				int rowNum = 0;
				do {
					List<AdminOrderGoodsVo> list = adminOrderService
							.findAllOrderGoods(
									BaseUtil.isEmpty(isReturn) ? false : true,
									params, pageBean);
					for (int i = 0; i < list.size(); i++) {
						AdminOrderGoodsVo goods = list.get(i);

						rowNum++;
						XSSFRow row = sheet.getRow(rowNum);
						row = (row == null ? sheet.createRow(rowNum) : row);

						OrderPoiUtil.OrderGoods2Row(goods, row, cellStyle);
					}
					pageBean.setPage(pageBean.getPage() + 1);
				} while (pageBean.getPage() <= pageBean.getTotalPages());

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Type", "application/force-download");
				response.setHeader("Content-Type", "application/vnd.ms-excel");
				String attachment = "attachment; filename=goods_"
						+ DateUtil.dateToString(new Date(),
								CommonConstant.DATE_SHORT_FORMAT) + ".xlsx";
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
	 * @Description(描述) : 下单列表导出(退货退款)
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("expRefund")
	public void orderExpRefund(PageBean4UI pageBean, String rtype,
			AdminOrderVo orderVo, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			BaseUtil.decodeObject(orderVo);
			Map<String, Object> params = HqlUtil.beanToMap(orderVo,
					AdminOrderVo.class);
			pageBean.setRows(1000);

			if (BaseUtil.isEmpty(rtype) || "1".equalsIgnoreCase(rtype)) {
				pageBean.init(adminOrderService.getAllUnRefundOrderRow(params));
			} else if ("2".equalsIgnoreCase(rtype)) {
				pageBean.init(adminOrderService.getAllRefundOrderRow(params));
			} else {
				return;
			}

			FileInputStream fis = null;
			XSSFWorkbook xwb = null;
			try {
				String filePath = null;
				if (BaseUtil.isEmpty(rtype) || "1".equalsIgnoreCase(rtype)) {
					filePath = request.getServletContext().getRealPath("/")
							+ "WEB-INF/classes/admin/template/refund_list_all.xlsx";
				} else if ("2".equalsIgnoreCase(rtype)) {
					filePath = request.getServletContext().getRealPath("/")
							+ "WEB-INF/classes/admin/template/order_list_all.xlsx";
				}

				fis = new FileInputStream(filePath);
				xwb = new XSSFWorkbook(fis); // 构造 XSSFWorkbook 传入文件路径

				XSSFCellStyle cellStyle = xwb.createCellStyle();

				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				XSSFSheet sheet = xwb.getSheetAt(0); // 读取第一章表格内容
				// 定义 row、cell
				// XSSFRow row = sheet.getRow(1);
				int rowNum = 0;
				do {
					List<AdminOrderVo> list = null;

					if (BaseUtil.isEmpty(rtype) || "1".equalsIgnoreCase(rtype)) {
						list = adminOrderService.findAllUnRefundOrders(params,
								pageBean);
					} else if ("2".equalsIgnoreCase(rtype)) {
						list = adminOrderService.findAllRefundOrders(params,
								pageBean);
					}
					for (int i = 0; list != null && i < list.size(); i++) {
						AdminOrderVo order = list.get(i);

						rowNum++;
						XSSFRow row = sheet.getRow(rowNum);
						row = (row == null ? sheet.createRow(rowNum) : row);

						if (BaseUtil.isEmpty(rtype)
								|| "1".equalsIgnoreCase(rtype)) {
							OrderPoiUtil.refund2Row(order, row, cellStyle);
						} else if ("2".equalsIgnoreCase(rtype)) {
							OrderPoiUtil.Order2Row(order, row, cellStyle);
						}
					}
					pageBean.setPage(pageBean.getPage() + 1);
				} while (pageBean.getPage() <= pageBean.getTotalPages());

				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Type", "application/force-download");
				response.setHeader("Content-Type", "application/vnd.ms-excel");
				String attachment = "attachment; filename=refund_"
						+ DateUtil.dateToString(new Date(),
								CommonConstant.DATE_SHORT_FORMAT) + ".xlsx";
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
	 * @Description(描述) : 导入处理结果（退款）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年8月24日 下午4:07:17
	 */
	@RequestMapping("impRefund")
	public ModelAndView importRefund(@RequestParam MultipartFile[] refund,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/refund_exp");
		try {
			if (refund == null || refund.length <= 0)
				return mav;
			List<AdminOrderVo> list = new LinkedList<AdminOrderVo>();
			String fileName = refund[0].getOriginalFilename();
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
			File tmpfile = new File(filePath);
			// 拷贝文件到服务器缓存目录（在项目下）
			PoiExcelUtils.copy(refund[0], filepathtemp, tmpFileName);// spring
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
					XSSFCell cell_7 = row.getCell(7);
					if (cell_7 == null || BaseUtil.isEmpty(cell_7.toString())) {
						continue;
					}
					XSSFCell cell_8 = row.getCell(8);
					if (cell_8 == null || BaseUtil.isEmpty(cell_8.toString())
							|| !"成功".equalsIgnoreCase(cell_8.toString())) {
						continue;
					}
					AdminOrderVo rvo = new AdminOrderVo();
					rvo.setOrder_sn(cell_1.toString());
					rvo.setOrder_amount(BigDecimal.valueOf(Double
							.parseDouble(cell_7.toString())));
					rvo.setConsignee(cell_1.toString() + "|"
							+ cell_7.toString() + "|" + cell_8.toString());
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
			tmpfile.delete();
			if (list.size() > 0) {
				adminOrderService.batchRefund(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
		}
		return mav;
	}
}
