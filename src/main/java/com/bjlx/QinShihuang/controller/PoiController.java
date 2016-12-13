package com.bjlx.QinShihuang.controller;

import com.bjlx.QinShihuang.core.PoiAPI;
import com.bjlx.QinShihuang.utils.ErrorCode;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * poi控制器
 * Created by pengyt on 2016/12/4.
 */
@Controller
public class PoiController {

    /**
     * 取得宾馆列表1021
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 宾馆列表
     */
    @RequestMapping(value = "/app/hotels", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getHotels(Integer offset, Integer limit) {
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return PoiAPI.getHotels(defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得宾馆详情1022
     * @param hotelId 宾馆id
     * @return 宾馆详情
     */
    @RequestMapping(value = "/app/hotels/{hotelId:[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getHotelById(@PathVariable("hotelId") String hotelId) {
        try {
            return PoiAPI.getHotelById(hotelId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得目的地列表1023
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 目的地列表
     */
    @RequestMapping(value = "/app/localities", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getLocalities(Integer offset, Integer limit) {
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return PoiAPI.getLocalities(defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得目的地详情1024
     * @param localityId 目的地id
     * @return 目的地详情
     */
    @RequestMapping(value = "/app/localities/{localityId:[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getLocalityById(@PathVariable("localityId") String localityId) {
        try {
            return PoiAPI.getLocalityById(localityId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得景点列表1025
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 景点列表
     */
    @RequestMapping(value = "/app/viewspots", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getViewspots(Integer offset, Integer limit) {
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return PoiAPI.getViewspots(defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得景点详情1026
     * @param viewspotId 景点id
     * @return 景点详情
     */
    @RequestMapping(value = "/app/viewspots/{viewspotId:[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getViewspotById(@PathVariable("viewspotId") String viewspotId) {
        try {
            return PoiAPI.getViewspotById(viewspotId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得美食列表1027
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 美食列表
     */
    @RequestMapping(value = "/app/restaurants", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getRestaurants(Integer offset, Integer limit) {
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return PoiAPI.getRestaurants(defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得美食详情1028
     * @param restaurantId 美食id
     * @return 美食详情
     */
    @RequestMapping(value = "/app/restaurants/{restaurantId:[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getRestaurantById(@PathVariable("restaurantId") String restaurantId) {
        try {
            return PoiAPI.getRestaurantById(restaurantId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得购物列表1029
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 购物列表
     */
    @RequestMapping(value = "/app/shoppings", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getShoppings(Integer offset, Integer limit) {
        Integer defaultOffset = offset == null ? 0 : offset;
        Integer defaultLimit = limit == null ? 10 : limit;
        try {
            return PoiAPI.getShoppings(defaultOffset, defaultLimit);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }

    /**
     * 取得购物详情1030
     * @param shoppingId 购物id
     * @return 购物详情
     */
    @RequestMapping(value = "/app/shoppings/{shoppingId:[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getShoppingById(@PathVariable("shoppingId") String shoppingId) {
        try {
            return PoiAPI.getShoppingById(shoppingId);
        } catch (Exception e) {
            return QinShihuangResult.getResult(ErrorCode.SERVER_EXCEPTION);
        }
    }
}
