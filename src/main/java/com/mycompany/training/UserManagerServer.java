package com.mycompany.training;

import com.mycompany.training.thrift.UserInfoImpl;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class UserManagerServer {
    TServer server;
    public void start() throws TTransportException {
        TServerTransport serverTransport = new TServerSocket(3030);
        server = new TSimpleServer(new TServer.Args(serverTransport)
                .processor(new UserManager.Processor<>(new UserInfoImpl())));

        System.out.print("Starting the server... ");
        new Thread(() -> server.serve()).start();

        System.out.println("done.");
    }

    public void stop() {
        if (server != null && server.isServing()) {
            System.out.print("Stopping the server... ");

            server.stop();

            System.out.println("done.");
        }
    }

    public static void main(String[] args) throws TTransportException {
        UserManagerServer calculatorServer = new UserManagerServer();
        calculatorServer.start();
    }
}