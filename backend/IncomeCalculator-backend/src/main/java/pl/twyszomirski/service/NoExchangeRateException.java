package pl.twyszomirski.service;

/**
 * Created by Tomasz
 */
public class NoExchangeRateException extends Exception{

    public NoExchangeRateException(){
        super();
    }

    public NoExchangeRateException(Throwable cause){
        super(cause);
    }
}
