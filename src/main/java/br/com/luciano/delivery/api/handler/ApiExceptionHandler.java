package br.com.luciano.delivery.api.handler;

import br.com.luciano.delivery.domain.exception.EntidadeEmUsoException;
import br.com.luciano.delivery.domain.exception.NegocioException;
import br.com.luciano.delivery.domain.exception.RestauranteNaoEncontradoException;
import br.com.luciano.delivery.domain.model.Problema;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<Problema> handleEntidadeEmUsoException(EntidadeEmUsoException ex) {

        Problema problema = Problema.builder()
                .date(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Problema> handleNegocioException(NegocioException ex) {
        Problema problema = Problema.builder()
                .date(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }

    @ExceptionHandler(RestauranteNaoEncontradoException.class)
    public ResponseEntity<Problema> handleRestauranteNaoEncontradoException(RestauranteNaoEncontradoException ex) {
        Problema problema = Problema.builder()
                .date(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        body = Problema.builder()
                .date(LocalDateTime.now())
                .mensagem(ex.getMessage())
                .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
