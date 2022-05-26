package com.mycompany.training.servlet;

import com.mycompany.training.jetty.JettyServer;
import com.mycompany.training.jetty.ResponseOutput;
import com.mycompany.training.thrift.ResponseData;
import io.vertx.core.json.JsonObject;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class AuthenServlet extends HttpServlet {
    ArrayList<String> ignoreAuthen = new ArrayList<String>() {
        {
            add("/login");
            add("/logout");
            add("/sign-up");
        }
    };
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("do authentication user");
        try {
            if (ignoreAuthen.contains(request.getRequestURI())){
                doFunction(request, response);
                return;
            }
            ResponseData responseData = JettyServer.client.getSessionInfo(request.getHeader("session-id"));
            if (responseData.getError() != 0 ){
                JsonObject resBody = JsonObject.mapFrom(responseData);
                ByteBuffer content = ByteBuffer.wrap(String.valueOf(resBody).getBytes(StandardCharsets.UTF_8));
                AsyncContext async = request.startAsync();
                ServletOutputStream out = response.getOutputStream();
                new ResponseOutput(response, out, content, async);
            }
            if (responseData.getSessionInfo() != null) {
                response.addHeader("session-id", responseData.getSessionInfo().getSessionId());
                response.addHeader("session-expire", String.valueOf(responseData.getSessionInfo().getExpireTime()));
            }
            doFunction(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected abstract void doFunction(HttpServletRequest request, HttpServletResponse response);
}
