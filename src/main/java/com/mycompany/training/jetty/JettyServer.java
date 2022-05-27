package com.mycompany.training.jetty;

import com.mycompany.training.client.UserAsyncClient;
import com.mycompany.training.servlet.LoginServlet;
import com.mycompany.training.servlet.LogoutServlet;
import com.mycompany.training.servlet.SignUpServlet;
import com.mycompany.training.servlet.UserServlet;
import com.mycompany.training.thrift.UserManager;
import io.vertx.core.json.JsonObject;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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

        FilterHolder filter = new FilterHolder();
        filter.setInitParameter("allowedOrigins", "*");
        filter.setInitParameter("allowedMethods", "POST,GET,OPTIONS,PUT,DELETE,HEAD");
        filter.setInitParameter("allowedHeaders", "origin,Access-Control-Allow-Origin,Content-type,X-PINGARUNER,accept,x-requested-with");
        filter.setInitParameter("chainPreflight", "false");
        filter.setInitParameter("allowCredentials", "true");

        JsonObject config = new JsonObject();
        config.put("allowedOrigins","*");
        config.put("allowCredentials","false");
        config.put("allowedMethods","POST,GET,OPTIONS,PUT,DELETE,HEAD");
        config.put("allowedHeaders","origin,Access-Control-Allow-Origin, Content-type,X-PINGARUNER,accept,x-requested-with");


        CrossOriginFilter corsFilter = new CrossOriginFilter();
        corsFilter.init(new FilterConfig() {
            @Override
            public String getFilterName() {
                return null;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public String getInitParameter(String s) {
                return config.getString(s);
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        });
        filter.setFilter(corsFilter);

        handler.addFilter(filter);

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