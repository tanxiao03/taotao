package com.taotao.search.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private SolrServer solrServer;

    /**
     * solr一键初始化
     * @return
     */
    @Override
    public TaotaoResult importSolr() {
        List<SearchItem> searchItemAll = tbItemMapper.findSearchItemAll();
            try {
                for (SearchItem searchItem : searchItemAll) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", searchItem.getId());
                    document.addField("item_title", searchItem.getTitle());
                    document.addField("item_sell_point", searchItem.getSellPoint());
                    document.addField("item_price", searchItem.getPrice());
                    document.addField("item_image", searchItem.getImage());
                    document.addField("item_category_name", searchItem.getCategoryName());
                    document.addField("item_desc", searchItem.getItemDesc());
                    solrServer.add(document);
                }
                solrServer.commit();
                return TaotaoResult.build(200,"导入成功");
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return TaotaoResult.build(500,"导入失败");
    }

    @Override
    public SearchResult findItemSearch(String query, Integer page) {
        SearchResult searchResult = new SearchResult();
        try {
            String str = new String(query.getBytes("iso-8859-1"),"UTF-8");
            SolrQuery solrQuery = new SolrQuery();

            //设置用户输入的内容
            solrQuery.setQuery(str);

            //设置默认域 从默认域中搜索
            solrQuery.set("df","item_keywords");

            //设置高亮
            solrQuery.setHighlight(true);
            solrQuery.addHighlightField("item_title");
            solrQuery.setHighlightSimplePre("<font style='color:red'>");
            solrQuery.setHighlightSimplePost("</font>");

            //设置每页显示条数和开始页码数
            solrQuery.setStart((page-1)*60);
            solrQuery.setRows(60);

            //查询后返回的结果集
            QueryResponse queryResponse = solrServer.query(solrQuery);
            //得到文档集合对象
            SolrDocumentList documentList = queryResponse.getResults();

            //通过域名获取域值，得到每个商品的信息，放入list集合中
            List<SearchItem> itemList = new ArrayList<SearchItem>();
            for (SolrDocument document:documentList) {
                SearchItem item = new SearchItem();
                item.setId((String)document.get("id"));
                item.setCategoryName((String)document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((Long) document.get("item_price"));
                item.setSellPoint((String) document.get("item_sell_point"));
                String item_title = "";
                Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
                List<String> list = highlighting.get(document.get("id")).get("item_title");
                if (list != null&&list.size()>0){
                    item_title = list.get(0);
                }else {
                    item_title = (String)document.get("item_title");
                }
                item.setTitle(item_title);
                itemList.add(item);
            }

            //得到总数据条数
            long totalCount = documentList.getNumFound();
            //得到总页数
            long totalPages = (totalCount%60) == 0 ? (totalCount/60) : (totalCount/60) + 1;

            searchResult.setTotalPages(totalPages);
            searchResult.setTotalCount(totalCount);
            searchResult.setItemList(itemList);

            return searchResult;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addSearchItem(SearchItem item) {
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSellPoint());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategoryName());
            document.addField("item_desc", item.getItemDesc());
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
