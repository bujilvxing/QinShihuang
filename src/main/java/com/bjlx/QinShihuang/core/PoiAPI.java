package com.bjlx.QinShihuang.core;

import com.bjlx.QinShihuang.core.formatter.geo.LocalityBasicFormatter;
import com.bjlx.QinShihuang.core.formatter.geo.LocalityFormatter;
import com.bjlx.QinShihuang.core.formatter.poi.*;
import com.bjlx.QinShihuang.model.geo.Locality;
import com.bjlx.QinShihuang.model.poi.Hotel;
import com.bjlx.QinShihuang.model.poi.Restaurant;
import com.bjlx.QinShihuang.model.poi.Shopping;
import com.bjlx.QinShihuang.model.poi.Viewspot;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
import java.util.List;

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
                return QinShihuangResult.getResult(ErrorCode.HOTEL_NOT_EXIST_1021);
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
                return QinShihuangResult.getResult(ErrorCode.LOCALITY_NOT_EXIST_1023);
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
                return QinShihuangResult.getResult(ErrorCode.VIEWSPOT_NOT_EXIST_1025);
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
                return QinShihuangResult.getResult(ErrorCode.RESTAURANT_NOT_EXIST_1027);
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
                return QinShihuangResult.getResult(ErrorCode.SHOPPING_NOT_EXIST_1029);
            else
                return QinShihuangResult.ok(ShoppingFormatter.getMapper().valueToTree(shopping));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}