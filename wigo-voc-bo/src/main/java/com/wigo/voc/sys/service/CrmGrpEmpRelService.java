package com.wigo.voc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wigo.voc.sys.dao.CrmGrpEmpHstDao;
import com.wigo.voc.sys.dao.CrmGrpEmpRelDao;
import com.wigo.voc.sys.dao.ICrmDao;

@Service
public class CrmGrpEmpRelService extends AbstractCrmService {
	@Autowired
	CrmGrpEmpRelDao dao;
	@Autowired
	CrmGrpEmpHstDao hstDao;

	@Override
	public ICrmDao getDao() {
		return dao;
	}

	@Override
	public int delete(Object param) throws Exception {
		hstDao.insertDelete(param);
		return super.delete(param);
	}

	@Override
	public int insert(Object param) throws Exception {
		if (get(param) == null) {
			hstDao.insert(param);
			return super.insert(param);
		}
		return 0;
	}
}
