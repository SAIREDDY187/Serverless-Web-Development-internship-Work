package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MyLambda implements RequestHandler<RequestClass, ResponseClass> {

    public ResponseClass handleRequest(RequestClass request, Context context) {
        boolean isValid = validateCredentials(request.getUsername(), request.getPassword());
        if (isValid) {
            return new ResponseClass("SUCCESS");
        } else {
            return new ResponseClass("FAILED");
        }
    }

    private boolean validateCredentials(String username, String password) {
        if (username == null || username.length() > 8 || !username.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        if (password == null || password.length() > 8 || !password.matches("[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+")) {
            return false;
        }
        return true;
    }

    public static class RequestClass {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class ResponseClass {
        private String message;

        public ResponseClass(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
