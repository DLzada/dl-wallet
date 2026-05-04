package br.com.daniel.dl_wallet.infra.exception;

import br.com.daniel.dl_wallet.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
        ErrorResponse erro = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroCampoDTO> handleValidation(MethodArgumentNotValidException ex){
        List<ErroCampoDTO> erros = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.add(new ErroCampoDTO(error.getField(), error.getDefaultMessage()));
        });
        return erros;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", String.format("O parametro '%s' recebeu o valor '%s' que é inválido. Esperado: %s", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));

        return erro;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex){
        Map<String, String> erro = new HashMap<>();

        erro.put("mensagem", "Este endpoint não suporta o método" + ex.getMethod());

        return erro;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneralException(Exception ex){
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", "Ocorreu um erro interno no servidor. Tente novamente mais tarde.");
        return erro;
    }
}
