package com.elasticsearch.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Angus
 * @date 2018/11/24
 */
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private TransportClient client;

    @GetMapping("/")
    public String index() {
        return "ElasticSearch Demo";
    }

    @GetMapping("/book/novel/{id}")
    public ResponseEntity getNovel(@PathVariable("id") String id) {
        GetResponse res = client.prepareGet("book", "novel", id).get();
        if (res.isExists()) {
            return ResponseEntity.ok(res.getSourceAsMap());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/book/novel")
    public ResponseEntity addNovel(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("word_count") int wordCount,
            @RequestParam("publish_date")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate) {
        try {
            XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate.getTime())
                    .endObject();
            IndexResponse res = client.prepareIndex("book", "novel")
                    .setSource(contentBuilder)
                    .get();
            return ResponseEntity.ok(res.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/book/novel/{id}")
    public ResponseEntity deleteNovel(@PathVariable("id") String id) {
        DeleteResponse res = client.prepareDelete("book", "novel", id).get();
        return ResponseEntity.ok(res.getResult());
    }

    @PutMapping("/book/novel")
    public ResponseEntity updateNovel(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "word_count", required = false) Integer wordCount,
            @RequestParam(name = "publish_date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate) {
        UpdateRequest updateRequest = new UpdateRequest("book", "novel", id);
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
            if (title != null) {
                builder.field("title", title);
            }
            if (author != null) {
                builder.field("author", author);
            }
            if (wordCount != null) {
                builder.field("word_count", wordCount);
            }
            if (publishDate != null) {
                builder.field("publish_date", publishDate.getTime());
            }
            builder.endObject();
            updateRequest.doc(builder);
            UpdateResponse res = client.update(updateRequest).get();
            return ResponseEntity.ok(res.getResult());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/query")
    public ResponseEntity query(
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "gt_word_count", defaultValue = "0") int gtWordCount,
            @RequestParam(name = "lt_word_count", required = false) Integer ltWordCount) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (author != null) {
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }
        if (title != null) {
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count")
                .from(gtWordCount);
        if (ltWordCount != null && ltWordCount > 0) {
            rangeQuery.to(ltWordCount);
        }
        boolQuery.filter(rangeQuery);

        SearchRequestBuilder builder = client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery);
        log.info("builder:{}", builder);
        SearchResponse res = builder.get();
        List<Map<String, Object>> sources = new ArrayList<>();
        res.getHits().forEach(hit -> sources.add(hit.getSourceAsMap()));
        return ResponseEntity.ok(sources);
    }
}
