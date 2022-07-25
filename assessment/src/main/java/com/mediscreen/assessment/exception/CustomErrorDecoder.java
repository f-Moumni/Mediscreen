package com.mediscreen.assessment.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {
        if(reponse.status() > 400 && reponse.status() <=499 ) {
            return new Ressource4XXException(
                    "4XX error "
            );
        }
        return defaultErrorDecoder.decode(invoqueur, reponse);
    }
}