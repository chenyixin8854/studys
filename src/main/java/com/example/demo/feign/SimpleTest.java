package com.example.demo.feign;

import com.example.demo.feign.entity.Issue;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import okhttp3.OkHttpClient;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-12 23:52
 **/
public class SimpleTest {
    private static final String GITHUB_TOKEN = "GITHUB_TOKEN";

    static GitHub connect() {
        Decoder decoder = new GsonDecoder();
        Encoder encoder = new GsonEncoder();

        return Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .errorDecoder(new GitHubErrorDecoder(decoder))
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .requestInterceptor(template -> {
                    if (System.getenv().containsKey(GITHUB_TOKEN)) {
                        System.out.println("Detected Authorization token from environment variable");
                        template.header(
                                "Authorization",
                                "token " + System.getenv(GITHUB_TOKEN));
                    }
                })
                .target(GitHub.class, "https://api.github.com");
    }

    public static void main(String... args) {
        GitHub github = connect();

        System.out.println("Let's fetch and print a list of the contributors to this org.");
        List<String> contributors = github.contributors("openfeign");
        for (String contributor : contributors) {
            System.out.println(contributor);
        }

        System.out.println("Now, let's cause an error.");
        try {
            github.contributors("openfeign", "some-unknown-project");
        } catch (GitHubClientError e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Now, try to create an issue - which will also cause an error.");
        try {
            Issue issue = new Issue();
            issue.setTitle("The title");
            issue.setBody("Some Text");
            github.createIssue(issue, "OpenFeign", "SomeRepo");
        } catch (GitHubClientError e) {
            System.out.println(e.getMessage());
        }
    }
}
