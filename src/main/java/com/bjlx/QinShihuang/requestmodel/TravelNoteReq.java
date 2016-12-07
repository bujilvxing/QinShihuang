package com.bjlx.QinShihuang.requestmodel;

import com.bjlx.QinShihuang.model.misc.ImageItem;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 游记参数
 * Created by xiaozhi on 2016/12/7.
 */
public class TravelNoteReq {

    /**
     * 封面图
     */
    @NotNull
    private ImageItem cover;

    /**
     * 图集
     */
    private List<ImageItem> images;

    /**
     * 游记标题
     */
    @NotBlank
    private String title;

    /**
     * 出游的时间
     */
    @NotNull
    private Long travelTime;

    /**
     * 游记摘要
     */
    @NotBlank
    private String summary;

    /**
     * 游记正文
     */
    private List<Map<String, String>> contents;


}
