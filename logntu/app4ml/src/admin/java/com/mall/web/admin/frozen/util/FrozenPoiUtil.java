package com.mall.web.admin.frozen.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.mall.web.admin.frozen.vo.AdminFrozenVo;

public class FrozenPoiUtil {
	public static void Frozen2Row(AdminFrozenVo frozen, XSSFRow row,
			CellStyle cellStyle) {
		XSSFCell cell = row.createCell(0);
		cell.setCellValue(frozen.getFrozen_id());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue(frozen.getUser_id());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue(frozen.getFrozen() == null ? "" : frozen.getFrozen()
				.toString());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue(frozen.getFrozen_time());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue(frozen.getStatus() == 1 ? "冻结"
				: frozen.getStatus() == 2 ? "解冻" : "");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue(frozen.getRemark());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue(frozen.getBank_reg_name());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue(frozen.getBank_card_no());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue(frozen.getCard_owner());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue(frozen.getBank_reg_pri());
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue(frozen.getBank_reg_sub());
		cell.setCellStyle(cellStyle);
	}
}
