package com.bjlx.QinShihuang.requestmodel;

/**
 * Created by Nov on 2016/11/22.
 */
public class ChatGroupReq {


    /**
     * Ⱥ��
     */
    private String name;

    /**
     * ������
     */
    private String creator;

    /**
     * ����Ա
     */
    private String [] admin;

    /**
     * ��Ա
     */
    private String [] participants ;

    /**
     * Ⱥ��״̬
     */
    private int status ;

    /**
     * ����Ա��
     */
  //  public final static int maxnum = groupMax ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String [] getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
