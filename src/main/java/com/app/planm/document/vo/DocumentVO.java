package com.app.planm.document.vo;

import com.app.planm.user.vo.UserVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DocumentVO extends UserVO {

	private String docNo;
	private String docType;
	private String docTypeNm;
	private String docStatus;
	private String docStatusNm;
	private String requestYmd;
	private String signYmd;	
	private String returnCause;
	
	private String leaveType;
	private String dayType;
	private String leaveDay;
	private String startDay;
	private String endDay;
	private String remark;
	
	private int createDay;
	private int useDay;
	private int RemainDay;
	private int leaveCnt;
	
	private String signUser;
	private String signName;	
	private int signSeq;
	private int lastSeq;
	
}
