package com.app.planm.document.vo;

import lombok.Data;

@Data
public class DocumentVO {

	private String docNo;
	private String userCode;
	private String docType;
	private String docStatus;
	private String requestYmd;
	private String signYmd;
	private String returnCause;
	
}
