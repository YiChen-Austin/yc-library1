package com.mall.web.mall.common.vo;

import java.util.List;

/**
 * json数据
 * 
 * @author ventrue
 * 
 */
public class FindBeans2Hz {
	private String blockName;
	private List<FindBean2Hz> block;

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public List<FindBean2Hz> getBlock() {
		return block;
	}

	public void setBlock(List<FindBean2Hz> block) {
		this.block = block;
	}
}
