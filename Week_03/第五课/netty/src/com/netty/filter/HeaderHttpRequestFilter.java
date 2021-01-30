package com.netty.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullHttpRequest) {
        fullHttpRequest.headers().set("RequestFilter","FromWDH");
    }
}
