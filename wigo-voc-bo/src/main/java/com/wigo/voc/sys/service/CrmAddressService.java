package com.wigo.voc.sys.service;

import com.wigo.voc.common.model.address.EzAddressSo;
import com.wigo.voc.common.model.address.EzAdressResultVo;
import org.springframework.stereotype.Service;

import com.wigo.voc.common.util.AddressUtil;

@Service
public class CrmAddressService {

	public EzAdressResultVo getList(EzAddressSo so) throws Exception {
		return AddressUtil.searchAddr(so.getKeyword(), so.getCurrentPage(), so.getCountPerPage(), so.getLanguage());
	}

}
