package es.source.code.model;

import java.io.Serializable;

/**
 * Created by apple on 2017/10/13.
 */

public class User implements Serializable {
    String userName;
    String password;
    Boolean oldUser;
    Boolean loginState;

    public String getName() {
        return userName;
    }
    public void setName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getOldUser() {
        return oldUser;
    }
    public void setOldUser(boolean oldUser) {
        this.oldUser = oldUser;
    }

    public Boolean getLoginState(){
        return loginState;
    }

    public void setLoginState(boolean a){
        this.loginState = a;
    }
}
