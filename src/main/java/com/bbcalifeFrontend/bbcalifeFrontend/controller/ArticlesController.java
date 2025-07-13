package com.bbcalifeFrontend.bbcalifeFrontend.controller;

import com.bbcalifeFrontend.bbcalifeFrontend.client.ArticleClient;
import com.bbcalifeFrontend.bbcalifeFrontend.model.response.ArticleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ArticlesController {
    private final ArticleClient articleClient;

    @GetMapping("/blog")
    public String getArticles(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            Model model) {

        if (search == null || search.isEmpty()) {
            search = null;
        }

        if (category == null || category.isEmpty()) {
            category = null;
        }

        List<ArticleResponse> articles = articleClient.searchArticlesFilter(search, category);
        model.addAttribute("articles", articles);
        model.addAttribute("search", search);
        model.addAttribute("category", category);

        return "/articles/blog";
    }


    @GetMapping("/articles/{id}")
    public String getArticleById(@PathVariable Long id, Model model) {
        ArticleResponse article = articleClient.getById(id);
        model.addAttribute("article", article);
        return "articles/article";
    }
}