package com.mycompany.training.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.training.thrift.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    RequestUserType type;
    UserInfo userData;
}
