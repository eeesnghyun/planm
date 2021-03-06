//주소 API 가져오기
const findAddr = function() {
	new daum.Postcode({
        oncomplete: function(data) {		        	
            var roadAddr = data.roadAddress; 	//도로명 주소
            var jibunAddr = data.jibunAddress;  //지번 주소
            
            $("#zipcode").val(data.zonecode);	//우편번호
            
            if (roadAddr !== "") {
            	$("#address").val(roadAddr);
            } else if(jibunAddr !== "") {
            	$("#address").val(jibunAddr);            
            }
        }
    }).open();
};

//회사 정보 유효성 체크
const isValid = function() {
	
	return true;
};

//회사 정보 저장
const saveCmp = function() {
	if (isValid()) {
		const frm = $("form");				
		const email = $("#email").val().replace(/\@/g,'') + "@" + $("#emailServer").val();
		
		const input = $("<input>")
			.attr("type", "hidden") 
			.attr("name", "email")
			.val(email);
		const logo = $("<input>")
			.attr("type", "hidden") 
			.attr("name", "cmpLogo")
			.val($("#imgSource").val());
		
		frm.append($(input));
		frm.append($(logo));
		frm.submit();
	}						
};

//부서,파트관리 : 부서 그리드 생성
const initGridDept = function() {
	const data = commonCallAjax("/admin/getDeptList", "");
	
	if (data.code == "ok") {
		const result = data.resultList;

		if (commonIsNull(gridDept)) {
			//Grid draw
			gridDept = new Tabulator("#gridDept", {
				layout: "fitDataTable",
				placeholder: "데이터가 존재하지 않습니다.",
				height: "220px",	    
			    columns: [
			    	{title: "부서코드" , field: "deptCode"    , width: 100},
			    	{title: "부서명"   , field: "deptName"    , width: 100},
			    	{title: "부서장코드", field: "deptManager" , width: 100, visible: false},
			    	{title: "부서장"   , field: "managerName" , width: 100},
				    {title: "생성일"   , field: "createDate"  , width: 120, hozAlign: "center"}						    						   
			    ]			  
			});		        	

			//Data set
			gridDept.on("tableBuilt", function(){				
				gridDept.setData(result);
				gridDept.on("rowClick", function(e, row) {		
					gridDept.deselectRow();
					row.toggleSelect();
			    	//e - click object
			        //row - row component
			        const rowData = row._row.data;
			    	
			        $("#deptCode").prop("disabled", true);
			        $("#deptCode").val(rowData.deptCode);
					$("#deptName").val(rowData.deptName);
					$("#deptManager").val(rowData.managerName);
					$("#deptManagerCode").val(rowData.deptManager);
					$("#saveBtnDept").hide();
					$("#updateBtnDept").show();
					
			    	initGridPart(rowData.deptCode);
				});
			});	
		} else {
			gridDept.replaceData(result);
		}    				
	}					    	        
};

//부서,파트관리 : 파트 그리드 생성
const initGridPart = function(deptCode) {
	const data = commonCallAjax("/admin/getPartList", {"deptCode" : deptCode});
	
	if (data.code == "ok") {
		const result = data.resultList;
		
		if (commonIsNull(gridPart)) {
			let nullMsg = "";				
			if (commonIsNull(deptCode)) {
				nullMsg = "부서를 선택해주세요.";
			} else {
				nullMsg = "데이터가 존재하지 않습니다.";
			}
			
	    	//Grid draw
			gridPart = new Tabulator("#gridPart", {
				layout: "fitDataTable",
				placeholder: nullMsg,
				height: "220px",	    
			    columns: [
			    	{title: "파트코드"  , field: "partCode"    , width: 100},
			    	{title: "파트명"    , field: "partName"    , width: 100},
			    	{title: "파트장코드", field: "partManager" , width: 100, visible: false},
			    	{title: "파트장"    , field: "managerName" , width: 100},				    	
				    {title: "생성일"    , field: "createDate"  , width: 120, hozAlign: "center"}						    						   
			    ]			  
			});		        	

			//Data set
			gridPart.on("tableBuilt", function(){				
				gridPart.setData(result);
				gridPart.on("rowClick", function(e, row) {		
					gridPart.deselectRow();
					row.toggleSelect();
			    	//e - click object
			        //row - row component
			        const rowData = row._row.data;
			    	
			        $("#partCode").val(rowData.partCode);
					$("#partName").val(rowData.partName);
					$("#partManager").val(rowData.managerName);
					$("#partManagerCode").val(rowData.partManager);
					$("#saveBtnPart").hide();
					$("#updateBtnPart").show();
				});
			});		
		} else {
			gridPart.replaceData(result);
		}						
	}	
};

