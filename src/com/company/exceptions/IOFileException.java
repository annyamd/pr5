package com.company.exceptions;

import java.io.IOException;

public class IOFileException extends IOException {
    @Override
    public String toString() {
        return "Impossible to make this action with the file. ";
    }
}
