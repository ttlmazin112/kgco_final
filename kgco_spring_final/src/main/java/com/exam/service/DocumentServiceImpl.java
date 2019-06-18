package com.exam.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.domain.DocumentVO;
import com.exam.mapper.DocumentMapper;

import lombok.Setter;

@Service("DocumentService")
public class DocumentServiceImpl implements DocumentService {
	@Setter(onMethod_ = @Autowired)
	private DocumentMapper mapper;

    @Override
    public String getDocumentType(String doctype) {
        // TODO Auto-generated method stub
        return mapper.getDocumentType(doctype);
    }

    @Override
    public String getDocumentCnt(String string) {
        // TODO Auto-generated method stub
        int result = mapper.getDocumentCnt(string) + 1;    
        return Integer.toString(result);
    }

    @Override
    public void setDocument(DocumentVO documentVO) {
        // TODO Auto-generated method stub
        mapper.setDocument(documentVO);
    }

	@Override
	public int setDocumentLev(String type) {
		// TODO Auto-generated method stub
		return mapper.setDocumentLev(type);
	}

	@Override
	public List<DocumentVO> getConfirmDocuments(String authLev, String selectStr) {
		// TODO Auto-generated method stub
		return mapper.getConfirmDocuments(authLev, selectStr);
	}

	@Override
	public DocumentVO getdocument(String no) {
		// TODO Auto-generated method stub
		return mapper.getdocument(no);
	}

	@Override
	public void setDocumentCancel(String no, String user) {
		mapper.setDocumentCancel(no, user);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentConfirm(String no, String user) {
		// TODO Auto-generated method stub
		mapper.setDocumentConfirm(no, user);
		
	}

	@Override
	public void setDocumentConfirmExaggeration(String no, String user) {
		// TODO Auto-generated method stub
		mapper.setDocumentConfirmExaggeration(no, user);
		
	}

	@Override
	public void setDocumentConfirmDeputy(String no, String user) {
		// TODO Auto-generated method stub
		mapper.setDocumentConfirmDeputy(no, user);
	}

	@Override
	public List<DocumentVO> getFinishDocuments(String eid, String checkStr) {
		// TODO Auto-generated method stub
		return mapper.getFinishDocuments(eid, checkStr);
	}


	

    



}
