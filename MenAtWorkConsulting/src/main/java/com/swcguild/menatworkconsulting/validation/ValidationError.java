/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.menatworkconsulting.validation;

/**
 *
 * @author Christopher Becker <no.one.laughed@gmail.com>
 */
public class ValidationError {
    

//At this step, I'm not sure of the code.
//    page 4 of the tutorial





 private String fieldEmail;
    private String message;
    
    public ValidationError(String fieldEmail, String message) {
        this.fieldEmail = fieldEmail;
        this.message = message;
    }

    public String getFieldEmail() {
        return fieldEmail;
    }

    public String getMessage() {
        return message;
    }
}
