package com.shop.message;

public class RequestMsg   {
	/** 开始索引,从1开始 */
	private int curPage = 1;

	/** 最大记录数 */
	private int pageSize = 1;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
        this.pageSize = pageSize > 100 ? 100 :pageSize;
	}

}
