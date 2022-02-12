/************************************************************
  File    : common.js
  Content : 공통으로 사용하는 스크립트 선언
  Author  : LSH
  Create  : 2020.05.06
 ************************************************************/

/*
 * 공통 POST 방식의 Ajax
 * return type은 json으로 통일한다.
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

/*
 * Jquery UI 날짜선택 함수
 * - id : Elements id
 */
const commonDatePicker = function(id) {
	$("#" + id).datepicker({
		showOn: "button",
		buttonImage: "/images/datepicker.png",
		buttonImageOnly: true,
		buttonText: "Select date",
		dateFormat: "yy-mm-dd",		//날짜 포맷 설정
		changeYear: true,			//년도 변경 옵션
		changeMonth: true			//월 변경 옵션
    });
};
