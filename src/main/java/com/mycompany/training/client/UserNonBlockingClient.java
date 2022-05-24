package com.mycompany.training.client;

import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserInfo;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;

public class UserNonBlockingClient {
    public UserNonBlockingClient() throws TException, IOException {
        TNonblockingTransport transport = new TNonblockingSocket("localhost", 3031);
        transport.open();
        UserManager.AsyncClient client = new UserManager.AsyncClient(new TBinaryProtocol.Factory(), new TAsyncClientManager(), transport);
        UserInfo userInfo = new UserInfo("khoinguyen3", "Khoi Nguyen 3", "1111 Huynh Tan Phat ", 22, null);

        client.createUser(userInfo, "123123", new AsyncMethodCallback<ResponseData>() {
            @Override
            public void onComplete(ResponseData responseData) {

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
}
