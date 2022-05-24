namespace java thrift

include "userInfo.thrift"

struct ResponseData {
    1:i32 error,
    2:string errorDesc,
    3:userInfo.UserInfo userInfo
}