package ru.yandex.practicum.catsgram.exception;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(String message) {
        super(message);
    }
}
