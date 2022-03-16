
const readUserImg = function(input) {			
	$("#fileName").val(input.files[0].name);
	
	if (input.files && input.files[0]) {
		const file = input.files[0];
		
		if (file.size > 300000) {
            alert("파일 사이즈가 너무 큽니다.")
            return;
        }
		
		var fileReader = new FileReader();
		
		fileReader.onload = function(e) {
			$("#userImg").attr("src", e.target.result);
			$("#imgSource").val(e.target.result);
		};       
		fileReader.readAsDataURL(file);
	}
};

const isValid = function() {
	if (commonIsNull($("#userName").val())) {
		$("#userName").addClass("is-invalid");
		alert("이름을 입력해주세요.")
		return false;
	}
	
	if (commonIsNull($("#birthYmd").val())) {
		$("#birthYmd").addClass("is-invalid");
		alert("생년월일을 입력해주세요.")
		return false;
	}
	
	if (commonIsNull($("#email").val()) || commonIsNull($("#emailServer").val())) {
		if (commonIsNull($("#email").val())) $("#email").addClass("is-invalid");
		if (commonIsNull($("#emailServer").val())) $("#emailServer").addClass("is-invalid");
			
		alert("이메일을 입력해주세요.");
		return false;
	} 
				
	if (commonIsNull($("#deptCode").val()) || commonIsNull($("#partCode").val())) {
		if (commonIsNull($("#deptCode").val())) $("#deptCode").addClass("is-invalid");
		if (commonIsNull($("#partCode").val())) $("#partCode").addClass("is-invalid");
		
		alert("부서/파트를 선택해주세요.");
		return false;
	}

	if (commonIsNull($("#enterYmd").val())) {
		$("#enterYmd").addClass("is-invalid");
		alert("입사일을 입력해주세요.")
		return false;
	}
	
	return true;
};

const saveUser = function() {
	if (isValid()) {
		const email = $("#email").val().replace(/\@/g,'') + "@" + $("#emailServer").val();
		
		const params = {
			"userName"  : $("#userName").val(),
			"userAuth"  : $("#userAuth").val(),
			"birthYmd"  : $("#birthYmd").val(),
			"gender"    : $("input[name=gender]:checked").val(),
			"mobile"    : $("#mobile").val(),
			"email"     : email,
			"deptCode"  : $("#deptCode").val(),
			"partCode"  : $("#partCode").val(),
			"jobPos"    : $("#jobPos").val(),
			"userImg"   : $("#imgSource").val(),
			"enterYmd"  : $("#enterYmd").val(),
			"retireYmd" : $("#retireYmd").val()
		};
		const data = commonCallAjax("/user/save", params);
		
		if (data.code == "ok") {				
			alert("등록되었습니다.");
			
			commonResetForm("divUser");
			initGrid();
		}	
	}						
};

const updateUser = function() {
	if (isValid()) {
		const email = $("#email").val().replace(/\@/g,'') + "@" + $("#emailServer").val();
		
		const params = {
			"userCode" : $("#userCode").val(),
			"userName"  : $("#userName").val(),
			"birthYmd"  : $("#birthYmd").val(),
			"gender"    : $("input[name=gender]:checked").val(),
			"mobile"    : $("#mobile").val(),
			"email"     : email,
			"deptCode"  : $("#deptCode").val(),
			"partCode"  : $("#partCode").val(),
			"jobPos"    : $("#jobPos").val(),
			"userImg"   : $("#imgSource").val(),
			"enterYmd"  : $("#enterYmd").val(),
			"retireYmd" : $("#retireYmd").val()
		};			
		const data = commonCallAjax("/user/update", params);
		
		if (data.code == "ok") {
			alert("수정되었습니다.");
							
			initGrid();
		}
	}						
};				

