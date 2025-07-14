package com.bbcalifeFrontend.bbcalifeFrontend.client;


import com.bbcalifeFrontend.bbcalifeFrontend.config.FeignClientConfiguration;
import com.bbcalifeFrontend.bbcalifeFrontend.model.ArticleCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "life-article-category", url = "${backend.base-url}/article-categories", configuration = FeignClientConfiguration.class)
public interface ArticleCategoryClient {

    @GetMapping("/{id}")
    ArticleCategory getCategoryById(@PathVariable Long id);

    @GetMapping("/all")
    List<ArticleCategory> getAllCategories();

    @PostMapping("/create")
    ArticleCategory createCategory(@RequestBody ArticleCategory category);

    @PutMapping("/{id}")
    ArticleCategory updateCategory(@PathVariable Long id, @RequestBody ArticleCategory category);

    @DeleteMapping("/{id}")
    Void deleteCategory(@PathVariable Long id);
}


