package in.itkaran.splitwise_180824.advices;

import in.itkaran.splitwise_180824.exceptions.InvalidUserException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidUserException.class)
    public String handleInvalidUserException(InvalidUserException e) {
        return e.getMessage();
    }
}
