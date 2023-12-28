// UserResponse.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.projectzeus.models.response;
import java.util.List;

public class UserResponse {
    private String access_token;
    private String token_type;
    private User user;

    public String getAccessToken() { return access_token; }
    public void setAccessToken(String value) { this.access_token = value; }

    public String getTokenType() { return token_type; }
    public void setTokenType(String value) { this.token_type = value; }

    public User getUser() { return user; }
    public void setUser(User value) { this.user = value; }
    public Long getUserId() {
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }
    public String getUserName() {
        if (user != null) {
            return user.getName();
        } else {
            return null;
        }
    }
    public String getUserEmail() {
        if (user != null) {
            return user.getEmail();
        } else {
            return null;
        }
    }
}

class User {
    private String name;
    private long id;
    private String email;

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getId() { return id; }
    public void setid(long value) { this.id = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
