package com.example.demo.feign;

import com.example.demo.feign.entity.Contributor;
import com.example.demo.feign.entity.Issue;
import com.example.demo.feign.entity.Repository;
import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.stream.Collectors;

public interface GitHub {
    @RequestLine("GET /users/{username}/repos?sort=full_name")
    List<Repository> repos(@Param("username") String owner);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

    /** Lists all contributors for all repos owned by a user. */
    default List<String> contributors(String owner) {
        return repos(owner).stream()
                .flatMap(repo -> contributors(owner, repo.getName()).stream())
                .map(c -> c.getLogin())
                .distinct()
                .collect(Collectors.toList());
    }
}