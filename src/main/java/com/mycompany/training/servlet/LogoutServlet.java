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
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("do post logout");

        try {
            JsonObject body = new JsonObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
            ResponseData responseData = JettyServer.client.logout(body.getString("username"));

            JsonObject resBody = JsonObject.mapFrom(responseData);
            ByteBuffer content = ByteBuffer.wrap(String.valueOf(resBody).getBytes(StandardCharsets.UTF_8));

            AsyncContext async = request.startAsync();

            ServletOutputStream out = response.getOutputStream();
            new ResponseOutput(response, out, content, async);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}