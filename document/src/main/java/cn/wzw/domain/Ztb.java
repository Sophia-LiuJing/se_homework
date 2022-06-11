package cn.wzw.domain;


import java.util.Date;

public class Ztb {
    private String body;
    private Date createTime;

    public Ztb(String body, Date createTime) {
        this.body = body;
        this.createTime = createTime;
    }

    public Ztb() {
    }

    @Override
    public String toString() {
        return "Ztb{" +
                "body='" + body + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
