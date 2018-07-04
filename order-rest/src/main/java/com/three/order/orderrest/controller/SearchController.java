package com.three.order.orderrest.controller;

import com.three.order.orderapi.api.ISearchService;
import com.three.order.orderapi.result.OrderResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: luiz
 * @Date: Create in 2018/07/03 19:25
 * @Description:搜索引擎
 */
@RestController
@Api(tags = "搜索",description = "搜索相关api")
public class SearchController {

    @Autowired
    private ISearchService iSearchService;

    @PostMapping("/api/search")
    public OrderResult search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page) {
        OrderResult searchResult = iSearchService.search(queryString, page);
        return searchResult;
    }
}
