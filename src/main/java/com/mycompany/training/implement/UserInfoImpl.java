package com.mycompany.training.implement;

import com.mycompany.training.MongoConnector;
import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserInfo;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;

public class UserInfoImpl implements UserManager.Iface {
    MongoConnector mongoConnector = new MongoConnector();

    @Override
    public ResponseData createUser(UserInfo userInfo, String password) {
        ResponseData responseData = new ResponseData();
        if (mongoConnector.insertData(userInfo.getUsername(), password, userInfo)){
            UserInfo createdUserInfo = mongoConnector.login(userInfo.getUsername(), password);
            if (createdUserInfo == null){
                responseData.setError(-67);
                responseData.setErrorDesc("User not found");
                return responseData;
            }
            responseData.setUserInfo(createdUserInfo);
            responseData.setError(0);
            responseData.setErrorDesc("Success");
            return responseData;
        }
        responseData.setError(-77);
        responseData.setErrorDesc("create new user failed");
        return responseData;
    }

    @Override
    public ResponseData login(String username, String password) {
        ResponseData responseData = new ResponseData();
        UserInfo userInfo = mongoConnector.login(username, password);
        if (userInfo == null){
            responseData.setError(-67);
            responseData.setErrorDesc("User not found");
            return responseData;
        }
        responseData.setUserInfo(userInfo);
        responseData.setErrorDesc("Login success !!!");
        return responseData;
    }

    @Override
    public ResponseData logout(String username) throws TException {
        ResponseData responseData = new ResponseData();
        mongoConnector.logout(username);
        responseData.setError(0);
        responseData.setErrorDesc("Success");
        return responseData;
    }

    @Override
    public ResponseData getUserBySession(String sessionId) throws TException {
        ResponseData responseData = new ResponseData();
        UserInfo userInfo = mongoConnector.getUserBySession(sessionId);
        if (userInfo == null) {
            responseData.setError(440);
            responseData.setErrorDesc("Session expired");
            return responseData;
        }
        responseData.setUserInfo(userInfo);
        responseData.setError(0);
        responseData.setErrorDesc("Success");
        return responseData;
    }

    @Override
    public ResponseData getInfo(String username) throws TException {
        ResponseData responseData = new ResponseData();
        UserInfo userInfo =  mongoConnector.readData(username, "");
        if (userInfo == null) {
            responseData.setError(-67);
            responseData.setErrorDesc("User not found");
            return responseData;
        }
        responseData.setUserInfo(userInfo);
        responseData.setError(0);
        responseData.setErrorDesc("Success");
        return responseData;
    }

    @Override
    public ResponseData update(UserInfo userInfo) {
        ResponseData responseData = new ResponseData();
        boolean update = mongoConnector.updateData(userInfo.getUsername(), userInfo.getName(), userInfo.getAddress(), userInfo.getAge());
        if (!update){
            responseData.setError(-68);
            responseData.setErrorDesc("Update failed");
            return responseData;
        }
        responseData.setUserInfo(userInfo);
        responseData.setError(0);
        responseData.setErrorDesc("Success");
        return responseData;
    }

}
