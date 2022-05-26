package com.mycompany.training.servlet;

import com.mycompany.training.data.RequestUserType;
import com.mycompany.training.data.UserRequest;
import com.mycompany.training.jetty.JettyServer;
import com.mycompany.training.jetty.ResponseOutput;
import com.mycompany.training.thrift.ResponseData;
import io.vertx.core.json.JsonObject;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SignUpServlet extends AuthenServlet {
    @Override
    protected void doFunction(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("do user sign up post");
            JsonObject body = new JsonObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
            UserRequest userRequest = body.mapTo(UserRequest.class);

            if (userRequest.getType() == RequestUserType.CREATE) {
                createUser(request, response, userRequest, body.getString("password"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void createUser(HttpServletRequest request, HttpServletResponse response, UserRequest userRequest, String password) {
        try {
            ResponseData responseData = JettyServer.client.createUser(userRequest.getUserData(), password);
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