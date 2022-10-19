package com.wigo.voc.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.dao.CrmXlsDnldHstDao;
import com.wigo.voc.sys.dao.ICrmDao;
import com.wigo.voc.sys.model.CrmXlsDnldHstVo;

@Service
public class CrmXlsDnldHstService extends AbstractCrmService {
   @Autowired
   CrmXlsDnldHstDao dao;

   @Override
   public ICrmDao getDao() {
       return dao;
   }

public void addLog(CrmXlsDnldHstVo vo) {
	String menuCd = Utilities.getCurrentMenuCd();
	if(Utilities.isEmpty(menuCd))
		return ;
	vo.setMenuCd(menuCd);
	dao.insert(vo);
}
}
