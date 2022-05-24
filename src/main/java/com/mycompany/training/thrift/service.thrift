struct UserInfo {
  1:string username,
  2:string name,
  3:string address,
  4:i32 age
}

struct ResponseData {
    1:i32 error,
    2:string errorDesc,
    3:UserInfo userInfo
}

service UserManager {

   bool createUser(1:string username, 2:string password, 3:UserInfo userInfo),

   string login(1:string username, 2:string password),

   bool logout(1:string sessionId),

   ResponseData getUserBySession(1:string sessionId),

   ResponseData getInfo(1:string username),

   bool update(1:UserInfo userInfo)

}

