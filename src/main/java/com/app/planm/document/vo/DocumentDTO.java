package com.app.planm.document.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDTO {

	private int docno;
	private String doctype;
	private String usercd;
	private String requestymd;
	private String docstatus;
	private String signymd;
	private String returncause;
	
	
}
