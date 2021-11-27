package org.ogorodnik.network;

import java.util.HashMap;
import java.util.Map;

class Request {
    private HttpMethod httpMethod;
    private String uri;
    private Map<String, String> headers = new HashMap<>();

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public String getHeaders(String key) {
        return headers.get(key);
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setHeaders(String key, String value) {
        headers.put(key, value);
    }
}