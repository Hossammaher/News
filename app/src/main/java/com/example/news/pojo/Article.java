
package com.example.news.pojo;

import com.google.gson.annotations.Expose;

public class Article {

    @Expose
    private String author;
    @Expose
    private Object content;
    @Expose
    private String description;
    @Expose
    private String publishedAt;
    @Expose
    private Source source;
    @Expose
    private String title;
    @Expose
    private String url;
    @Expose
    private String urlToImage;

    public String getAuthor() {
        return author;
    }

    public Object getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public static class Builder {

        private String author;
        private Object content;
        private String description;
        private String publishedAt;
        private Source source;
        private String title;
        private String url;
        private String urlToImage;

        public Article.Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Article.Builder withContent(Object content) {
            this.content = content;
            return this;
        }

        public Article.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Article.Builder withPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        public Article.Builder withSource(Source source) {
            this.source = source;
            return this;
        }

        public Article.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Article.Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Article.Builder withUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
            return this;
        }

        public Article build() {
            Article article = new Article();
            article.author = author;
            article.content = content;
            article.description = description;
            article.publishedAt = publishedAt;
            article.source = source;
            article.title = title;
            article.url = url;
            article.urlToImage = urlToImage;
            return article;
        }

    }

}
