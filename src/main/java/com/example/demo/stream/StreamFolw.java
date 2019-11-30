package com.example.demo.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamFolw {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(b -> !b.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered.toString());

        Random random = new Random();
//        random.ints().limit(10).forEach(System.out::println);
//        random.ints(1,10).limit(10).forEach(System.out::println);
        IntSummaryStatistics stats =random.ints(1,10).limit(100).map(i -> i*i).distinct().limit(7).sorted().summaryStatistics();
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
        System.out.println(stats.getSum());
        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
    }




}
