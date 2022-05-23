package com.mycompany.training.thrift;

import com.mycompany.training.MongoConnector;
import io.vertx.core.json.JsonObject;
import org.apache.thrift.TException;

public class UserInfoImpl implements com.mycompany.training.thrift.UserManager.Iface{
    MongoConnector mongoConnector = new MongoConnector();
    @Override
    public boolean createUser(String username, String password) throws TException {
        return mongoConnector.insertData(username, password, null, null, 0);
    }

    @Override
    public boolean login(String username, String password) {
        return !mongoConnector.readData(username, password).isEmpty();
    }

    @Override
    public UserInfo getInfo(String username) throws TException {
        JsonObject joUserInfo =  mongoConnector.readData(username, "").getJsonObject(0);
        UserInfo user = new UserInfo();
        user.setUsername(joUserInfo.getString("username"));
        user.setName(joUserInfo.getString("name"));
        user.setOld(joUserInfo.getInteger("age"));
        user.setAddress(joUserInfo.getString("address"));
//        return userInfo.mapTo(UserInfo.class);
        return user;
    }

    @Override
    public boolean update(UserInfo userInfo) throws TException {
        return mongoConnector.updateData(userInfo.getUsername(), userInfo.getName(), userInfo.getAddress(), userInfo.getOld());
    }
}
