package com.bjlx.QinShihuang.core;


import java.util.List;
import com.bjlx.QinShihuang.model.misc.Column;
import com.bjlx.QinShihuang.model.misc.Sequence;
import com.bjlx.QinShihuang.utils.MorphiaFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 * ��ҳ��Ӫ����ʵ��
 * Created by gaomin on 2016/11/6.
 */
public class ColumnAPI {

    /**
     * ȡ�����ݿ����
     */
    private static Datastore ds = MorphiaFactory.getInstance();

    /**
     * ȡ��ר��
     * @return ר���б�
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
