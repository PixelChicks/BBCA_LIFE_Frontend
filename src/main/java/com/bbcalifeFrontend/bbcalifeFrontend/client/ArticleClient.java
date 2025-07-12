package com.bbcalifeFrontend.bbcalifeFrontend.client;

import com.bbcalifeFrontend.bbcalifeFrontend.config.FeignClientConfiguration;
import com.bbcalifeFrontend.bbcalifeFrontend.model.request.ArticleRequest;
import com.bbcalifeFrontend.bbcalifeFrontend.model.response.ArticleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "life-article", url = "${backend.base-url}/articles", configuration = FeignClientConfiguration.class)
public interface ArticleClient {

    @PostMapping("/create")
    ArticleResponse create(@RequestBody ArticleRequest request);

    @PutMapping("/{id}")
    ArticleResponse update(@PathVariable("id") Long id, @RequestBody ArticleRequest request);

    @GetMapping("/{id}")
    ArticleResponse getById(@PathVariable("id") Long id);

    @GetMapping("/all")
    List<ArticleResponse> getAll();

    @GetMapping("/allNotDeleted")
    List<ArticleResponse> getAllDeletedAtNull();

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @GetMapping("/category/{id}")
    List<ArticleResponse> getByCategory(@PathVariable("id") Long id);

    @GetMapping("/visibility/{visibility}")
    List<ArticleResponse> getByVisibility(@PathVariable("visibility") String visibility);

    @GetMapping("/filter")
    List<ArticleResponse> getByCategoryAndVisibility(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("visibility") String visibility
    );

    @GetMapping("/newest-not-deleted")
    List<ArticleResponse> getNewestNotDeleted(@RequestParam("limit") int limit);
}