//직급관리 : 직급 그리드 생성
const initGridPos = function() {
	const params = {
		"parentCode" : posCategory,
		"show" : "all" 
	};
	const data = commonCallAjax("/cmn/getCodeList", params);
	
	if (data.code == "ok") {
		const result = data.resultList;
		
		if (commonIsNull(gridPos)) {
			//Grid draw
			gridPos = new Tabulator("#gridPos", {
				layout: "fitDataTable",
				placeholder: "데이터가 존재하지 않습니다.",
				height: "250px",	    
			    columns: [
			    	{title: "직급코드" , field: "code"  , width: 100},
			    	{title: "직급명"   , field: "name"  , width: 120},					    						    						    						  
			    	{title: "보이기"   , field: "useYn" , width: 100, hozAlign: "center"}
			    ]			  
			});		        	

			//Data set
			gridPos.on("tableBuilt", function(){				
				gridPos.setData(result);						
			});	
		} else {
			gridPos.replaceData(result);
		}    	
	}					    	        
};

//전자결재관리 : 결재유저 그리드 생성
const initGridSignUser = function() {
	if (commonIsNull($("#signDept").val())) {
		alert("부서를 선택해주세요.");
		return;
	}
	
	if (commonIsNull($("#signPart").val())) {
		alert("파트를 선택해주세요.");
		return;
	}
	
	const params = {
		"docType"  : $("#document").val(),
		"deptCode" : $("#signDept").val(),
		"partCode" : $("#signPart").val()
	};	
	const data = commonCallAjax("/admin/getUserList", params);
	
	if (data.code == "ok") {
		const autoSign = data.autoSign;
		const result = data.resultList;

		if (autoSign == "Y") {
			$("input:checkbox[id='signPicker']").prop("checked", true);	
		} else {
			$("input:checkbox[id='signPicker']").prop("checked", false);
		}
		
		if (commonIsNull(gridSignUser)) {
			//Grid draw
			gridSignUser = new Tabulator("#gridSignUser", {
				layout: "fitDataTable",
				placeholder: "데이터가 존재하지 않습니다.",
				height: "220px",	    
			    columns: [
			    	{formatter: "rowSelection", 
			    	 titleFormatter: "rowSelection", 
			    	 hozAlign: "center", 
			    	 headerSort: false, 
			    	 cellClick: function(e, cell) {		    	 	 
			    	 	cell.getRow().toggleSelect();
			    	}},
			    	{title: "사번"     , field: "userCode"  , width: 100},
			    	{title: "이름"     , field: "userName"  , width: 120},
			    	{title: "직급"     , field: "jobPosName", width: 100},		    					    
			    	{title: "결재권한" , field: "signauth"  , hozAlign:"center", formatter:"tickCross", width: 100}
			    ]
			});		        	

			//Data set
			gridSignUser.on("tableBuilt", function(){				
				gridSignUser.setData(result);
			});	
		} else {
			gridSignUser.replaceData(result);	
		}		
		
		getSignUser(autoSign);
	}			
}			

//부서,파트 관리 : 파트,부서장 조회
const showUser = function(task) {
	const dept = $("#deptCode").val();
	let data = null;
	
	if (task == "dept") {				
		if (commonIsNull(dept)) {
			alert("부서를 선택해주세요.");
			return;
		}	
		
		$(".layer-pop").css("left", "0");
		$("#layerTitle").text("부서장 선택");
		$("#layerBtn").on("click", function(){
			setDeptUser();
		});
		
		data = commonCallAjax("/user/getDeptUser", {"deptCode" : $("#deptCode").val()});
	} else {				
		const part = $("#partCode").val();
		
		if (commonIsNull(part)) {
			alert("파트를 선택해주세요.");
			return;
		}
		
		$(".layer-pop").css("left", "50%");
		$("#layerTitle").text("파트장 선택");
		$("#layerBtn").on("click", function(){
			setPartUser();
		});
		
		const params = {
			"deptCode" : dept,
			"partCode" : part
		};
		data = commonCallAjax("/user/getPartUser", params);
	}												
	
	if (data.code == "ok") {
		const result = data.resultList;

		$(".layer-pop").show();
		
		if (commonIsNull(gridUser)) {
			//Grid draw
			gridUser = new Tabulator("#gridUser", {
				layout: "fitDataTable",
				placeholder: "데이터가 존재하지 않습니다.",
				height: "220px",	    
			    columns: [
			    	{title: "사번" , field: "userCode" , width: 100},
			    	{title: "이름" , field: "userName" , width: 120},
			    	{title: "직급" , field: "jobPos"   , width: 100}						    						    						  
			    ]			  
			});		        	

			//Data set
			gridUser.on("tableBuilt", function(){				
				gridUser.setData(result);
				gridUser.on("rowClick", function(e, row) {		
					gridPart.deselectRow();
					row.toggleSelect();
			    	//e - click object
			        //row - row component
			        const rowData = row._row.data;
			    	
			        userCode = rowData.userCode;
					userName = rowData.userName;
				});
			});	
		} else {
			gridUser.replaceData(result);
		}
	}					    	        
};

