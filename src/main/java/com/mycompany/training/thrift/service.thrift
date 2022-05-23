struct UserInfo {
  1:string username,
  2:string name,
  3:string address,
  4:i32 age
}

service UserManager {

   bool createUser(1:string username, 2:string password),

   bool login(1:string username, 2:string password),

   UserInfo getInfo(1:string username),

   bool update(1:UserInfo userInfo)

}

