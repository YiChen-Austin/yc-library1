package com.mall.web.admin.order.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.mall.web.admin.order.vo.AdminOrderGoodsVo;
import com.mall.web.admin.order.vo.AdminOrderVo;

public class OrderPoiUtil {
	public static void Order2Row(AdminOrderVo order, XSSFRow row,
			CellStyle cellStyle) {
		XSSFCell cell = row.createCell(0);
		cell.setCellValue(order.getSeller_name());
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(1);
		cell.setCellValue(order.getAdd_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue(order.getPay_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue(order.getShip_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue(order.getFinished_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue(order.getFinished_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue(order.getRecord_time());
		cell.setCellStyle(cellStyle);

		// cell = row.createCell(5);
		// cell.setCellValue(order.getEvaluation_time());
		// cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue(order.getOrder_sn());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue(order.getStatus() == 0 ? "已取消"
				: order.getStatus() == 11 ? "待付款"
						: order.getStatus() == 20 ? "已付款"
								: order.getStatus() == 30 ? "已发货" : order
										.getStatus() == 40 ? "已收货" : order
										.getStatus() == 50 ? "已结束" : "异常");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue(order.getRefund_type() == null ? "" : order
				.getRefund_type() == 1 ? "仅退款"
				: order.getRefund_type() == 2 ? "退货退款" : "");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue(order.getRefund_status() == 0 ? "" : order
				.getRefund_status() == 1 ? "待商家处理"
				: order.getRefund_status() == 2 ? "商家拒绝" : order
						.getRefund_status() == 3 ? "商家同意" : order
						.getRefund_status() == 4 ? "商家同意" : order
						.getRefund_status() == 5 ? "退款成功" : order
						.getRefund_status() == 6 ? "退款取消" : "");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(11);
		cell.setCellValue(order.getGoods_amount() == null ? "" : order
				.getGoods_amount().toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(12);
		cell.setCellValue(order.getOrder_amount() == null ? "" : order
				.getOrder_amount().toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(13);
		cell.setCellValue(order.getPayMoney() == null ? "" : order
				.getPayMoney().toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(14);
		cell.setCellValue(order.getCreduce() == null ? "" : order.getCreduce()
				.toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(15);
		cell.setCellValue(order.getRreduce() == null ? "" : order.getRreduce()
				.toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(16);
		cell.setCellValue(order.getOreduce() == null ? "" : order.getOreduce()
				.toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(17);
		cell.setCellValue(order.getPayIdentity2());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(18);
		cell.setCellValue(order.getPayIdentity());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(19);
		cell.setCellValue(order.getInvoice_name());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(20);
		cell.setCellValue(order.getInvoice_no());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(21);
		cell.setCellValue(order.getBuyer_name());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(22);
		cell.setCellValue(order.getConsignee());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(23);
		cell.setCellValue(order.getPhoneMob());
		cell.setCellStyle(cellStyle);
	}

	public static void refund2Row(AdminOrderVo order, XSSFRow row,
			CellStyle cellStyle) {
		XSSFCell cell = row.createCell(0);
		cell.setCellValue(order.getOrder_sn());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue(order.getRecord_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue(order.getStatus() == 0 ? "已取消"
				: order.getStatus() == 11 ? "待付款"
						: order.getStatus() == 20 ? "已付款"
								: order.getStatus() == 30 ? "已发货" : order
										.getStatus() == 40 ? "已收货" : order
										.getStatus() == 50 ? "已结束" : "异常");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue(order.getPayMoney() == null ? "" : order
				.getPayMoney().toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue(order.getPayIdentity2());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue(order.getPayIdentity());
		cell.setCellStyle(cellStyle);
	}

	public static void OrderGoods2Row(AdminOrderGoodsVo orderGoods,
			XSSFRow row, CellStyle cellStyle) {
		XSSFCell cell = row.createCell(0);
		cell.setCellValue(orderGoods.getOrder_sn());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue(orderGoods.getAdd_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue(orderGoods.getPay_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue(orderGoods.getShip_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue(orderGoods.getFinished_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue(orderGoods.getFinished_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue(orderGoods.getStatus() == 0 ? "已取消" : orderGoods
				.getStatus() == 11 ? "待付款"
				: orderGoods.getStatus() == 20 ? "已付款"
						: orderGoods.getStatus() == 30 ? "已发货" : orderGoods
								.getStatus() == 40 ? "已收货" : orderGoods
								.getStatus() == 50 ? "已结束" : "异常");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue(orderGoods.getCate_name());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue(orderGoods.getGoods_name());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue(orderGoods.getPrice() == null ? "" : orderGoods
				.getPrice().toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue(orderGoods.getQuantity() == null ? "" : orderGoods
				.getQuantity().toString());
		cell.setCellStyle(cellStyle);

	}
}
