package com.three.order.orderapi.api;

import com.three.order.orderapi.result.OrderResult;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:13
 * @Descripton:
 * @Modify :
 **/
public interface ISearchService {
    OrderResult search(String queryString, Integer page);
}
