package com.bbcalifeFrontend.bbcalifeFrontend.controller;

import com.bbcalifeFrontend.bbcalifeFrontend.client.ArticleCategoryClient;
import com.bbcalifeFrontend.bbcalifeFrontend.client.ArticleClient;
import com.bbcalifeFrontend.bbcalifeFrontend.model.ArticleCategory;
import com.bbcalifeFrontend.bbcalifeFrontend.model.request.ArticleRequest;
import com.bbcalifeFrontend.bbcalifeFrontend.model.response.ArticleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@AllArgsConstructor
public class ArticlesController {
    private final ArticleClient articleClient;
    private final ArticleCategoryClient articleCategoryClient;

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

    @GetMapping("/articles/create")
    public String showCreateForm(Model model) {
        List<ArticleCategory> articleCategories = articleCategoryClient.getAllCategories();
        model.addAttribute("categories", articleCategories);
        model.addAttribute("article", new ArticleResponse());
        return "articles/create";
    }

    @PostMapping("/articles/create")
    public String createArticle(
            @ModelAttribute ArticleRequest article,
            @RequestParam("file") MultipartFile file,
            Model model
    ) {
        try {
            if (!file.isEmpty()) {
                Path uploadDir = Paths.get("uploads/articles");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                String filename = System.currentTimeMillis() + "" + file.getOriginalFilename();
                Path targetPath = uploadDir.resolve(filename);

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                article.setThumbnailPicture(filename);
            }

            articleClient.create(article);

            return "redirect:/blog";
        } catch (Exception e) {
            model.addAttribute("error", "Error while creating article: " + e.getMessage());
            model.addAttribute("categories", articleCategoryClient.getAllCategories());
            return "articles/create";
        }
    }

    @GetMapping("/articles/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ArticleResponse article = articleClient.getById(id);
        List<ArticleCategory> categories = articleCategoryClient.getAllCategories();

        model.addAttribute("article", article);
        model.addAttribute("categories", categories);

        return "articles/edit";
    }

    @PostMapping("/articles/edit/{id}")
    public String editArticle(
            @PathVariable Long id,
            @ModelAttribute ArticleRequest article,
            @RequestParam("file") MultipartFile file,
            Model model
    ) {
        try {
            // Fetch existing article to access current thumbnail if needed
            ArticleResponse existing = articleClient.getById(id);

            if (!file.isEmpty()) {
                Path uploadDir = Paths.get("uploads/articles");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path targetPath = uploadDir.resolve(filename);

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                article.setThumbnailPicture(filename); // ✅ New file
            } else {
                article.setThumbnailPicture(existing.getThumbnailPicture()); // ✅ Keep old file
            }

            articleClient.update(id, article);

            return "redirect:/articles/" + id;
        } catch (Exception e) {
            model.addAttribute("error", "Error updating article: " + e.getMessage());
            model.addAttribute("categories", articleCategoryClient.getAllCategories());
            model.addAttribute("article", articleClient.getById(id));
            return "articles/edit";
        }
    }




}