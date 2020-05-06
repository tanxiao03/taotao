package com.taotao.search.service;

import com.taotao.pojo.SearchResult;
import com.taotao.pojo.TaotaoResult;

public interface SearchService {
    /**
     * solr一键初始化
     * @return
     */
    TaotaoResult importSolr();

    /**
     * 根据页面传递的条件进行商品的搜索
     * @param query 页面传递的条件
     * @param page 当前页码
     * @return
     */
    SearchResult findItemSearch(String query, Integer page);
}
