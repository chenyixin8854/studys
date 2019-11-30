package com.example.demo.feign;

import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-12 23:50
 **/
public class GitHubErrorDecoder implements ErrorDecoder {
    final Decoder decoder;
    final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    GitHubErrorDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            // must replace status by 200 other GSONDecoder returns null
            response = response.toBuilder().status(200).build();
            return (Exception) decoder.decode(response, GitHubClientError.class);
        } catch (IOException fallbackToDefault) {
            return defaultDecoder.decode(methodKey, response);
        }
    }
}
