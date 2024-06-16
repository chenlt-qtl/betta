package com.betta.es.service.impl;

import com.alibaba.fastjson2.JSON;
import com.betta.common.core.domain.entity.SysUser;
import com.betta.common.exception.ServiceException;
import com.betta.common.utils.SecurityUtils;
import com.betta.es.service.IEsHistoryService;
import com.betta.es.service.IEsNoteService;
import com.betta.note.domain.NoteInfo;
import com.betta.note.domain.NoteVo;
import com.betta.note.service.INoteInfoService;
import com.betta.system.service.ISysUserService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.elasticsearch.client.indices.CreateIndexRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.betta.common.utils.PageUtils.startPage;

@Service
public class EsNoteServiceImpl implements IEsNoteService {


    @Qualifier("restHighLevelClient")
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private IEsHistoryService esHistoryService;

    @Autowired
    private INoteInfoService noteInfoService;

    @Autowired
    private ISysUserService userService;

    @Override
    public void createIndex(String username) throws IOException {
        SysUser sysUser = userService.selectUserByUserName(username);
        if(Objects.isNull(sysUser)){
            throw new ServiceException("用户不存在");
        }

        CreateIndexRequest request = new CreateIndexRequest(username);
        //设置请求参数

        String json = "{\n" +
                "    \"mappings\": {\n" +
                "        \"properties\": {\n" +
                "            \"id\": {\n" +
                "                \"type\": \"keyword\"\n" +
                "            },\n" +
                "            \"name\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"copy_to\": \"all\"\n" +
                "            },\n" +
                "            \"text\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\",\n" +
                "                \"copy_to\": \"all\"\n" +
                "            },\n" +
                "            \"all\": {\n" +
                "                \"type\": \"text\",\n" +
                "                \"analyzer\": \"ik_max_word\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        request.source(json, XContentType.JSON);
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

    @Override
    public void insertNoteDoc(Long noteId) {
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setId(noteId);
        List<NoteVo> noteVos = noteInfoService.selectNoteInfoDetailList(noteInfo);
        if(noteVos.isEmpty()){
            throw new ServiceException(String.format("ID为 %d 的笔记不存在",noteId));
        }
        NoteVo noteVo = noteVos.get(0);

        IndexRequest request = new IndexRequest(noteVo.getCreateBy()).id(noteId.toString());
        String json = JSON.toJSONString(noteVo);
        request.source(json, XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            String message = e.getMessage();
            if (!message.contains("created") && !message.contains("200")) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void insertPageNoteDoc(List<NoteVo> list,String userName) {
        BulkRequest bulkRequest = new BulkRequest();
        list.forEach(noteVo -> {
            IndexRequest request = new IndexRequest(userName).id(noteVo.getId().toString());
            String json = JSON.toJSONString(noteVo);
            request.source(json, XContentType.JSON);
            bulkRequest.add(request);
        });

        try {
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            String message = e.getMessage();
            if (!message.contains("created") && !message.contains("200")) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int updateNoteDoc(NoteInfo noteInfo) {
        return 0;
    }

    @Override
    public int deleteNoteDocByIds(Long[] ids) {
        return 0;
    }

    /**
     * 根据关键词搜索
     * @param key
     * @return
     * @throws IOException
     */
    @Override
    public List<NoteVo> search(String key) throws IOException {
        List<NoteVo> result = new ArrayList();
        SearchRequest searchRequest = new SearchRequest(SecurityUtils.getUsername());
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("all",key));
        searchRequest.source(builder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        hits.forEach(hit->{
            String sourceAsString = hit.getSourceAsString();
            NoteVo noteVo = JSON.parseObject(sourceAsString, NoteVo.class);
            result.add(noteVo);
        });
        return result;
    }
}
