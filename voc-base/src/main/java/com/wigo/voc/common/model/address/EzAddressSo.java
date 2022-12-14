package com.wigo.voc.common.model.address;

import com.wigo.voc.common.model.EzPaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EzAddressSo {
	public EzAddressSo() {
		
	}
	public EzAddressSo(EzPaginationInfo page) {
		currentPage = page.getCurrentPageNo();
		countPerPage = page.getRecordCountPerPage();
	}
	String keyword;
	String language;
	private int currentPage;
	private int countPerPage;
}
