package com.mycompany.training.client;

import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class UserClient1 {
    public static void main(String[] args) throws TException {
        TTransport transport = new TSocket("localhost", 3030);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        UserManager.Client client = new UserManager.Client(protocol);

//        UserInfo userInfo = new UserInfo("khoinguyen2","Khoi Nguyen 2","Huynh Tan Phat",22);
//        boolean result = client.createUser(userInfo.getUsername(),"123123", userInfo);
//        System.out.printf("create new user : " + result);

//        String result = client.login("khoinguyen1","123123");
//        System.out.printf("result login : " + result);

//        ResponseData result = client.getInfo("khoinguyen1");
//        System.out.printf("get user info : " + result.toString());

        ResponseData result = client.getUserBySession("457a8235-7820-483d-8a74-e8dbc6b13b50");
        System.out.printf("get userBySessionID : " + result.toString());

//        UserInfo userInfo = new UserInfo("khoinguyen1", "Khoi Nguyen", "HCM District.7", 23);
//        boolean result = client.update(userInfo);
//        System.out.printf("update user info result : " + result);

//        boolean result = client.logout("khoinguyen1");
//        System.out.printf("logout user result : " + result);

        transport.close();
    }
}
