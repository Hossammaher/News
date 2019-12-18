
package com.example.news.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;


public class NewsResponse {

    @Expose
    private List<Article> articles;
    @Expose
    private String status;
    @Expose
    private Long totalResults;

    public List<Article> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public static class Builder {

        private List<Article> articles;
        private String status;
        private Long totalResults;

        public NewsResponse.Builder withArticles(List<Article> articles) {
            this.articles = articles;
            return this;
        }

        public NewsResponse.Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public NewsResponse.Builder withTotalResults(Long totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        public NewsResponse build() {
            NewsResponse newsResponse = new NewsResponse();
            newsResponse.articles = articles;
            newsResponse.status = status;
            newsResponse.totalResults = totalResults;
            return newsResponse;
        }

    }

}
