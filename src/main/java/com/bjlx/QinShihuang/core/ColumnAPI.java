package com.bjlx.QinShihuang.core;


import java.util.List;
import com.bjlx.QinShihuang.model.misc.Column;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * 首页运营核心实现
 * Created by gaomin on 2016/11/6.
 */
public class ColumnAPI {

    /**
     * 取得数据库对象
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * 取得专栏
     * @return 专栏列表
     */
    public static String getColumns() throws Exception {
        List<Column> result;
        Query<Column> query = ds.createQuery(Column.class);
        try{
            result = query.asList();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        ObjectMapper mapper =
    }


}
