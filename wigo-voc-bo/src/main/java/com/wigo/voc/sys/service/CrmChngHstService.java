package com.wigo.voc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wigo.voc.sys.dao.CrmChngHstDao;
import com.wigo.voc.sys.dao.ICrmDao;

@Service
public class CrmChngHstService extends AbstractCrmService {
	@Autowired
	CrmChngHstDao dao;

	@Override
	public ICrmDao getDao() {
		return dao;
	}
}
