namespace java thrift

include "responseData.thrift"
include "userInfo.thrift"

service UserManager {

   responseData.ResponseData createUser(1:userInfo.UserInfo userInfo, 2:string password),

   responseData.ResponseData login(1:string username, 2:string password),

   responseData.ResponseData logout(1:string sessionId),

   responseData.ResponseData getUserBySession(1:string sessionId),

   responseData.ResponseData getInfo(1:string username),

   responseData.ResponseData update(1:userInfo.UserInfo userInfo)

}

