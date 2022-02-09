package com.app.planm.document.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.app.planm.document.vo.DocumentDTO;

@Mapper
public interface DocumentDao {

	public List<DocumentDTO> getDocumentList() throws Exception;
}
