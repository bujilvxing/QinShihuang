package com.bjlx.QinShihuang.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 自增长序列化器
 * Created by xiaoyao on 15/12/30.
 */
@Entity
public class Sequence {

    public static final String fd_userId = "userId";
    public static final String fd_chatGroupId = "chatGroupId";
    public static final String fd_msgId = "msgId";

    @Id
    private ObjectId id = new ObjectId();
    /**
     * 序列化器的类别
     */
    private String column = "";

    /**
     * 计数器
     */
    private long count = 1L;

    public long getCount() {
        return count;
    }
    

    public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public Sequence(){}
}
