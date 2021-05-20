package com.company.exceptions;

public class NoSuchCommandException extends IncorrectInputException{

    @Override
    public String toString() {
        return "No such command.";
    }
}
