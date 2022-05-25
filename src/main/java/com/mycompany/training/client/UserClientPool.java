package com.mycompany.training.client;

import com.mycompany.training.client.clientpool.ServiceClientFactory;
import com.mycompany.training.client.clientpool.ServiceClientPool;
import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TSocket;

public class UserClientPool {
    ServiceClientPool clientPool = new ServiceClientPool(new ServiceClientFactory());
    public UserClientPool(String sessionId) throws TException {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    UserManager.Client client = clientPool.getClient();
                    ResponseData result = client.getUserBySession(sessionId);
                    System.out.println("get userBySessionID : " + result.toString());
                }
            } catch (TException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void main(String[] args) throws TException {
        new UserClientPool("1334eaa5-9679-4d0e-8b81-6d36895ec774");
    }
}
