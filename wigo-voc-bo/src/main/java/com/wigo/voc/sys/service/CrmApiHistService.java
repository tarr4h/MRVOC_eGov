package com.wigo.voc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wigo.voc.sys.dao.CrmApiExecHstDao;
import com.wigo.voc.sys.dao.ICrmDao;

@Service
public class CrmApiHistService extends AbstractCrmService {
	@Autowired
	CrmApiExecHstDao dao;

	@Override
	public ICrmDao getDao() {
		return dao;
	}

	
}
