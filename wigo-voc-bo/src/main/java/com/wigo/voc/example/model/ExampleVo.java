package com.wigo.voc.example.model;

import com.wigo.voc.common.model.BaseVo;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExampleVo extends BaseVo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
    * <p>코드아이디</p> 
    * <p>공통코드 : 121</p> 
    */
    @NonNull
    private String codeCd;
    /**
    * <p>대분류</p> 
    * <p>공통코드 : 1212</p> 
    */
    @NonNull
    private String rtCodeCd;
    /**
    * <p>코드레벨</p> 
    * <p>공통코드 : 12121</p> 
    */
    @NonNull
    private String codeLevel;
    /**
    * <p>코드순번</p> 
    * <p>공통코드 : 1212</p> 
    */
    private int codeSeq;
    /**
    * <p>상위코드</p> 
    */
    private String uprCodeCd;
    /**
    * <p>코드명</p> 
    */
    @NonNull
    private String codeNm;
    /**
    * <p>사용여부</p> 
    */
    @NonNull
    private String useYn = "Y";
    /**
    * <p>참조코드명1</p> 
    */
    private String refCd1;
    /**
    * <p>참조코드명2</p> 
    */
    private String refCd2;
    /**
    * <p>참조코드명3</p> 
    */
    private String refCd3;
    /**
    * <p>참조코드명4</p> 
    */
    private String refCd4;
    /**
    * <p>참조코드명5</p> 
    */
    private String refCd5;
    /**
    * <p>참조코드명6</p> 
    */
    private String refCd6;
    /**
    * <p>참조코드명7</p> 
    */
    private String refCd7;
    /**
    * <p>참조코드명8</p> 
    */
    private String refCd8;
    /**
    * <p>참조코드명9</p> 
    */
    private String refCd9;
    /**
    * <p>사용여부1</p> 
    */
    @NonNull
    private String useYn1 = "Y";
    /**
    * <p>사용여부2</p> 
    */
    @NonNull
    private String useYn2 = "Y";
    /**
    * <p>사용여부3</p> 
    */
    @NonNull
    private String useYn3 = "Y";
    /**
    * <p>사용여부4</p> 
    */
    @NonNull
    private String useYn4 = "Y";
    /**
    * <p>사용여부5</p> 
    */
    @NonNull
    private String useYn5 = "Y";
    /**
    * <p>사용여부6</p> 
    */
    @NonNull
    private String useYn6 = "Y";
    /**
    * <p>사용여부7</p> 
    */
    @NonNull
    private String useYn7 = "Y";
    /**
    * <p>사용여부8</p> 
    */
    @NonNull
    private String useYn8 = "Y";
    /**
    * <p>사용여부9</p> 
    */
    @NonNull
    private String useYn9 = "Y";
}
