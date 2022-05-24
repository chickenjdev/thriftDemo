package com.mycompany.training.thrift;

import com.mycompany.training.MongoConnector;
import io.vertx.core.json.JsonObject;
import org.apache.thrift.TException;

public class UserInfoImpl implements com.mycompany.training.thrift.UserManager.Iface {
    MongoConnector mongoConnector = new MongoConnector();

    @Override
    public boolean createUser(String username, String password, UserInfo userInfo) {
        return mongoConnector.insertData(username, password, userInfo);
    }

    @Override
    public String login(String username, String password) {
        return mongoConnector.login(username, password);
    }

    @Override
    public boolean logout(String username) throws TException {
        return mongoConnector.logout(username);
    }

    @Override
    public ResponseData getUserBySession(String sessionId) throws TException {
        ResponseData responseData = new ResponseData();
        JsonObject joUserInfo = mongoConnector.getUserBySession(sessionId);
        if (joUserInfo.isEmpty()) {
            responseData.setError(440);
            responseData.setErrorDesc("Session expired");
            return responseData;
        }
        responseData.setUserInfo(joUserInfo.mapTo(UserInfo.class));
        responseData.setError(0);
        responseData.setErrorDesc("Success");
        return responseData;
    }

    @Override
    public ResponseData getInfo(String username) throws TException {
        ResponseData responseData = new ResponseData();
        JsonObject joUserInfo = mongoConnector.readData(username, "");
        if (joUserInfo == null || joUserInfo.isEmpty()) {
            responseData.setError(-67);
            responseData.setErrorDesc("User not found");
            return responseData;
        }
        responseData.setUserInfo(joUserInfo.mapTo(UserInfo.class));
        responseData.setError(0);
        responseData.setErrorDesc("Success");
        return responseData;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        return mongoConnector.updateData(userInfo.getUsername(), userInfo.getName(), userInfo.getAddress(), userInfo.getAge());
    }

//    @Override
//    public boolean createUser(String username, String password) {
//        return mongoConnector.insertData(username, password, null, null, 0);
//    }
//
//    @Override
//    public boolean login(String username, String password) {
//        return !mongoConnector.readData(username, password).isEmpty();
//    }
//
//    @Override
//    public UserInfo getInfo(String username) {
//        JsonObject joUserInfo =  mongoConnector.readData(username, "").getJsonObject(0);
//        return joUserInfo.mapTo(UserInfo.class);
//    }
//
//    @Override
//    public boolean update(UserInfo userInfo) {
//        return mongoConnector.updateData(userInfo.getUsername(), userInfo.getName(), userInfo.getAddress(), userInfo.getOld());
//    }
}
