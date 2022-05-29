package com.mycompany.training;

import com.mycompany.training.jetty.JettyServer;
import com.mycompany.training.server.UMThreadPoolServer;
import com.mycompany.training.service.WebClient;
import org.apache.thrift.transport.TTransportException;

public class Launcher {
    public static void main(String[] args) throws Exception {
        new JettyServer();
        new WebClient().startServer();
    }
}
