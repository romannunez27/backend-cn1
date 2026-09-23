package ms_pedidos_360.bff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> manejarErrorCliente(
            HttpClientErrorException ex) {


        return ResponseEntity
                .status(ex.getStatusCode())
                .body(
                        new ErrorResponse(
                                LocalDateTime.now(),
                                ex.getStatusCode().value(),
                                ex.getResponseBodyAsString()
                        )
                );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarErrorGeneral(
            Exception ex) {


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponse(
                                LocalDateTime.now(),
                                500,
                                ex.getMessage()
                        )
                );
    }

}