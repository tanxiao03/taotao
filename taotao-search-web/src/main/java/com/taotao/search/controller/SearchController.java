package com.taotao.search.controller;

import com.taotao.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 使用solr进行搜索（solr搜索是指将数据库中的数据放入solr文档库中，进行搜索的时候直接从文档库中搜索）
     * @param query
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String showSearch(@RequestParam("q") String query, @RequestParam(value = "page",defaultValue = "1") Integer page, Model model){
        SearchResult result = searchService.findItemSearch(query,page);
        model.addAttribute("query",query);
        model.addAttribute("totalPages",result.getTotalPages());
        model.addAttribute("totalCount",result.getTotalCount());
        model.addAttribute("itemList",result.getItemList());
        model.addAttribute("page",page);
        return "search";
    }
}
