package com.bjlx.QinShihuang.utils;

import java.util.Arrays;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.LoggerFactory;

import com.bjlx.QinShihuang.model.Sequence;
import com.bjlx.QinShihuang.model.Sms;
//import com.bjlx.core.model.account.Credential;
//import com.bjlx.core.model.account.Favorite;
//import com.bjlx.core.model.account.UserInfo;
//import com.bjlx.core.model.account.Vote;
//import com.bjlx.core.model.activity.Activity;
//import com.bjlx.core.model.activity.Ticket;
//import com.bjlx.core.model.comment.Comment;
//import com.bjlx.core.model.geo.Locality;
//import com.bjlx.core.model.guide.Guide;
//import com.bjlx.core.model.im.Chatgroup;
//import com.bjlx.core.model.im.Conversation;
//import com.bjlx.core.model.im.Message;
//import com.bjlx.core.model.im.Post;
//import com.bjlx.core.model.im.Relationship;
//import com.bjlx.core.model.marketplace.Commodity;
//import com.bjlx.core.model.misc.Card;
//import com.bjlx.core.model.misc.Column;
//import com.bjlx.core.model.misc.Feedback;
//import com.bjlx.core.model.misc.TravelNote;
//import com.bjlx.core.model.misc.ValidationCode;
//import com.bjlx.core.model.poi.Hotel;
//import com.bjlx.core.model.poi.Restaurant;
//import com.bjlx.core.model.poi.Shopping;
//import com.bjlx.core.model.poi.Viewspot;
//import com.bjlx.core.model.quora.Question;
//import com.bjlx.core.model.specialservice.Car;
//import com.bjlx.core.model.specialservice.RentCar;
//import com.bjlx.core.model.timeline.Moment;
//import com.bjlx.core.model.trace.Trace;
//import com.bjlx.core.model.tripplan.TripPlan;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MorphiaFactory {

	/**
	 * 懒加载MorphiaFactory对象
	 * @author xiaozhi
	 *
	 */
	private static class MorphiaFactoryHolder {
		private static final MorphiaFactory morphiaFactory = new MorphiaFactory();
	}

	/**
	 * 默认构造方法
	 */
	private MorphiaFactory(){}

	/**
	 * 取得数据库
	 * @return
	 */
	public static final Datastore getInstance() {
		return MorphiaFactoryHolder.morphiaFactory.getDatastore();
	}


    /**
     * 取得Morphia
     * @return
     */
    private final Morphia getMorphia() {
    	final Morphia morphia = new Morphia();
    	morphia.map(Sms.class, Sequence.class);
//    	morphia.map(UserInfo.class, Credential.class, Favorite.class, Vote.class, Ticket.class, Activity.class, 
//    			Comment.class, Locality.class, Guide.class, Chatgroup.class, Conversation.class, Message.class,
//    			Post.class, Relationship.class, Commodity.class, Card.class, Column.class, Feedback.class,
//    			TravelNote.class, ValidationCode.class, Hotel.class, Restaurant.class, Shopping.class, Viewspot.class,
//    			Question.class, Car.class, RentCar.class, Moment.class, Trace.class, TripPlan.class, Sms.class, 
//    			Sequence.class);
        return morphia;
    }

    /**
     * 取得Mongo客户端
     * @return
     */
    private final MongoClient getClient() {
    	// 主机地址
    	String host = "192.168.1.128";
    	// 端口
    	int port = 27017;
    	// 服务器地址
    	ServerAddress serverAddress = null;
    	serverAddress = new ServerAddress(host, port);
    	// 数据库名称
    	String db_name = "QinShihuang";
//    	// 用户名
//    	String username = "xiaoyao";
//    	// 密码
//    	String password = "iloveyou";
    	// 建立凭证
    	List<MongoCredential> credentials = Arrays.asList();
    	//MongoCredential.createCredential(username, db_name, password.toCharArray()));

    	// 打印日志
    	LoggerFactory.getLogger(MorphiaFactory.class).info(String.format("Connected to MongoDB: dbName=%s", db_name));
    	MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder().connectTimeout(60000).socketTimeout(10000).connectionsPerHost(50).threadsAllowedToBlockForConnectionMultiplier(50).build();

    	// 构建mongo客户端
    	if(credentials.isEmpty())
    		return new MongoClient(serverAddress, mongoClientOptions);
    	else
    		return new MongoClient(serverAddress, credentials, mongoClientOptions);
    }

    /**
     * 取得数据库
     * @return
     */
    private final Datastore getDatastore() {
    	String db_name = "QinShihuang";
    	final Datastore datastore = getMorphia().createDatastore(getClient(), db_name);
    	datastore.ensureIndexes();
    	datastore.ensureCaps();
    	return datastore;
    }
}
