package com.mycompany.training.client;


import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

import java.io.IOException;

public class UserAsyncClient {
    TSocket socket;
    UserManager.Client client;

    public UserAsyncClient() {
        try {
            socket = new TSocket("localhost", 3031, 30000);
            TTransport framedTransport = new TFramedTransport(socket);
            framedTransport.open();
            TProtocol protocol = new TBinaryProtocol(framedTransport);
            client = new UserManager.Client(protocol);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserAsyncClient(String sessionId) throws TException, IOException {
        socket = new TSocket("localhost", 3031, 30000);
        TTransport framedTransport = new TFramedTransport(socket);
        framedTransport.open();
        TProtocol protocol = new TBinaryProtocol(framedTransport);

        client = new UserManager.Client(protocol);
        new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    ResponseData result = client.getUserBySession(sessionId);
                    System.out.println("get userBySessionID : " + result.toString());
                }
            } catch (TException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public UserManager.Client getClient() {
        return client;
    }

    public static void main(String[] args) throws TException, IOException {
        String[] arr = new String[]{
                "1334eaa5-9679-4d0e-8b81-6d36895ec774",
                "4802c84c-240f-4352-9b62-7b557489ce8a",};
        for (int i = 0; i < 1; i++) {
            new UserAsyncClient(arr[i % 2]);
        }

    }
}
