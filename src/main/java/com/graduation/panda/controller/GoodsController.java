package com.graduation.panda.controller;

import com.graduation.panda.model.GoodsDetail;
import com.graduation.panda.model.GoodsInfo;
import com.graduation.panda.service.GoodsDetailService;
import com.graduation.panda.service.GoodsInfoService;
import com.graduation.panda.utils.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    GoodsInfoService goodsInfoService;

    @Autowired
    GoodsDetailService goodsDetailService;


    @PostMapping("/goodsList")
    @ResponseBody
    public HttpResult goodsList(@RequestBody GoodsInfo goodsInfo){
        int categoryId = goodsInfo.getCategoryId();
        String keyword = goodsInfo.getKeyword();
        if(categoryId == 0){
            List<GoodsInfo> goods = goodsInfoService.findByKeyword(keyword);
            return HttpResult.ok(goods);
        }else {
            return HttpResult.ok();
        }
    }

    @PostMapping("/loadDetail")
    @ResponseBody
    public HttpResult loadDetail(@RequestBody HashMap map){
        String goodsId = map.get("goodsId").toString();
        if(goodsId != null){
            GoodsDetail detail = goodsDetailService.findByGoodsId(goodsId);
            return HttpResult.ok(detail);
        }else {
            return HttpResult.error("信息错误");
        }

    }

}