const setDeptUser = function() {
	$("#deptManagerCode").val(userCode);
	$("#deptManager").val(userName);
	$(".layer-pop").hide();
};

const setPartUser = function() {
	$("#partManagerCode").val(userCode);
	$("#partManager").val(userName);
	$(".layer-pop").hide();
};

const closeLayer = function() {
	gridUser = null;
	userCode = "";
	userName = "";
	
	$(".layer-pop").hide();			
};

const readCmpLogo = function(input) {			
	$("#fileName").val(input.files[0].name);
	
	if (input.files && input.files[0]) {
		const file = input.files[0];
		
		if (file.size > 300000) {
            alert("파일 사이즈가 너무 큽니다.")
            return;
        }
		
		var fileReader = new FileReader();
		
		fileReader.onload = function(e) {
			$("#cmpLogo").attr("src", e.target.result);
			$("#imgSource").val(e.target.result);
		};       
		fileReader.readAsDataURL(file);
	}
};

const newDept = function() {
	$("#deptCode").prop("disabled", false);
	$("#saveBtnDept").show();
	$("#updateBtnDept").hide();
};

//부서,파트 관리 : 부서 저장
const saveDept = function() {
	
};

//부서,파트 관리 : 부서 수정
const updateDept = function() {
	const params = {
		"deptCode" : $("#deptCode").val(),
		"deptName" : $("#deptName").val(),
		"deptManager" : $("#deptManagerCode").val()
	};
	const data = commonCallAjax("/admin/updateDept", params);
	
	if (data.code == "ok") {
		alert("저장되었습니다.");
		
		initGridDept();
	}
};

//부서,파트 관리 : 파트 저장
const savePart = function() {
	
};

//부서,파트 관리 : 파트 수정
const updatePart = function() {
	const params = {
		"deptCode" : $("#deptCode").val(),
		"partCode" : $("#partCode").val(),
		"partName" : $("#partName").val(),
		"partManager" : $("#partManagerCode").val()
	};
	const data = commonCallAjax("/admin/updatePart", params);
	
	if (data.code == "ok") {
		alert("저장되었습니다.");
		
		initGridPart(data.deptCode);
	}
};

//직급관리 : 직급유형 변경
const updatePos = function() {
	const data = commonCallAjax("/admin/updatePos", {"posCategory" : $("#posCategory").val()});
	
	if (data.code == "ok") {
		alert("저장되었습니다.");
		
		posCategory = data.posCategory;
		initGridPos();
	}
};

//결재권한 등록
const saveSignUser = function() {
	const params = {
		"docType"  : $("#document").val(),
		"deptCode" : $("#signDept").val(),
		"partCode" : $("#signPart").val(),
		"userCode" : $("#signManagerCode").val(),
		"data" : JSON.stringify(gridSignUser.getSelectedData())
	};			
	
	const data = commonCallAjax("/admin/saveSignUser", params);
	
	if (data.code == "ok") {
		alert("저장되었습니다.");
		
		initGridSignUser();
	}
};

const initObj = function() {								
	const comboObj = {"objId" : "posCategory", "params" : {"parentCode" : "POS003"}, "defaultSelected" : "blank"};			
	const comboObj2 = {"objId" : "emailServer", "params" : {"parentCode" : "FRM001"}, "defaultSelected" : "blank"};			
	const comboObj3 = {"objId" : "document", "params" : {"parentCode" : "DOC001"}, "defaultSelected" : "blank"};			
						
	commonInitObj(comboObj);
	commonInitObj(comboObj2);
	commonInitObj(comboObj3);
	
	const data = commonCallAjax("/admin/getDeptList", "");
	const result = data.resultList;
	let dataList = new Array();
	
	for (idx in result) {				
        let data = new Object();
         
        data.code = result[idx].deptCode;
        data.name = result[idx].deptName;

		dataList.push(data);
	}
				
	const comboObj4  = {"objId" : "signDept", "params" : {}, "data" : dataList, "defaultSelected" : "blank"};	
	commonInitObj(comboObj4);
	
	if (!commonIsNull(fullEmail)) {
		const email = fullEmail.split("@")[0];
		const emailServer = fullEmail.split("@")[1];
		
		$("#email").val(email);
		$("#emailServer").val(emailServer);				
	}
	
	if (commonIsNull(logo)) {
		$("#cmpLogo").attr("src", "/images/blank.png");
		$("#imgSource").val("");
	} else {				
		$("#cmpLogo").attr("src", logo);
		$("#imgSource").val(logo);	
	}		
	
	$("#posCategory").val(posCategory);
};

