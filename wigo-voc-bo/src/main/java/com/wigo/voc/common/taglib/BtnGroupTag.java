package com.wigo.voc.common.taglib;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.model.CrmMenuVo;

/**
 * 
* <pre>
* com.wigo.crm.common.taglib
*	- GrdBtnTag.java
* </pre>
*
* @ClassName	: GrdBtnTag 
* @Description	: TODO 
* @author 		: MKH
* @date 		: 2021. 1. 18.
* @Version 		: 1.0 
* @Company 		: Copyright ⓒ wigo.ai. All Right Reserved
 */

public class BtnGroupTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5810949506019349503L;

	
	private String  name;
	private boolean forceInsert = false;
	private boolean hideInsert = false;
	private boolean forceSave = false;
	private boolean hideSave = false;
	private boolean forceDelete = false;
	private boolean hideDelete = false;
	private boolean hasCancel = false;
	private boolean hasSort = false;
	private String gridId = "";
	private boolean hideExcel = false;
	private String dispName = "";
	
	private String insertName ="추가";
	private String saveName ="저장";
	private String deleteName ="삭제";
	private String excelName ="엑셀";
	private String cancelName ="취소";
	private String seqName ="순서변경";
	
	@Override
    public int doStartTag() throws JspException {
    	
		ServletRequest req = pageContext.getRequest();
		CrmMenuVo menu = (CrmMenuVo)req.getAttribute("currentMenu");
		if(menu != null) {
			StringBuffer html = new StringBuffer();
			String gridIdHtml = "";
			if(Utilities.isNotEmpty(gridId)) {
				gridIdHtml = " data-grid-id=\""+gridId+"\"  data-disp-name=\""+dispName+"\"";
			}
			if(hasSort&&(forceSave || "Y".equals(menu.getMenuAmdAuthYn())))
//				html.append(" <a type=\"button\" "+gridIdHtml+" data-click=\"sort"+name+"\" id=\"btnSort"+name+"\" class=\"btn_whites\">순서변경</a>");
				//<a href="#" class="mBtn1 m lWhite">수정</a>
				html.append(" <a href=\"#\"  "+gridIdHtml+" data-click=\"sort"+name+"\" id=\"btnSort"+name+"\" class=\"mBtn1 m lWhite\">"+seqName+"</a>");
//				html.append(" <button type=\"button\" "+gridIdHtml+" data-click=\"sort"+name+"\" id=\"btnSort"+name+"\" class=\"btn_whites\">순서변경</button>");
			if(hasCancel)
				html.append(" <a href=\"#\" "+gridIdHtml+" data-click=\"cancel"+name+"\" id=\"btnCancel"+name+"\" class=\"mBtn1 m lWhite\">"+cancelName+"</a>");
			if(!hideExcel && Utilities.isNotEmpty(gridId))  
				html.append(" <a href=\"#\" "+gridIdHtml+" data-name=\""+name+"\" data-click=\"excelDownload\" id=\"btnExcel"+name+"\" class=\"mBtn1 m lWhite\">"+excelName+"</a>");
			if(!hideDelete&&( forceDelete || "Y".equals(menu.getMenuDelAuthYn())))
				html.append(" <a href=\"#\" "+gridIdHtml+" data-click=\"remove"+name+"\" id=\"btnRemove"+name+"\" class=\"mBtn1 m\">"+deleteName+"</a>");
			if(!hideInsert&&(forceInsert || "Y".equals(menu.getMenuRegAuthYn())))
				html.append(" <a href=\"#\" "+gridIdHtml+" data-click=\"new"+name+"\" id=\"btnNew"+name+"\" class=\"mBtn1 m lWhite\">"+insertName+"</a>");
			if(!hideSave&&(forceSave || "Y".equals(menu.getMenuAmdAuthYn())))
				html.append(" <a href=\"#\" "+gridIdHtml+" data-click=\"save"+name+"\" id=\"btnSave"+name+"\" class=\"mBtn1 m lWhite\">"+saveName+"</a>");
			try {
				pageContext.getOut().print(html.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    
    	return SKIP_BODY;
    }

	/**
	 * @return the forceInsert
	 */
	public boolean isForceInsert() {
		return forceInsert;
	}

	/**
	 * @param forceInsert the forceInsert to set
	 */
	public void setForceInsert(boolean forceInsert) {
		this.forceInsert = forceInsert;
	}

	/**
	 * @return the forceSave
	 */
	public boolean isForceSave() {
		return forceSave;
	}

	/**
	 * @param forceSave the forceSave to set
	 */
	public void setForceSave(boolean forceSave) {
		this.forceSave = forceSave;
	}

	/**
	 * @return the forceDelete
	 */
	public boolean isForceDelete() {
		return forceDelete;
	}

	/**
	 * @param forceDelete the forceDelete to set
	 */
	public void setForceDelete(boolean forceDelete) {
		this.forceDelete = forceDelete;
	}

	/**
	 * @return the hasCancel
	 */
	public boolean isHasCancel() {
		return hasCancel;
	}

	/**
	 * @param hasCancel the hasCancel to set
	 */
	public void setHasCancel(boolean hasCancel) {
		this.hasCancel = hasCancel;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hideInsert
	 */
	public boolean isHideInsert() {
		return hideInsert;
	}

	/**
	 * @param hideInsert the hideInsert to set
	 */
	public void setHideInsert(boolean hideInsert) {
		this.hideInsert = hideInsert;
	}

	/**
	 * @return the hideSave
	 */
	public boolean isHideSave() {
		return hideSave;
	}

	/**
	 * @param hideSave the hideSave to set
	 */
	public void setHideSave(boolean hideSave) {
		this.hideSave = hideSave;
	}

	/**
	 * @return the hideDelete
	 */
	public boolean isHideDelete() {
		return hideDelete;
	}

	/**
	 * @param hideDelete the hideDelete to set
	 */
	public void setHideDelete(boolean hideDelete) {
		this.hideDelete = hideDelete;
	}

	/**
	 * @return the gridId
	 */
	public String getGridId() {
		return gridId;
	}

	/**
	 * @param gridId the gridId to set
	 */
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	/**
	 * @return the hideExcel
	 */
	public boolean isHideExcel() {
		return hideExcel;
	}

	/**
	 * @param hideExcel the hideExcel to set
	 */
	public void setHideExcel(boolean hideExcel) {
		this.hideExcel = hideExcel;
	}

	public boolean isHasSort() {
		return hasSort;
	}

	public void setHasSort(boolean hasSort) {
		this.hasSort = hasSort;
	}

	/**
	 * @return the dispName
	 */
	public String getDispName() {
		return dispName;
	}

	/**
	 * @param dispName the dispName to set
	 */
	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	public String getInsertName() {
		return insertName;
	}

	public void setInsertName(String insertName) {
		this.insertName = insertName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getDeleteName() {
		return deleteName;
	}

	public void setDeleteName(String deleteName) {
		this.deleteName = deleteName;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getCancelName() {
		return cancelName;
	}

	public void setCancelName(String cancelName) {
		this.cancelName = cancelName;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	
	
}
