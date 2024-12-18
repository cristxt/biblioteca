
package com.example.proyecto_biblioteca.exception;

public class SuccessResponse<T> {
    private String message;

    public SuccessResponse(String message, T data) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
