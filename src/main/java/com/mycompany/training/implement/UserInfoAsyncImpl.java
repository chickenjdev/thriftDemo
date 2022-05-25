package com.mycompany.training.implement;

import com.mycompany.training.MongoConnector;
import com.mycompany.training.thrift.ResponseData;
import com.mycompany.training.thrift.UserInfo;
import com.mycompany.training.thrift.UserManager;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

public class UserInfoAsyncImpl implements UserManager.AsyncIface {
    MongoConnector mongoConnector = new MongoConnector();

    @Override
    public void createUser(UserInfo userInfo, String password, AsyncMethodCallback<ResponseData> resultHandler) throws TException {
        ResponseData responseData = new ResponseData();
        responseData.setErrorDesc("Success");
        resultHandler.onComplete(responseData);
    }

    @Override
    public void login(String username, String password, AsyncMethodCallback<ResponseData> resultHandler) throws TException {

    }

    @Override
    public void logout(String sessionId, AsyncMethodCallback<ResponseData> resultHandler) throws TException {

    }

    @Override
    public void getUserBySession(String sessionId, AsyncMethodCallback<ResponseData> resultHandler) throws TException {

    }

    @Override
    public void getInfo(String username, AsyncMethodCallback<ResponseData> resultHandler) throws TException {

    }

    @Override
    public void update(UserInfo userInfo, AsyncMethodCallback<ResponseData> resultHandler) throws TException {

    }
}
