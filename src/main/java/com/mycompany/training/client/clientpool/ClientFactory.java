package com.mycompany.training.client.clientpool;

import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.protocol.TProtocol;

public interface ClientFactory {
    UserManager.Client newClient(TProtocol protocol);
}