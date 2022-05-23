package com.mycompany.training;

import com.mycompany.training.thrift.UserInfo;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class UserClient {
    public static void main(String[] args) throws TException {
        TTransport transport = new TSocket("localhost", 3030);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        UserManager.Client client = new UserManager.Client(protocol);

//        boolean result = client.createUser("khoinguyen1","123123");
//        System.out.printf("create new user : " + result);

//        boolean result = client.login("khoinguyen1","123123");
//        System.out.printf("result login : " + result);

//        UserInfo result = client.getInfo("khoinguyen1");
//        System.out.printf("get user info : " + result.toString());

        UserInfo userInfo = new UserInfo("khoinguyen1", "Khoi Nguyen", "HCM D7", 23);
        boolean result = client.update(userInfo);
        System.out.printf("update user info result : " + result);


        transport.close();
    }
}
