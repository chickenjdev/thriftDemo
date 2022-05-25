package com.mycompany.training.client;

import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

public class UserNonBClient {
    public UserNonBClient() throws Exception {
        nonblockingSocket();
    }
    private void nonblockingSocket() throws Exception {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 3032));   //Non-blocking
        TProtocol protocol = new TBinaryProtocol(transport);
        UserManager.Client client = new UserManager.Client(protocol);
        transport.open();
        int i = 5;
        while (i > 0) {
            System.out.println("The client call returns:" + client.login("khoinguyen1", "123123"));
            i--;
        }
        transport.close();
    }

    public static void main(String[] args) throws Exception {
        new UserNonBClient();
    }
}
