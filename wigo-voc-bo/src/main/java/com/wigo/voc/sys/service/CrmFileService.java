package com.wigo.voc.sys.service;

import java.io.File;
import java.util.List;

import javax.xml.ws.http.HTTPException;

import com.wigo.voc.common.model.EzMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wigo.voc.common.util.Utilities;
import com.wigo.voc.sys.dao.CrmFileBaseDao;
import com.wigo.voc.sys.dao.ICrmDao;
import com.wigo.voc.sys.model.CrmFileVo;

@Service
public class CrmFileService extends AbstractCrmService {
	@Autowired
	CrmFileBaseDao dao;

	@Value("${spring.nas.path}")
	private String nasPath;


	@Value("${spring.nas.url}")
	private String nasUrl;



	@Override
	public ICrmDao getDao() {
		return dao;
	}

	@Override
	public int delete(Object param) throws Exception {
		CrmFileVo fileInfo = get(param);
		if (fileInfo == null)
			return 0;
		String saveFileName = getSaveFileName( fileInfo.getFileSaveNm());
		Utilities.deleteFile(new File(saveFileName));
		int ret = super.delete(param);
		String fileCd = fileInfo.getFileCd();
		EzMap so = new EzMap();
		so.setString("fileCd", fileCd);
		int cnt = getListCount(so);
		if(cnt == 0) {
			String dir = Utilities.getFilePath( saveFileName);
			Utilities.deleteDirectory(dir);
		}
		return ret;
	}
	private String getSaveFileName(String saveName) {
		return nasPath + saveName;
	}
	private String getFileUrlName(String fileName) {
		return nasUrl + fileName;
	}
	private void saveUploadFile(MultipartFile uploadfile, String fileName) throws Exception {
		Utilities.createDirectory(Utilities.getFilePath(fileName));
		uploadfile.transferTo(new File(fileName));
	}

	public CrmFileVo uploadFile(MultipartFile uploadfile, CrmFileVo fileInfo) throws Exception {
		if (uploadfile == null)
			throw new Exception();

		if (Utilities.isEmpty(fileInfo.getFileNm())) {
			String rFileName = uploadfile.getOriginalFilename();
			fileInfo.setFileNm(rFileName);
			fileInfo.setFileExtNm(".txt");
			fileInfo.setFileSize(uploadfile.getSize());
			fileInfo.setMimeTypeNm(uploadfile.getContentType());
			;
		}

		String ctgryCd = fileInfo.getFileCtgryCd();
		String subUrl = fileInfo.getFileCd() + "/";
		String fileName = fileInfo.getFileNm();

		// ?????????
		fileName = Utilities.getUniqID(20) + "." + Utilities.getFileExtension(fileName);

		if (Utilities.isEmpty(ctgryCd))
			ctgryCd = "attachment";
		if (ctgryCd.startsWith("/"))
			ctgryCd = ctgryCd.substring(1);
		if (ctgryCd.endsWith("/"))
			ctgryCd = ctgryCd.substring(0, ctgryCd.length() - 1);

		String fileCd = fileInfo.getFileCd();

		subUrl = ctgryCd + "/" + fileCd + "/";
		String subFileName = subUrl + fileName;
		fileInfo.setFileUrl( getFileUrlName(subFileName));
		fileInfo.setFileSaveNm( subFileName);
		String saveFileName =getSaveFileName( subFileName) ;
		saveUploadFile(uploadfile, saveFileName);

		Integer fileSeq = fileInfo.getFileOdrg();
		if (fileSeq !=null && fileSeq > 0) {
			delete(fileInfo);
		}
		insert(fileInfo);
		return fileInfo;
	}

	public Object downloadFile(CrmFileVo param) throws Exception {
		CrmFileVo fileInfo = get(param);
		if (fileInfo == null)
			throw new HTTPException(404);
		String saveName =getSaveFileName(fileInfo.getFileSaveNm());
		String fileNm = fileInfo.getFileNm();
		Utilities.DownloadFile(saveName, fileNm);
		return true;

	}

	public void deleteFileCd(String fileCd) throws Exception {
		if (Utilities.isEmpty(fileCd))
			return;
		EzMap fileSo = new EzMap();
		fileSo.setString("fileCd", fileCd);
		List<CrmFileVo> fileList = dao.selectList(fileSo);
		deleteList(fileList);

	}

	public int getMaxFileSeq(String fileCd) throws Exception {
		EzMap param = new EzMap();
		param.setString("fileCd", fileCd);
		return dao.selectMaxFileSeq(param);
	}

}
