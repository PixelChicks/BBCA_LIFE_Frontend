package com.bbcalifeFrontend.bbcalifeFrontend.controller;

import com.bbcalifeFrontend.bbcalifeFrontend.client.ArticleClient;
import com.bbcalifeFrontend.bbcalifeFrontend.model.response.ArticleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ArticlesController {

    private final ArticleClient articleClient;

    @GetMapping("/home")
    public String getArticles(Model model) {
        List<ArticleResponse> articles = articleClient.getAllDeletedAtNull();
        model.addAttribute("articles", articles);
        return "index";
    }
}
