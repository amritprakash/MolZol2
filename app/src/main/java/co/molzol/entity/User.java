package co.molzol.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * Main Table
 * All User Mapped with UID
 * Created by hp on 19-12-2017.
 */

public class User implements Serializable {

    //Map of UID and UserDetail
    private Map<String, UserDetail> users;

    public Map<String, UserDetail> getUsers() {
        return users;
    }

    public void setUsers(Map<String, UserDetail> users) {
        this.users = users;
    }
}
