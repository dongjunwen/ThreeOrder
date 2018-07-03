package com.three.order.orderservice.apiimpl;

import com.three.order.orderapi.api.ISearchService;
import com.three.order.orderapi.result.OrderResult;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/7/3 19:15
 * @Descripton:搜索引擎执行搜索
 * @Modify :
 **/
@Service
public class SearchService implements ISearchService {
    @Override
    public OrderResult search(String queryString, Integer page) {
        return null;
    }
}
