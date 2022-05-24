namespace java thrift

include "sessionInfo.thrift"
struct UserInfo {
  1:string username,
  2:string name,
  3:string address,
  4:i32 age,
  5:sessionInfo.SessionInfo sessionInfo
}