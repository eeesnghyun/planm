package com.app.planm.document.vo;

import com.app.planm.user.vo.UserVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DocumentVO extends UserVO {

	private String docNo;
	private String docType;
	private String docStatus;
	private String requestYmd;
	private String signYmd;
	private String returnCause;
	
}
