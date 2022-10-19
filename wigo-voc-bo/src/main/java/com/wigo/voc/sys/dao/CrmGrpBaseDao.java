package com.wigo.voc.sys.dao;

import java.util.List;

import com.wigo.voc.common.model.EzMap;
import com.wigo.voc.sys.mapper.CrmMapper;
import com.wigo.voc.sys.model.CrmGrpBaseVo;
import com.wigo.voc.sys.model.CrmGrpMenuRelVo;
import com.wigo.voc.sys.model.CrmGrpUserRelVo;

@CrmMapper
public interface CrmGrpBaseDao extends ICrmDao {

	List<CrmGrpUserRelVo> selectGroupUserList(Object param) throws Exception;

	int selectGroupUserListCount(Object param) throws Exception;
	
	List<CrmGrpMenuRelVo> selectGroupMenuList(Object param) throws Exception;

	int selectGroupMenuListCount(Object param) throws Exception;

	List<CrmGrpBaseVo> selectGroupCheckList(EzMap param) throws Exception;

}
