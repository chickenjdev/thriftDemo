package com.mycompany.training.client.clientpool;

import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.protocol.TProtocol;

public class ServiceClientFactory implements ClientFactory{
    @Override
    public UserManager.Client newClient(TProtocol protocol) {
        return new UserManager.Client(protocol);
    }
}
