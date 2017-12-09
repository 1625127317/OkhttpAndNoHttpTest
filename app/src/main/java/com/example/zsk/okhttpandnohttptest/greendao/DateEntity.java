package com.example.zsk.okhttpandnohttptest.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ZSK
 * @date 2017/12/9
 * @function
 */

@Entity
public class DateEntity {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "userName")
    private String username;
    @Property(nameInDb = "password")
    private String password;
    @Generated(hash = 1902539469)
    public DateEntity(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    @Generated(hash = 894995106)
    public DateEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
