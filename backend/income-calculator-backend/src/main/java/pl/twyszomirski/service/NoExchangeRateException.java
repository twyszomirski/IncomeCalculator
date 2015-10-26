package pl.twyszomirski.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Tomasz
 * Exception indicating problem with providing the exchange rate
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="No exchange rates available")
public class NoExchangeRateException extends Exception{

    public NoExchangeRateException(){
        super();
    }

    public NoExchangeRateException(Throwable cause){
        super(cause);
    }
}
