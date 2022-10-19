package com.wigo.voc.example.service;

import com.wigo.voc.example.dao.ExampleDao;
import com.wigo.voc.sys.dao.ICrmDao;
import com.wigo.voc.sys.service.AbstractCrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService extends AbstractCrmService {
	@Autowired
    ExampleDao dao;

	@Override
	public ICrmDao getDao() {
		return dao;
	}
	
	
}
