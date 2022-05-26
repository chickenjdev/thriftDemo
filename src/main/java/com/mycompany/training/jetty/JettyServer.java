package com.mycompany.training.jetty;

import com.mycompany.training.client.UserAsyncClient;
import com.mycompany.training.servlet.LoginServlet;
import com.mycompany.training.servlet.LogoutServlet;
import com.mycompany.training.servlet.SignUpServlet;
import com.mycompany.training.servlet.UserServlet;
import com.mycompany.training.thrift.UserManager;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    private Server server;
    public static UserManager.Client client;

    public JettyServer() throws Exception {
        start();
    }
    public void start() throws Exception {
        UserAsyncClient userAsyncClient = new UserAsyncClient();
        client = userAsyncClient.getClient();

        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[]{connector});
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(LoginServlet.class, "/login");
        handler.addServletWithMapping(LogoutServlet.class, "/logout");
        handler.addServletWithMapping(SignUpServlet.class, "/sign-up");
        handler.addServletWithMapping(UserServlet.class, "/user");
        server.start();
    }

    public static void main(String[] args) throws Exception {
        new JettyServer();
    }
}