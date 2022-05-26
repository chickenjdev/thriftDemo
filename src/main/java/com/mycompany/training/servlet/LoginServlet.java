package com.mycompany.training.servlet;

import com.mycompany.training.client.UserAsyncClient;
import com.mycompany.training.jetty.JettyServer;
import com.mycompany.training.jetty.ResponseOutput;
import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserManager;
import io.vertx.core.json.JsonObject;
import org.apache.thrift.TException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("do post login");
        JsonObject body = new JsonObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

        try {
            ResponseData responseData = JettyServer.client.login(body.getString("username"), body.getString("password"));
            if (responseData.getSessionInfo() != null) {
                response.addHeader("session-id", responseData.getSessionInfo().getSessionId());
                response.addHeader("session-expire", String.valueOf(responseData.getSessionInfo().getExpireTime()));
            }
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