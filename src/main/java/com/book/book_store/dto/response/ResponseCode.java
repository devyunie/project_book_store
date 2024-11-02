package com.book.book_store.dto.response;

public interface ResponseCode {
    String SUCCESS = "SU";
    String AUTHENTICATION_FAIL = "AF";
    String DATABASE_ERROR = "DB";
    String SIGN_IN_FAIL = "SF";
    String TOKEN_CREATE_FAIL = "TF";
    String NO_EXIST_BOOK = "NB";
    String NO_EXIST_USER_ID = "NI";
    String YES_PURCHASE_BOOK = "YPB";
    String ALREADY_REVIEW_WRITE = "ARW";
    String NO_PERMISSION = "NP";


}
