package com.book.book_store.dto.response;

public interface ResponseMessage {

    String SUCCESS = "Success.";
    String AUTHENTICATION_FAIL = "Authentication fail.";
    String DATABASE_ERROR = "Database error.";
    String SIGN_IN_FAIL = "Sign in fail.";
    String TOKEN_CREATE_FAIL = "Token Create fail.";
    String NO_EXIST_USER_ID = "No exist user id.";
    String NO_EXIST_BOOK = "No exist book.";
    String NO_PURCHASE_BOOK = "No exist purchase book.";
    String ALREADY_REVIEW_WRITE = "Already review write.";
    String NO_PERMISSION = "No permission.";


}
