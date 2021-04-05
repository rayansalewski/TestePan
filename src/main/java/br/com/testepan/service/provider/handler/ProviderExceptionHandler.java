package br.com.testepan.service.provider.handler;

import br.com.testepan.api.exception.ErrorDetail;
import br.com.testepan.api.exception.ResourceNotFoundException;
import br.com.testepan.service.provider.ProviderException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ProviderExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProviderException.class)
    public ErrorDetail handleProviderException(ProviderException e) {
        log.error(e.getMessage());
        return new ErrorDetail(e.getMessage());
    }

}