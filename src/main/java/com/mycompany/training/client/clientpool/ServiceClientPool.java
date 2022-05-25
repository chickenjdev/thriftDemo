package com.mycompany.training.client.clientpool;

import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFastFramedTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

import java.util.ArrayList;
import java.util.List;

public class ServiceClientPool implements AutoCloseable {
    private final ThreadLocal clientThreadLocal = new ThreadLocal<>();
    private final ServiceClientFactory clientFactory;
    private final List clients = new ArrayList<>();
    TSocket socket = new TSocket("localhost", 3031, 30000);

    int maxPoolSize = 10;

    UserManager.Client client;

    public ServiceClientPool(ServiceClientFactory clientFactory) throws TTransportException {
        this.clientFactory = clientFactory;
    }

    public UserManager.Client getClient() throws TTransportException {
        client = (UserManager.Client) clientThreadLocal.get();
        if (client == null || !client.getInputProtocol().getTransport().isOpen()) {
            if (clients.size() < maxPoolSize) {
                TTransport framedTransport = new TFramedTransport(socket);
                framedTransport.open();
                TProtocol protocol = new TBinaryProtocol(framedTransport);
                client = clientFactory.newClient(protocol);
                clientThreadLocal.set(client);
                clients.add(client);
            }
        }
        return client;
    }

    public void close() {
        clients.forEach(client -> {
            ((UserManager.Client) client).getInputProtocol().getTransport().close();
            ((UserManager.Client) client).getOutputProtocol().getTransport().close();
        });
    }
}
