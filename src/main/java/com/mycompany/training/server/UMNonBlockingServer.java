package com.mycompany.training.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TTransportException;

public class UMNonBlockingServer {
    TServer server;
    public UMNonBlockingServer() throws TTransportException {
//        TNonblockingServerTransport nonblockingServerTransport = new TNonblockingServerSocket(3031);
//        server = new TNonblockingServer(new TNonblockingServer.Args(nonblockingServerTransport)
//                .processor(new UserManager.AsyncProcessor<>(new UserInfoAsyncImpl())));
//
//        System.out.print("Starting the non blocking server... ");
//        new Thread(() -> server.serve()).start();
//
//        System.out.println("done.");
//
//        TNonblockingServerSocket socket = new TNonblockingServerSocket(3031);
////        UserManager.Processor processor = new UserManager.Processor(new U());
//        TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
//        arg.protocolFactory(new TBinaryProtocol.Factory());
//        arg.transportFactory(new TFramedTransport.Factory());
//        arg.processorFactory(new TProcessorFactory(processor));
//        TServer server = new TNonblockingServer (arg);
//
//        server.serve();

    }

    public static void main(String[] args) throws TTransportException {
        new UMNonBlockingServer();
    }
}