const initGrid = function() {
	const data = commonCallAjax("/user/getUserList", "");
	
	if (data.code == "ok") {
		const result = data.resultList;
		
		if (commonIsNull(grid)) {
			//Grid draw
			grid = new Tabulator("#gridUser", {
				layout: "fitDataTable",
				placeholder: "데이터가 존재하지 않습니다.",
				height: "450px",	    
			    columns: [
			    	{title: "사번"     , field: "userCode"  , width: 100},
			    	{title: "이름"	   , field: "userName"  , width: 100},
			    	{title: "권한"	   , field: "userAuth"  , width: 100 , visible: false},
				    {title: "생년월일" , field: "birthYmd"  , width: 120, hozAlign: "center"},
				    {title: "성별"	   , field: "gender"    , width: 100, hozAlign: "center"},
				    {title: "연락처"   , field: "mobile"    , width: 150},
				    {title: "이메일"   , field: "email"     , width: 250},
				    {title: "부서코드" , field: "deptCode"  , visible: false},
				    {title: "부서"	   , field: "deptName"  , width: 130},
				    {title: "파트코드" , field: "partCode"  , visible: false},
				    {title: "파트"     , field: "partName"  , width: 130},
				    {title: "직급코드" , field: "jobPos"    , visible: false},
				    {title: "직급"	   , field: "jobName"    , width: 130},
				    {title: "입사일"   , field: "enterYmd"  , width: 120, hozAlign: "center"},
				    {title: "퇴사일"   , field: "retireYmd" , width: 120, hozAlign: "center"},
				    {title: "이미지"   , field: "userImg"   , visible: false}
			    ]			  
			});		        	

			//Data set
			grid.on("tableBuilt", function(){				
				grid.setData(result);
				grid.on("rowClick", function(e, row) {
					grid.deselectRow();
					row.toggleSelect();
			    	//e - click object
			        //row - row component
			        const rowData = row._row.data;
			    	const gender = rowData.gender == "남" ? "male" : "female";				    	
			    	const email = rowData.email.split("@");
			    	const userImg = rowData.userImg;
			    	
			    	$("#userCode").val(rowData.userCode);
			    	$("#userName").val(rowData.userName);
			    	$("#userAuth").val(rowData.userAuth);
			        $("#birthYmd").val(rowData.birthYmd);
			        $("input:radio[id='"+ gender +"']").prop("checked", true);
			        $("#mobile").val(rowData.mobile);
			        $("#email").val(email[0]);
			        $("#emailServer").val(email[1]);
			        $("#deptCode").val(rowData.deptCode);
			        
			        getPartList(rowData.deptCode);
			        
			        $("#partCode").val(rowData.partCode);
			        $("#jobPos").val(rowData.jobPos);
			        $("#enterYmd").val(rowData.enterYmd);
			        $("#retireYmd").val(rowData.retireYmd);
			        
			        if (commonIsNull(userImg)) {
			        	$("#userImg").attr("src", "/images/userImg.png");
				        $("#imgSource").val("");						        
			        } else {
			        	$("#userImg").attr("src", rowData.userImg);
				        $("#imgSource").val(rowData.userImg);
			        }
			        
			        $("#saveBtn").hide();
			        $("#updateBtn").show();
			    });
			});
		} else {
			grid.replaceData(result);
		}
        	
		$("#divUser").find("input, select").each(function() {
	 		$t = jQuery(this);
	 		$t.removeClass("is-invalid");
	    });	
	}					    	        
};

const initObj = function() {
	const data = commonCallAjax("/admin/getDeptList", "");
	const result = data.resultList;
	let dataList = new Array();
	
	for (idx in result) {				
        let data = new Object();
         
        data.code = result[idx].deptCode;
        data.name = result[idx].deptName;

		dataList.push(data);
	}
				
	const comboObj  = {"objId" : "deptCode", "params" : {}, "data" : dataList, "defaultSelected" : "blank"};			
	const comboObj2 = {"objId" : "jobPos", "params" : {"parentCode" : "POS001"}, "defaultSelected" : "blank"};			
	const comboObj3 = {"objId" : "emailServer", "params" : {"parentCode" : "FRM001"}, "defaultSelected" : "blank"};			
	const comboObj4 = {"objId" : "userAuth", "params" : {"parentCode" : "ROLE"}, "defaultSelected" : "blank"};
	
	commonInitObj(comboObj);
	commonInitObj(comboObj2);
	commonInitObj(comboObj3);
	commonInitObj(comboObj4);
};

const getPartList = function(deptCode) {
	const data = commonCallAjax("/admin/getPartList", {"deptCode" : deptCode})
	const result = data.resultList;
	let dataList = new Array();
	
	for (idx in result) {				
        let data = new Object();
         
        data.code = result[idx].partCode;
        data.name = result[idx].partName;

		dataList.push(data);
	}
	
	const comboObj = {"objId" : "partCode", "params" : {}, "data" : dataList, "defaultSelected" : "blank"};
	commonInitObj(comboObj);
};