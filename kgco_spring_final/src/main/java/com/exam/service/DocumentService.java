package com.exam.service;

import java.util.List;

import com.exam.domain.DocumentVO;

public interface DocumentService {

    String getDocumentType(String doctype);

    String getDocumentCnt(String string);

    void setDocument(DocumentVO documentVO);

	int setDocumentLev(String type);

	List<DocumentVO> getConfirmDocuments(String authLev, String selectStr);

	DocumentVO getdocument(String no);

	void setDocumentCancel(String no,  String user);

	void setDocumentConfirm(String no, String user);

	void setDocumentConfirmExaggeration(String no, String user);

	void setDocumentConfirmDeputy(String no, String user);

	List<DocumentVO> getFinishDocuments(String eid, String checkStr);
}
