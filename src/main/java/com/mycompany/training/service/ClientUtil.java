package com.mycompany.training.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import io.vertx.core.json.JsonObject;

public class ClientUtil {
    String url;

    public ClientUtil(String url) {
        this.url = url;
    }

    public HttpResponse<String> call(String path, JsonObject body, String sessionId) {
        try {
               Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post(url + path)
                    .header("Content-Type", "application/json")
                    .body(String.valueOf(body))
                    .asString();
            return response;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
