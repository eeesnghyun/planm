const initObj = function() {													
	const comboObj  = {"objId" : "leaveType", "params" : {"parentCode" : "LVE001"}, "defaultSelected" : "blank"};			
	const comboObj2 = {"objId" : "dayType", "params" : {"parentCode" : "LVE002"}, "defaultSelected" : "blank"};			
						
	commonInitObj(comboObj);
	commonInitObj(comboObj2);

	$("#leaveType option:eq(1)").prop("selected", true); //년차 기본값
	$("#dayType option:eq(1)").prop("selected", true); 	 //전일 기본값
};

const initLeave = function() {
	const data = commonCallAjax("/document/getUserLeave", "");
	
	if (data.code == "ok") {				
		const result = data.result;
						
		$("#createDay").val(result.createDay);
		$("#useDay").val(result.useDay);
		$("#remainDay").val(result.remainDay);			   	
	}
};

//결재라인 가져오기
const initSignLine = function() {
	const data = commonCallAjax("/document/getSignUser", {"docType" : $("#docType").val()});
	
	if (data.code == "ok") {
		const result = data.resultList;
		
		let userHtml = "";				
		
		for (let i = 0; i < result.length; i++) {
				userHtml += "<div class='row mt-2 pb-1 border-bottom'>";
				userHtml += "	<div class='col-1 text-center'><div class='custom-control custom-checkbox pb-2'>";
				userHtml += "	  <input type='checkbox' class='custom-control-input' id='sign"+i+"' data-user='"+ result[i].userName +"' onclick='setSignUser(this)'>";
				userHtml += "	     <label class='custom-control-label' for='sign"+i+"'></label>";
				userHtml += "	</div></div>";
				userHtml += "	<div class='col text-center'>"+ result[i].userCode +"</div>";
				userHtml += "	<div class='col text-center'>"+ result[i].userName +"</div>";
				userHtml += "	<div class='col text-center'>"+ result[i].jobName +"</div>";
				userHtml += "</div>";
		}

		$("#userList").append(userHtml);
	}
};	

const getLeaveUseday = function(gubun) {
	let count = 0;
	const mStart = moment($("#startDay").val());
	const mEnd   = moment($("#endDay").val());
	const startDay = new Date(mStart.year(), mStart.month(), mStart.date());
	const endDay   = new Date(mEnd.year(), mEnd.month(), mEnd.date());
	const dayType   = $("#dayType option:selected").val();
	const leaveType = $("#leaveType option:selected").val();

	if (leaveType == "" || dayType == "") {
		alert("구분, 전일/반일을 선택해주세요.");				
		return;
	}

	if (!isNaN(startDay) && !isNaN(endDay)) {
		if (endDay < startDay) {
			alert("종료일이 시작일보다 작을 수 없습니다.");
			$("#endDay").val("");
			return;
		}
	}			
	
	while (true) {
		let temp = startDay;
		
		if (temp.getTime() > endDay.getTime()) {					
			break;
		} else {					
			//주말 계산(0:일,6:토)
			const day = temp.getDay();
			if (day != 0 || day != 6) {
				//공휴일 계산
				const yyyymmdd = temp.toISOString().slice(0,10).replace(/-/g,"");
				if (!isHoliday(yyyymmdd)) {
					count++;
				}
			}
			temp.setDate(startDay.getDate() + 1);
		}				
	}
	
	if (dayType == "D002" || dayType == "D003") {		//반차인 경우
		if (gubun == "s") {
			$("#endDay").val($("#startDay").val());	
		} else {
			$("#startDay").val($("#endDay").val());
		}
		
		$("#leaveCnt").val(0.5);
	} else {
		$("#leaveCnt").val(count);
	}
};		

const isHoliday = function(yyyymmdd) {			
 	for (let i = 0; i < holidays.length; i++) {
  		if (holidays[i] == yyyymmdd) {
   			return true;
  		}
	}
};

const setSignUser = function(e) {
	var userHtml = "";
	var liId = "#li-" + e.id;
	
	if($("input:checkbox[id='" + e.id + "']").is(":checked")) {
		const user = $("#" + e.id).data("user");
		
		userHtml += "<li class='list-group-item' id='li-" + e.id + "'>";
		userHtml += "	<img src='/images/down.png' class='w18-h18 mr-2'>" + user + "(<span class='badge badge-primary badge-pill'>" + e.id + "</span>)";
		userHtml += "</li>";
		
		$("#signuserList").append(userHtml);
	} else {
		$(liId).remove();
	}
};

const saveDoc = function() {
	const params = {
		"docType"   : "0002",
		"leaveType" : "A001",
		"dayType"   : "D001",
		"startDay"  : "2022-02-17",
		"endDay"    : "2022-02-17",
		"leaveCnt"  : "1",
		"remark"    : "테스트"								
	};
	
	const data = commonCallAjax("/document/saveDoc", params);
	
	if (data.code == "ok") {				
		alert("임시저장 되었습니다.");
	}
};

const requestDoc = function() {
	const params = {
		"docType"   : $("#docType").val(),
		"leaveType" : "A001",
		"dayType"   : "D001",
		"startDay"  : "2022-02-17",
		"endDay"    : "2022-02-17",
		"leaveCnt"  : "1",
		"remark"    : "테스트",
		"signUser"  : "001,002"									
	};
	
	const data = commonCallAjax("/document/requestDoc", params);
	
	if (data.code == "ok") {				
		alert("결재신청 되었습니다.");
	}
};
