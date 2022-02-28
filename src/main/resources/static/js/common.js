/************************************************************
 * File    : common.js
 * Content : 공통으로 사용하는 스크립트 선언
 * Author  : LSH
 * Create  : 2020.05.06
 ************************************************************/

/**
 * 공통 ajax 호출(Method : POST)
 * return : json 
 */
const commonCallAjax = function(url, params) {
	var returnData = {};

	$.ajax({
        type       : "POST",
        async      : false,
        url        : url,
        cache      : false,
        dataType   : "json",
        data       : JSON.stringify(params),
        contentType: "application/json; charset=utf-8",
        beforeSend : function(xhr)
	    {
	        xhr.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
	    },
        success: function(data) {
        	returnData = data;
        },
		error : function(request, status, error) {
			//alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);			
		}
	});

	return returnData;
};

const commonIsNull = function(obj) {
	if (obj == "" || obj == null || obj == undefined || obj == "undefined" || (obj != null && typeof obj == "object" && !Object.keys(obj).length)) {
		return true;
	} else {
		return false;
	}
};

/**
 * Bootstrap-Datepicker 옵션 지정
 * 참고 : https://github.com/uxsolutions/bootstrap-datepicker
 */
const commonDatePickerOpt = {
    format: "yyyy-mm-dd",			//데이터 포맷 형식
    autoclose : true,				//날짜 클릭시 자동 닫힘
    calendarWeeks : false, 			//주차별 표시    
    datesDisabled : ['2019-06-24','2019-06-26'],//선택 불가능 날짜 설정(데이터 포맷 형식과 동일하도록)        
    disableTouchKeyboard : false,	//모바일 플러그인 작동 여부(false:작동,true:작동X)
    immediateUpdates: false,		//날짜 변경 여부 
    multidate : false, 				//여러 날짜 선택 여부 
    multidateSeparator :",", 		//여러 날짜 선택시 구분값(2019-05-01,2019-06-01)
    templates : {
        leftArrow: '&laquo;',
        rightArrow: '&raquo;'
    }, 								//다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징    
    showWeekDays : true,			//요일 표시 여부
    todayHighlight : true ,			//오늘 날짜 하이라이팅 여부 
    weekStart : 0 ,					//달력 시작 요일(0:일요일) 
    language : "ko"					//달력 언어	    
};

const commonGetToday = function() {
	const date = new Date();
	
	return date.getFullYear() + "-" 
			+ ("0"+(date.getMonth()+1)).slice(-2) + "-" 
			+ ("0"+date.getDate()).slice(-2);
};

const commonDateCalcurator = function(date, num, type) {
	let yy = parseInt(date.substr(0, 4), 10);
    let mm = parseInt(date.substr(5, 2), 10);
    let dd = parseInt(date.substr(8), 10);
    
    if (type == "dd") {   		 //일
    	d = new Date(yy, mm - 1, dd + num);
    } else if (type == "mm") {   //월
    	d = new Date(yy, mm - 1, dd + (num * 31));
    } else if (type == "yy") {   //년
    	d = new Date(yy + num, mm - 1, dd);
    }
    
    yy = d.getFullYear();
    mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm; 
    dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;
    
    return '' + yy + '-' +  mm  + '-' + dd;
};

const commonResetForm = function(id) {
 	var divId = "#" + id;

 	//input, select, textarea Element 초기화
 	$(divId).find("input, select, textarea").each(function() {
 		$t = jQuery(this);
 		
        if (($t.is("input") == true && $t.attr("type") == "text") ||
            ($t.is("input") == true && $t.attr("type") == "hidden") || $t.is("textarea") == true) {
         	$t.val("");         	
        } else if ($t.is("select") == true) {
         	$("#" + $t.attr("id") + " option:eq(0)").prop("selected", true);         	
 		}
    }); 	

 	//Checkbox 초기화
 	$(divId + " input[type=checkbox]").prop("checked", false);

 	//Radio button 초기화
 	var radioArr = [];
    var radio = $(divId + " input[type=radio]");

    $.each(radio, function(key, value) { 			 //input radio의 name값
     	radioArr.push($(value).attr("name"));
    });

    radioArr = $.unique(radioArr.sort()).sort();	 //중복요소 이름을 제거

    for(var i = 0; i < radioArr.length; i++) {
    	$('input[name="' + radioArr[i] + '"]').removeAttr('checked');
     	$('input[name="' + radioArr[i] + '"]')[0].checked = true;
    }

    //File 초기화
    var fileArr = [];
    var file = $(divId + " input[type=file]");

    $.each(file, function(key, value) { 			 //input file의 id값
     	fileArr.push($(value).attr("id"));
    });

    fileArr = $.unique(fileArr.sort()).sort(); 		 //중복요소 이름을 제거
	if (fileArr.length > 1) {
		for (var i = 0; i < fileArr.length; i++) {
	    	$("#" + fileArr[i]).MultiFile("reset");
	    }
	}
};

const commonInitObj = function(comboObj) {
	let result = "";
	const obj = $("#" + comboObj.objId);
	const params = comboObj.params;
	
	obj.empty();
	
	/**
	 * blank : 선택
	 * all : 전체
	 */
	if (comboObj.defaultSelected == "blank") {
		obj.append("<option value=''>선택</option>");
	} else if (comboObj.defaultSelected == "all") {
		obj.append("<option value='all'>전체</option>");
	}
	
	if (commonIsNull(comboObj.data)) {	
		const data = commonCallAjax("/cmn/getCodeList", {"parentCode" : params.parentCode});
			
		if (data.code == "ok") {
			result = data.resultList;
			
			for (idx in result) {
				obj.append("<option value='" + result[idx].code + "'>" + result[idx].name + "</option>");					
			}
		}	
	} else {
		result = comboObj.data;
		
		for (idx in result) {			
			obj.append("<option value='" + result[idx].code + "'>" + result[idx].name + "</option>");					
		}
	}	
};

