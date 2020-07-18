package com.homework.loan.service;

import java.util.List;

public class Error extends Response {

    private final List<String> errorList;

    public Error(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }

}
