package com.calabashCat.android.sample.data.exception.exceptions;


public class AreaTooLarge extends YelpAPIError {
    public AreaTooLarge(int code, String message, String id, String text) {
        super(code, message, id, text);
    }
}
