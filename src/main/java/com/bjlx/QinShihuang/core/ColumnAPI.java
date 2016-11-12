package com.bjlx.QinShihuang.core;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bjlx.QinShihuang.core.formatter.misc.ColumnFormatter;
import com.bjlx.QinShihuang.model.misc.Column;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.utils.Constant;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.bjlx.QinShihuang.utils.QinShihuangResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 专栏核心实现
 * Created by gaomin on 2016/11/6.
 */
public class ColumnAPI {

    /**
     * 取得数据库对象��ݿ����
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得专栏
     * @return
     */
    public static String getColumns() throws Exception {
        Query<Column> query = ds.createQuery(Column.class);
        List<Column> result;
        try{
            result = query.asList();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        if (result==null){
            return QinShihuangResult.ok(ColumnFormatter.getMapper().valueToTree(new ArrayList<Column>()));
        }
        else{
            return  QinShihuangResult.ok(ColumnFormatter.getMapper().valueToTree(result));
        }
    }

    /**
     * 首页banner
     * @return
     * @throws Exception
     */
    public static String getBanners() throws Exception{
        Query<Column> query = ds.createQuery(Column.class)
                                .field(Column.fd_columnType).equal(Constant.SLIDE_TYPE_COLUMN);
        List<Column> result;
        try{
            result = query.asList();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        if (result==null){
            return QinShihuangResult.ok(ColumnFormatter.getMapper().valueToTree(new ArrayList<Column>()));
        }
        else{
            return QinShihuangResult.ok(ColumnFormatter.getMapper().valueToTree(result));
        }
    }

}
