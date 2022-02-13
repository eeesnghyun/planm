/************************************************************
  File    : common.js
  Content : 공통으로 사용하는 스크립트 선언
  Author  : LSH
  Create  : 2020.05.06
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
