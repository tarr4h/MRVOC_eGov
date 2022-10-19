package com.wigo.voc.sys.dao;

import com.wigo.voc.sys.mapper.CrmMapper;

@CrmMapper
public interface CrmGrpMenuRelDao extends ICrmDao {

	int deleteMenuCd(Object param) throws Exception;

	void deleteGrpCd(Object param) throws Exception;

}
