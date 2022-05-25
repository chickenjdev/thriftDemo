package com.mycompany.training.server;

import com.mycompany.training.implement.UserInfoImpl;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

public class UMThreadPoolServer {
    TServer server;
    public UMThreadPoolServer() throws TTransportException {
        System.out.println("UserInfoServiceDemo TThreadPoolServer start ....");
        UserManager.Processor processor = new UserManager.Processor(new UserInfoImpl());
        TServerSocket serverTransport = new TServerSocket( 3031);
        TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
        ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
        ttpsArgs.transportFactory( new TFramedTransport.Factory());
        ttpsArgs.processorFactory(new TProcessorFactory(processor));
        server = new TThreadPoolServer(ttpsArgs);
        server.serve();
    }

    public static void main(String[] args) throws TTransportException {
        new UMThreadPoolServer();
    }
}
