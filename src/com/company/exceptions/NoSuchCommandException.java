package com.company.exceptions;

public class NoSuchCommandException extends Exception{

    @Override
    public String toString() {
        return "No such command.";
    }
}
