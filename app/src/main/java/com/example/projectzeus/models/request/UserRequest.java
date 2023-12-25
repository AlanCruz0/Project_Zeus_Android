// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.projectzeus.models.request;
import java.util.List;

public class UserRequest {
    private String password;
    private String email;

    public String getPassword() { return password; }

    public UserRequest(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public void setPassword(String value) { this.password = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }
}
