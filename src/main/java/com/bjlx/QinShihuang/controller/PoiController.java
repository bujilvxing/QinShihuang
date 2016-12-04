package com.bjlx.QinShihuang.controller;

import org.springframework.web.bind.annotation.*;

/**
 * poi控制器
 * Created by pengyt on 2016/12/4.
 */
public class PoiController {

    /**
     * 取得宾馆列表1020
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 宾馆列表
     */
    @RequestMapping(value = "/app/hotels", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getHotels(Integer offset, Integer limit) {

        return null;
    }

    /**
     * 取得宾馆详情1021
     * @param hotelId 宾馆id
     * @return 宾馆详情
     */
    @RequestMapping(value = "/app/hotels/{hotelId:\\[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getHotelById(@PathVariable("hotelId") String hotelId) {

        return null;
    }

    /**
     * 取得目的地列表1022
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 目的地列表
     */
    @RequestMapping(value = "/app/localities", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getLocalities(Integer offset, Integer limit) {

        return null;
    }

    /**
     * 取得目的地详情1023
     * @param localityId 目的地id
     * @return 目的地详情
     */
    @RequestMapping(value = "/app/localities/{localityId:\\[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getLocalityById(@PathVariable("localityId") String localityId) {

        return null;
    }

    /**
     * 取得景点列表1024
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 景点列表
     */
    @RequestMapping(value = "/app/viewspots", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getViewspots(Integer offset, Integer limit) {

        return null;
    }

    /**
     * 取得景点详情1025
     * @param viewspotId 景点id
     * @return 景点详情
     */
    @RequestMapping(value = "/app/viewspots/{viewspotId:\\[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getViewspotById(@PathVariable("viewspotId") String viewspotId) {

        return null;
    }

    /**
     * 取得美食列表1026
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 美食列表
     */
    @RequestMapping(value = "/app/restaurants", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getRestaurants(Integer offset, Integer limit) {

        return null;
    }

    /**
     * 取得美食详情1027
     * @param restaurantId 美食id
     * @return 美食详情
     */
    @RequestMapping(value = "/app/restaurants/{restaurantId:\\[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getRestaurantById(@PathVariable("restaurantId") String restaurantId) {

        return null;
    }

    /**
     * 取得购物列表1028
     * @param offset 从第几个开始取
     * @param limit 取多少个
     * @return 购物列表
     */
    @RequestMapping(value = "/app/shoppings", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getShoppings(Integer offset, Integer limit) {

        return null;
    }

    /**
     * 取得购物详情1029
     * @param shoppingId 购物id
     * @return 购物详情
     */
    @RequestMapping(value = "/app/shoppings/{shoppingId:\\[0-9a-f]{24}}}", method= RequestMethod.GET, produces = "application/json;charset=utf-8")
    public @ResponseBody String getShoppingById(@PathVariable("shoppingId") String shoppingId) {

        return null;
    }
}
