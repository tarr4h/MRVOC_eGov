package com.wigo.voc.sys.dao;

import java.util.List;

import com.wigo.voc.sys.mapper.CrmMapper;
import com.wigo.voc.sys.model.CrmComnCdBaseVo;

@CrmMapper
public interface CrmComnCdBaseDao extends ICrmDao {

	int deleteChildren(Object param) throws Exception;
	List<CrmComnCdBaseVo> selectCodeCombo(Object param) throws Exception;
}
