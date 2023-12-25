// UserResponse.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.projectzeus.models.response;
import java.util.List;

public class UserResponse {
    private String accessToken;
    private String tokenType;
    private User user;

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String value) { this.accessToken = value; }

    public String getTokenType() { return tokenType; }
    public void setTokenType(String value) { this.tokenType = value; }

    public User getUser() { return user; }
    public void setUser(User value) { this.user = value; }
}

class User {
    private String name;
    private long id;
    private String email;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