//전자결재관리 : 파트 조회
const getSignPartList = function(deptCode) {			
	const data = commonCallAjax("/admin/getPartList", {"deptCode" : deptCode})
	
	if (data.code == "ok") {
		const result = data.resultList;
		
		//부서장 입력
		$("#signManagerCode").val(result[0].deptManager);
		$("#signManager").text(result[0].deptManagerName);
		
		let dataList = new Array();
		
		for (idx in result) {				
	        let data = new Object();
	         
	        data.code = result[idx].partCode;
	        data.name = result[idx].partName;

			dataList.push(data);
		}
		
		const comboObj = {"objId" : "signPart", "params" : {}, "data" : dataList, "defaultSelected" : "blank"};
		commonInitObj(comboObj);	
	}
};

//결재권자 조회
const getSignUser = function(autoSign) {
	const params = {
		"docType"  : $("#document").val(),
		"deptCode" : $("#signDept").val(),
		"partCode" : $("#signPart").val()
	};	
	const data = commonCallAjax("/admin/getSignUserList", params);
	
	if (data.code == "ok") {
		const result = data.resultList;
		const checked = autoSign == "Y" ? "checked='checked' disabled='disabled'" : "";
		
		let userHtml = "";	
		let signHtml = "";
		
		for (let i = 0; i < result.length; i++) {				
			userHtml += "<div class='row mt-2 pb-1 border-bottom'>";
			userHtml += "	<div class='col-1 text-center'><div class='custom-control custom-checkbox pb-2'>";
			userHtml += "	  <input type='checkbox' class='custom-control-input' id='sign"+i+"' data-user='"+ result[i].userName +"' data-code='"+ result[i].userCode +"' onclick='setSignLine(this)' "+ checked +">";
			userHtml += "	     <label class='custom-control-label' for='sign"+i+"'></label>";
			userHtml += "	</div></div>";
			userHtml += "	<div class='col text-center'>"+ result[i].userCode +"</div>";
			userHtml += "	<div class='col text-center'>"+ result[i].userName +"</div>";
			userHtml += "	<div class='col text-center'>"+ result[i].jobName +"</div>";
			userHtml += "</div>";

			if (autoSign == "Y") {								
				signHtml += "<li class='list-group-item' id='li-sign"+ i +"' data-code='"+ result[i].userCode +"'>";
				signHtml += "	<img src='/images/down.png' class='w18-h18 mr-2'>"+ result[i].userName +"(<span class='badge badge-primary badge-pill'>"+ result[i].userCode +"</span>)";
				signHtml += "</li>";					
			}
		}
		
		$("#userList").empty();
		$("#userList").append(userHtml);		
		$("#signuserList").empty();
		$("#signuserList").append(signHtml);
	}
};	

//결재라인 출력
const setSignLine = function(e) {
	let userHtml = "";
	const liId = "#li-" + e.id;

	if ($("input:checkbox[id='" + e.id + "']").is(":checked")) {
		const user = $("#" + e.id).data("user");
		const code = $("#" + e.id).data("code");
		
		userHtml += "<li class='list-group-item' id='li-" + e.id + "' data-code='"+ code +"'>";
		userHtml += "	<img src='/images/down.png' class='w18-h18 mr-2'>" + user + "(<span class='badge badge-primary badge-pill'>" + code + "</span>)";
		userHtml += "</li>";
				
		$("#signuserList").append(userHtml);
	} else {
		$(liId).remove();
	}
};

//결재라인 저장
const saveSignLine = function() {
	const params = {
		"autoSign" : $("#signPicker").prop("checked") == true ? "Y" : "N",
		"docType"  : $("#document").val(),
		"deptCode" : $("#signDept").val(),
		"partCode" : $("#signPart").val()
	};
	
	let array = new Array();
	const signUser = $(".list-group-item");	
	
	for (let i = 0; i < signUser.length; i++) { 
		array.push(signUser[i].dataset.code);
	}	
	params.data = JSON.stringify(array);
	
	const data = commonCallAjax("/admin/saveCompanySign", params);
	
	if (data.code == "ok") {
		alert("저장되었습니다.");
		
		initGridSignUser();
	}	
};