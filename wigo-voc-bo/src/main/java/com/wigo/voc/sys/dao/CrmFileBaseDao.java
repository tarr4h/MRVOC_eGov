package com.wigo.voc.sys.dao;

import com.wigo.voc.sys.mapper.CrmMapper;

@CrmMapper
public interface CrmFileBaseDao extends ICrmDao {

	int selectMaxFileSeq(Object param) throws Exception;

}
