package com.wigo.voc.sys.dao;

import com.wigo.voc.sys.mapper.CrmMapper;

@CrmMapper
public interface CrmGrpUserRelDao extends ICrmDao {

	void deleteUserCd(Object param) throws Exception;

	void deleteGrpCd(Object param) throws Exception;

}
