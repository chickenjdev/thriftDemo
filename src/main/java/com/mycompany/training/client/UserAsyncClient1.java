package com.mycompany.training.client;

import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

import java.io.IOException;

public class UserAsyncClient1 {
    public UserAsyncClient1() throws TException, IOException {

        TSocket socket = new TSocket("localhost", 3031,20000);
        TTransport framedTransport = new TFramedTransport(socket);
        framedTransport.open();
        TProtocol protocol = new TBinaryProtocol(framedTransport);

        UserManager.Client client = new UserManager.Client(protocol);
        new Thread(() -> {
            try {
                ResponseData result = client.getUserBySession("4802c84c-240f-4352-9b62-7b557489ce8a");
                System.out.println("get userBySessionID : " + result.toString());
            } catch (TException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void main(String[] args) throws TException, IOException {
        new UserAsyncClient1();
    }
}
