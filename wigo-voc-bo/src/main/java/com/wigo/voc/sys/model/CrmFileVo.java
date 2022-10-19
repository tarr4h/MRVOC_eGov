package com.wigo.voc.sys.model;

import com.wigo.voc.common.util.Utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrmFileVo extends CrmFileBaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileCtgryCd;

	public String getFileSizeText() {
		return Utilities.getSizeString(getFileSize()) + "B";
	};
}
