package com.wigo.voc.sys.dao;

import com.wigo.voc.sys.mapper.CrmMapper;

@CrmMapper
public interface CrmGrpEmpRelDao extends ICrmDao {
	void deleteGrpCd(Object param) throws Exception;
}
