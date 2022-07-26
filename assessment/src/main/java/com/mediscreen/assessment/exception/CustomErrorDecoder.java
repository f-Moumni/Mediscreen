package com.mediscreen.assessment.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response reponse) {
        if(reponse.status() == 404  ) {
            return new RessourceNotfoundException( " 404 error "
            );
        }
        return defaultErrorDecoder.decode(invoqueur, reponse);
    }
}