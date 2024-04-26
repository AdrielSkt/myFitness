package com.NashDev.myFitness.service.exeption;

public class BusinessExeption extends RuntimeException{
    public BusinessExeption(){
        super();
    }
    public BusinessExeption(String message){
        super(message);
    }
    public BusinessExeption(Throwable cause){
        super(cause);
    }
    public BusinessExeption(String message, Throwable cause){
        super(message, cause);
    }
}
