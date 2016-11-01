package com.bjlx.QinShihuang.utils;

import java.util.Arrays;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.LoggerFactory;

import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.model.misc.Token;
import com.bjlx.QinShihuang.model.account.Credential;
import com.bjlx.QinShihuang.model.account.Favorite;
import com.bjlx.QinShihuang.model.account.UserInfo;
import com.bjlx.QinShihuang.model.account.Vote;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.activity.Ticket;
import com.bjlx.QinShihuang.model.comment.Comment;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.guide.Guide;
import com.bjlx.QinShihuang.model.im.Chatgroup;
import com.bjlx.QinShihuang.model.im.Conversation;
import com.bjlx.QinShihuang.model.im.Message;
import com.bjlx.QinShihuang.model.im.Post;
import com.bjlx.QinShihuang.model.im.Relationship;
import com.bjlx.QinShihuang.model.marketplace.Commodity;
import com.bjlx.QinShihuang.model.misc.Card;
import com.bjlx.QinShihuang.model.misc.Column;
import com.bjlx.QinShihuang.model.misc.Feedback;
import com.bjlx.QinShihuang.model.misc.TravelNote;
import com.bjlx.QinShihuang.model.misc.ValidationCode;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.model.quora.Question;
import com.bjlx.QinShihuang.model.specialservice.Car;
import com.bjlx.QinShihuang.model.specialservice.RentCar;
import com.bjlx.QinShihuang.model.timeline.Moment;
import com.bjlx.QinShihuang.model.trace.Trace;
import com.bjlx.QinShihuang.model.tripplan.TripPlan;
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
    	morphia.map( 
    			UserInfo.class, Favorite.class, TripPlan.class, Trace.class, Question.class, RentCar.class, Post.class, 
    			Commodity.class, TravelNote.class, Hotel.class, Restaurant.class, Shopping.class, 
    			Moment.class, Locality.class, Viewspot.class, Car.class, Feedback.class, Conversation.class, 
    			Comment.class, Guide.class, Column.class, Card.class, Message.class, Chatgroup.class, 
    			Vote.class, Ticket.class, Activity.class, Credential.class, Relationship.class, 
    			ValidationCode.class, Sequence.class, Token.class);
        return morphia;
    }

    /**
     * 取得Mongo客户端
     * @return
     */
    private final MongoClient getClient() {
    	// 主机地址
    	String host = "192.168.1.128";
//		String host = "127.0.0.1";
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
