package com.app.planm.approval.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter 
//@Builder 
//@NoArgsConstructor
//@Entity(name="PmDoc")
public class PmDoc {
	
	@Id	
	private String cmpcd;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long docno;
	
	@Column(nullable = false, length = 20)
	private String doctype;
	
	@Column(nullable = false, length = 20)
	private String usercd;
	
	@Column(nullable = false, length = 20)
	private String requestymd;
	
	@Column(nullable = false, length = 20)
	private String docstatus;
	
	@Column(nullable = false, length = 20)
	private String signymd;
	
	@Column(nullable = false, length = 20)
	private String returncause;
	
	public PmDoc(
			String cmpcd,
			long docno,
			String doctype, 
			String usercd, 
			String requestymd,
			String docstatus,
			String signymd,
			String returncause) {
		this.cmpcd  = cmpcd;
		this.docno = docno;
		this.doctype = doctype;
		this.usercd = usercd;
		this.requestymd = requestymd;
		this.docstatus = docstatus;
		this.signymd = signymd;
		this.returncause = returncause;		
	}
	
	
}
