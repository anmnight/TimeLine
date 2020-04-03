package com.anxiao.timeline.domain.exceptions;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile.domain.exceptions
 * @description: description
 * @date: 2020-01-02
 * @time: 14:28
 */
public class PostInfoException extends Exception {

    //post address error
    private int POST_ADDRESS_ERROR = 10000;
    
    private int LOAD_BANKS_ERROR = 10001;
    
    private int POST_USERINFO_ERROR=10002;
    
    private int SEND_SMSCODE_ERROR=10003;
    
    private int VERIFY_SMSCODE_ERROR=10004;

    private int mErrorCode;

    public PostInfoException(int errorCode) {
        this.mErrorCode = errorCode;
    }


    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println("ERROR : " + mErrorCode);
    }
}
