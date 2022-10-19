package com.wigo.voc.sys.dao;

import java.util.List;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.sys.mapper.CrmMapper;
import com.wigo.voc.sys.model.CrmMenuVo;

@CrmMapper
public interface CrmMenuBaseDao extends ICrmDao {

	CrmMenuVo selectMaxInfo(Object so) throws Exception;

	List<CrmMenuVo> selectTreeList(EzMap param) throws Exception;

	int updateSeq(Object param);

	List<CrmMenuVo> selectUserMenuList(Object param) throws Exception;

	int selectChildrenCount(Object param) throws Exception;
	
}
