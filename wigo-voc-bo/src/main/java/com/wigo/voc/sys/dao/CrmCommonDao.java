package com.wigo.voc.sys.dao;

import com.wigo.voc.sys.mapper.CrmMapper;

@CrmMapper
public interface CrmCommonDao extends ICrmDao {

	public String getAutoSeq(Object param);
}
