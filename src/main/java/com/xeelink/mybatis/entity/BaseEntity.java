package com.xeelink.mybatis.entity;

import java.util.Date;

/**
 * @author: zhaoyk
 * @date: 2019-3-9 10:46
 */
@data
public class BaseEntity {

    private Integer isdelete;

    private Date lastupdate;

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }
//    private Timestamp meteversion;

    public void preInsert() {
        this.isdelete = 0;
        long now = System.currentTimeMillis();
        this.lastupdate = new Date(now);
//        this.meteversion = new Timestamp(now);
    }

    public void preUpdate() {
        this.isdelete = 0;
        long now = System.currentTimeMillis();
        this.lastupdate = new Date(now);
//        this.meteversion = new Timestamp(now);
    }

    public void preDelete() {
        this.isdelete = 1;
        long now = System.currentTimeMillis();
        this.lastupdate = new Date(now);
//        this.meteversion = new Timestamp(now);
    }
}
