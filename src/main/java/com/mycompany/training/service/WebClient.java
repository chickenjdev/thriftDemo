package com.mycompany.training.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class WebClient extends AbstractVerticle {
    ClientUtil clientUtil;
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        clientUtil = new ClientUtil("http://localhost:8090");
        HttpServer server = Vertx.vertx().createHttpServer();
        Router router = Router.router(this.getVertx());

        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-requested-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");

        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);

        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.PUT);

        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));
        router.post("/login").handler(routingContext -> {
            routingContext.request().bodyHandler(bh -> {
                System.out.println(bh.toJsonObject());
                login(routingContext, bh);
            });
        });

        server.requestHandler(router).listen(8089);
        startPromise.complete();
    }

    private void login(RoutingContext context, Buffer bh) {
        HttpServerResponse response = context.response();
        JsonObject jo = bh.toJsonObject().getJsonObject("loginJson");
        JsonObject res = new JsonObject(clientUtil.call("/login", jo));
        System.out.println("login called");
        response.putHeader("Content-Type","application/json");
        response.end(res.encode());
    }


    public WebClient() {
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(WebClient.class.getName());
    }
}
