package com.wigo.voc.sys.dao;

import com.wigo.voc.sys.mapper.CrmMapper;

import java.util.List;

@CrmMapper
public interface IBaseDao {
	<T> T select(Object param);
	<T> List<T> selectList(Object param);
	int selectListCount(Object param);
	int insert(Object param);
	int update(Object param);
	int delete(Object param);
}
