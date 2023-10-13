package ru.yandex.practicum.catsgram.exception.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.exception.DataAlreadyExistException;
import ru.yandex.practicum.catsgram.exception.DataNotFoundException;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerIncorrectParameter(IncorrectParameterException exception) {
        log.warn("Status received: 400 BAD_REQUEST. ex.Message: " + exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerInvalidEmail(InvalidEmailException exception) {
        log.warn("Status received: 400 BAD_REQUEST. ex.Message: " + exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFound(DataNotFoundException exception) {
        log.warn("Status received: 404 NOT_FOUND. ex.Message:" + exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCategoryIsNotEmpty(final DataAlreadyExistException exception) {
        log.warn("Status received: 409 CONFLICT. ex.Message: " + exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable exception) {
        log.warn("Status received: 500 SERVER ERROR. ex.Message: " + exception.getMessage());
        return new ErrorResponse(exception.getMessage());
    }
}
