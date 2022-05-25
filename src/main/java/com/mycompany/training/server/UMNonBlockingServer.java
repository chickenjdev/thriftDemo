package com.mycompany.training.server;

import com.mycompany.training.implement.UserInfoImpl;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

public class UMNonBlockingServer {
    public UMNonBlockingServer() throws TTransportException {
        nonblockingServer();
    }

    private void nonblockingServer() throws TTransportException {
        TNonblockingServerTransport serverSocket = new TNonblockingServerSocket(3032);
        TNonblockingServer.Args serverParams = new TNonblockingServer.Args(serverSocket);
        serverParams.protocolFactory(new TBinaryProtocol.Factory());
        serverParams.transportFactory(new TFramedTransport.Factory()); //Non-blocking
        serverParams.processor(new UserManager.Processor<UserManager.Iface>(new UserInfoImpl()));
        TServer server = new TNonblockingServer(serverParams);
        server.serve();
    }

    public static void main(String[] args) throws TTransportException {
        new UMNonBlockingServer();
    }
}
