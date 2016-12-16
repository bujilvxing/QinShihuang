package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.geo.LocalityBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.geo.LocalityFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.*;
import com.bjlx.QinShihuang.model.activity.Activity;
import com.bjlx.QinShihuang.model.geo.DetailsEntry;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.misc.Address;
import com.bjlx.QinShihuang.model.misc.Contact;
import com.bjlx.QinShihuang.model.poi.*;
import com.bjlx.QinShihuang.model.specialservice.RentCar;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * poi核心实现
 * Created by pengyt on 2016/12/4.
 */
public class PoiAPI {


    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得宾馆列表
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 宾馆列表
     * @throws Exception 异常
     */
    public static String getHotels(Integer offset, Integer limit) throws Exception {
        Query<Hotel> query = ds.createQuery(Hotel.class).offset(offset).limit(limit);
        try {
            List<Hotel> hotels = query.asList();
            if(hotels == null || hotels.isEmpty())
                return QinShihuangResult.ok(HotelBasicFormatter.getMapper().valueToTree(new ArrayList<Hotel>()));
            else
                return QinShihuangResult.ok(HotelBasicFormatter.getMapper().valueToTree(hotels));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得宾馆详情
     * @param hotelId 宾馆id
     * @return 宾馆详情
     * @throws Exception 异常
     */
    public static String getHotelById(String hotelId) throws Exception {
        Query<Hotel> query = ds.createQuery(Hotel.class).field(Hotel.fd_id).equal(new ObjectId(hotelId));
        try {
            Hotel hotel = query.get();
            if(hotel == null)
                return QinShihuangResult.getResult(ErrorCode.HOTEL_NOT_EXIST_1022);
            else
                return QinShihuangResult.ok(HotelFormatter.getMapper().valueToTree(hotel));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得目的地列表
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 目的地列表
     * @throws Exception 异常
     */
    public static String getLocalities(Integer offset, Integer limit) throws Exception {
        Query<Locality> query = ds.createQuery(Locality.class).offset(offset).limit(limit);
        try {
            List<Locality> localities = query.asList();
            if(localities == null || localities.isEmpty())
                return QinShihuangResult.ok(LocalityBasicFormatter.getMapper().valueToTree(new ArrayList<Locality>()));
            else
                return QinShihuangResult.ok(LocalityBasicFormatter.getMapper().valueToTree(localities));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得目的地详情
     * @param localityId 目的地id
     * @return 目的地详情
     * @throws Exception 异常
     */
    public static String getLocalityById(String localityId) throws Exception {
        Query<Locality> query = ds.createQuery(Locality.class).field(Locality.fd_id).equal(new ObjectId(localityId));
        try {
            Locality locality = query.get();
            if(locality == null)
                return QinShihuangResult.getResult(ErrorCode.LOCALITY_NOT_EXIST_1024);
            else
                return QinShihuangResult.ok(LocalityFormatter.getMapper().valueToTree(locality));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得景点列表
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 景点列表
     * @throws Exception 异常
     */
    public static String getViewspots(Integer offset, Integer limit) throws Exception {
        Query<Viewspot> query = ds.createQuery(Viewspot.class).offset(offset).limit(limit);
        try {
            List<Viewspot> viewspots = query.asList();
            if(viewspots == null || viewspots.isEmpty())
                return QinShihuangResult.ok(ViewspotBasicFormatter.getMapper().valueToTree(new ArrayList<Hotel>()));
            else
                return QinShihuangResult.ok(ViewspotBasicFormatter.getMapper().valueToTree(viewspots));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得景点详情
     * @param viewspotId 景点id
     * @return 景点详情
     * @throws Exception 异常
     */
    public static String getViewspotById(String viewspotId) throws Exception {
        Query<Viewspot> query = ds.createQuery(Viewspot.class).field(Viewspot.fd_id).equal(new ObjectId(viewspotId));
        try {
            Viewspot viewspot = query.get();
            if(viewspot == null)
                return QinShihuangResult.getResult(ErrorCode.VIEWSPOT_NOT_EXIST_1026);
            else
                return QinShihuangResult.ok(ViewspotFormatter.getMapper().valueToTree(viewspot));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得美食列表
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 美食列表
     * @throws Exception 异常
     */
    public static String getRestaurants(Integer offset, Integer limit) throws Exception {
        Query<Restaurant> query = ds.createQuery(Restaurant.class).offset(offset).limit(limit);
        try {
            List<Restaurant> restaurants = query.asList();
            if(restaurants == null || restaurants.isEmpty())
                return QinShihuangResult.ok(RestaurantBasicFormatter.getMapper().valueToTree(new ArrayList<Restaurant>()));
            else
                return QinShihuangResult.ok(RestaurantBasicFormatter.getMapper().valueToTree(restaurants));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得美食详情
     * @param restaurantId 美食id
     * @return 美食详情
     * @throws Exception 异常
     */
    public static String getRestaurantById(String restaurantId) throws Exception {
        Query<Restaurant> query = ds.createQuery(Restaurant.class).field(Restaurant.fd_id).equal(new ObjectId(restaurantId));
        try {
            Restaurant restaurant = query.get();
            if(restaurant == null)
                return QinShihuangResult.getResult(ErrorCode.RESTAURANT_NOT_EXIST_1028);
            else
                return QinShihuangResult.ok(RestaurantFormatter.getMapper().valueToTree(restaurant));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得购物列表
     * @param offset 第几个开始取
     * @param limit 取多少个
     * @return 购物列表
     * @throws Exception 异常
     */
    public static String getShoppings(Integer offset, Integer limit) throws Exception {
        Query<Shopping> query = ds.createQuery(Shopping.class).offset(offset).limit(limit);
        try {
            List<Shopping> shoppings = query.asList();
            if(shoppings == null || shoppings.isEmpty())
                return QinShihuangResult.ok(ShoppingBasicFormatter.getMapper().valueToTree(new ArrayList<Shopping>()));
            else
                return QinShihuangResult.ok(ShoppingBasicFormatter.getMapper().valueToTree(shoppings));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 取得购物详情
     * @param shoppingId 购物id
     * @return 购物详情
     * @throws Exception 异常
     */
    public static String getShoppingById(String shoppingId) throws Exception {
        Query<Shopping> query = ds.createQuery(Shopping.class).field(Shopping.fd_id).equal(new ObjectId(shoppingId));
        try {
            Shopping shopping = query.get();
            if(shopping == null)
                return QinShihuangResult.getResult(ErrorCode.SHOPPING_NOT_EXIST_1030);
            else
                return QinShihuangResult.ok(ShoppingFormatter.getMapper().valueToTree(shopping));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 数据
     */
    public static String addHotel() {
        Contact contact = new Contact(Arrays.asList("010-62737359"), Arrays.asList("13811111111"), "1234213123", "mofashi", "312413123", "010-62737358", "3813231231@qq.com", "www.bjlx.com");
        Description description = new Description("客栈描述", "客栈详情", "客栈提示", "到达客栈交通方式");
        Address address = new Address("江西省", "南昌市", "昌北区", "机场路16号", "333133");
        Locality locality = new Locality(new ObjectId(), "南昌市", "NanChang", AccountAPI.defaultUserAvatar);
        RentCar rentCar = new RentCar(new ObjectId(), 100, true, 10, true);
        AvailableDay availableDay = new AvailableDay(System.currentTimeMillis(), true, 100);
        Hotel hotel = new Hotel(new ObjectId(), 160.0, 170.0, AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), 1,
                0.98, 0.99, contact, "不羁客栈", "BuJiLvXing Hotel", "http://xxx", 400, 200, "双十二特惠", "9:00~21:00", description, Arrays.asList("不羁", "客栈"),
                Arrays.asList("不羁家"), Arrays.asList(""), "bjlx", "http://xxx", address, Arrays.asList(locality), 100, 0.98f, rentCar, locality, Arrays.asList(availableDay));
        ds.save(hotel);
        return "{\"msg\":\"success\"}";
    }

    public static String addViewspot() {
        Contact contact = new Contact(Arrays.asList("010-62737359"), Arrays.asList("13811111111"), "1234213123", "mofashi", "312413123", "010-62737358", "3813231231@qq.com", "www.bjlx.com");
        Description description = new Description("景点描述", "景点详情", "景点提示", "到达景点交通方式");
        Address address = new Address("江西省", "九江市", "庐山区", "牯岭镇16号", "333133");
        Locality locality = new Locality(new ObjectId(), "九江市", "JiuJiang", AccountAPI.defaultUserAvatar);
        Viewspot viewspot = new Viewspot(new ObjectId(), 160.0, 170.0, AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), 1,
                0.98, 0.99, contact, "庐山", "Lu Shan", "http://xxx", 160, "双十二特惠", "9:00~21:00", description, Arrays.asList("庐山", "山"),
                Arrays.asList("不羁家"), "bjlx", "http://xxx", address, Arrays.asList(locality), locality, 0.88f, 180);
        ds.save(viewspot);
        return "{\"msg\":\"success\"}";
    }

    public static String addShopping() {
        Contact contact = new Contact(Arrays.asList("010-62737359"), Arrays.asList("13811111111"), "1234213123", "mofashi", "312413123", "010-62737358", "3813231231@qq.com", "www.bjlx.com");
        Description description = new Description("购物描述", "购物详情", "购物提示", "到达购物交通方式");
        Address address = new Address("江西省", "九江市", "庐山区", "优衣库", "333133");
        Locality locality = new Locality(new ObjectId(), "九江市", "JiuJiang", AccountAPI.defaultUserAvatar);
        Shopping shopping = new Shopping(new ObjectId(), 160.0, 170.0, AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), 1,
                0.98, 0.99, contact, "优衣库", "UNIQLO", "http://xxx", 160, 180, "双十二特惠", "9:00~21:00", description, Arrays.asList("服装", "优衣库"),
                Arrays.asList("不羁家"), "bjlx", "http://xxx", address, Arrays.asList(locality), locality, 0.88f);
        ds.save(shopping);
        return "{\"msg\":\"success\"}";
    }

    public static String addRestaurant() {
        Contact contact = new Contact(Arrays.asList("010-62737359"), Arrays.asList("13811111111"), "1234213123", "mofashi", "312413123", "010-62737358", "3813231231@qq.com", "www.bjlx.com");
        Description description = new Description("美食描述", "美食详情", "美食提示", "到达美食交通方式");
        Address address = new Address("江西省", "九江市", "庐山区", "优衣库", "333133");
        Locality locality = new Locality(new ObjectId(), "九江市", "JiuJiang", AccountAPI.defaultUserAvatar);
        Restaurant restaurant = new Restaurant(new ObjectId(), 160.0, 170.0, AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), 1,
                0.98, 0.99, contact, "季季红", "JiJiHong", "http://xxx", 34, 50, "双十二特惠", "9:00~21:00", description, Arrays.asList("火锅", "季季红"),
                Arrays.asList("季季红火锅"), "bjlx", "http://xxx", address, Arrays.asList(locality), locality);
        ds.save(restaurant);
        return "{\"msg\":\"success\"}";
    }


    public static String addLocality() {
        List<DetailsEntry> remoteTraffic = Arrays.asList(new DetailsEntry(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), "非本市交通", "打飞机过来吧"));
        List<DetailsEntry> localTraffic = Arrays.asList(new DetailsEntry(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), "本地交通", "公交602或者打的"));
        List<DetailsEntry> cuisines = Arrays.asList(new DetailsEntry(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), "特色菜系", "黄芽头炒春不老"));
        List<Activity> activities = Arrays.asList(new Activity(new ObjectId().toString(), "活动1", "音乐", "摇滚", 1, AccountAPI.defaultUserAvatar, true));
        List<DetailsEntry> geoHistory = Arrays.asList(new DetailsEntry(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), "历史文化", "望庐山瀑布"));
        List<DetailsEntry> specials = Arrays.asList(new DetailsEntry(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), "城市亮点", "庐山"));
        List<DetailsEntry> tips = Arrays.asList(new DetailsEntry(AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar), "城市提醒", "小心骗子"));
        Locality locality = new Locality(new ObjectId(), "牯岭镇", "GuLing Zhen", AccountAPI.defaultUserAvatar, Arrays.asList(AccountAPI.defaultUserAvatar, AccountAPI.defaultGroupAvatar),
                160.0, 170.0, 1, remoteTraffic, localTraffic, "购物简介", "美食简介", cuisines, activities, tips, geoHistory, specials, 0.98, 0.99,
                Arrays.asList("庐山", "九江").stream().collect(Collectors.toSet()), "庐山风景秀丽", "4~10月", "7天左右", 7 * 24);
        ds.save(locality);
        return "{\"msg\":\"success\"}";
    }

}
