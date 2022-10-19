package com.wigo.voc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wigo.voc.sys.dao.CrmNtcartBasDao;
import com.wigo.voc.sys.dao.ICrmDao;

@Service
public class CrmNtcartBasService extends AbstractCrmService {
   @Autowired
   CrmNtcartBasDao dao;

   @Override
   public ICrmDao getDao() {
       return dao;
   }
}
