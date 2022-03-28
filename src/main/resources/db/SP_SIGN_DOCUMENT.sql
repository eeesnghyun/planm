DELIMITER $$
CREATE DEFINER=`lsh`@`%` PROCEDURE `SP_SIGN_DOCUMENT`(
	  v_CMP_CODE  VARCHAR(4)
    , v_DOC_NO    VARCHAR(10)
    , v_USER_CODE VARCHAR(30)
)
BEGIN
	DECLARE _signYn VARCHAR(10);

	# 결재자의 하위라인까지 모두 결재처리
	UPDATE PM_DOCUMENT_SIGN
	   SET SIGN_YMD   = NOW()
		 , DOC_STATUS = '0002'
	 WHERE CMP_CODE = v_CMP_CODE
	   AND DOC_NO   = v_DOC_NO
	   AND SIGN_SEQ <= (SELECT SIGN_SEQ
						 FROM (
								SELECT A.SIGN_SEQ 
								  FROM PM_DOCUMENT_SIGN A
								 WHERE A.CMP_CODE  = v_CMP_CODE
								   AND A.DOC_NO    = v_DOC_NO
								   AND A.SIGN_USER = v_USER_CODE
								 ) TMP
					    );

	SELECT IF(DOC_STATUS = '0002', 'Y', 'N')
      INTO _signYn
	  FROM PM_DOCUMENT_SIGN
	 WHERE CMP_CODE = v_CMP_CODE
	   AND DOC_NO   = v_DOC_NO
	   AND SIGN_SEQ = (
						SELECT MAX(A.SIGN_SEQ)
						  FROM PM_DOCUMENT_SIGN A
						 WHERE A.CMP_CODE = v_CMP_CODE
						   AND A.DOC_NO   = v_DOC_NO						
					   );

	# 마지막 결재라인이 결재완료인 경우
	IF _signYn = 'Y' THEN
		UPDATE PM_DOCUMENT
           SET DOC_STATUS = '0002'
             , SIGN_YMD   = NOW()
		 WHERE CMP_CODE = v_CMP_CODE
           AND DOC_NO   = v_DOC_NO;
	END IF;
			
END$$
DELIMITER ;
