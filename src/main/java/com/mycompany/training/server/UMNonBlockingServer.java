package com.mycompany.training.server;

import com.mycompany.training.implement.UserInfoImpl;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.transport.layered.TFramedTransport;

public class UMNonBlockingServer {
    TServer server;
    public UMNonBlockingServer() throws TTransportException {
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
        new UMNonBlockingServer();
    }
}
