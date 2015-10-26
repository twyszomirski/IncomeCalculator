package pl.twyszomirski.service;

/**
 * Created by Tomasz
 * Exception indicating problem with providing the exchange rate
 */
public class NoExchangeRateException extends Exception{

    public NoExchangeRateException(){
        super();
    }

    public NoExchangeRateException(Throwable cause){
        super(cause);
    }
}
