package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.DocumentVO;

public interface DocumentMapper {

    public String getDocumentType(String doctype);

    public int getDocumentCnt(String string);

    public void setDocument(DocumentVO documentVO);

	public int setDocumentLev(String type);

	public List<DocumentVO> getConfirmDocuments(@Param("authLev") String authLev,@Param("selectStr") String selectStr);

	public DocumentVO getdocument(String no);

	public void setDocumentCancel(@Param("no")String no,@Param("user") String user);

	public void setDocumentConfirm(@Param("no")String no,@Param("user") String user);

	public void setDocumentConfirmExaggeration(@Param("no")String no,@Param("user") String user);

	public void setDocumentConfirmDeputy(@Param("no")String no, @Param("user")String user);

	public List<DocumentVO> getFinishDocuments(@Param("eid")String eid, @Param("checkStr")String checkStr);
}
