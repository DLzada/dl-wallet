package br.com.daniel.dl_wallet.infra.exception;

import br.com.daniel.dl_wallet.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        String mensagem = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroCampoDTO> handleValidation(MethodArgumentNotValidException ex){
        List<ErroCampoDTO> erros = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.add(new ErroCampoDTO(error.getField(), error.getDefaultMessage()));
        });

        return erros;
    }
}
