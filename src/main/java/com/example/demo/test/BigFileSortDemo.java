package com.example.demo.test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.sun.webkit.dom.CharacterDataImpl.LINE_SEPARATOR;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-16 20:29
 **/
public class BigFileSortDemo {
    private static final String BIG_FILE_NAME="bigFile.txt";
    private static final String SORT_FILE_NAME="sortFile.txt";
    private static final Long LINE_COUNT=Long.MAX_VALUE;
    private static final int BATCH_SIZE=10;


    /**
     * 创建大文件
     */
    private static void createBigFile() {
        Random random = new Random();
        try (FileWriter writer = new FileWriter(BIG_FILE_NAME)) {
            for (int i = 0; i < LINE_COUNT; i++) {
                int val = random.nextInt(Integer.MAX_VALUE);
                writer.write(val + LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 切分文件
     * @return
     */
    private static List<String> separateFile() {
        List<String> fileNameList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BIG_FILE_NAME))) {
            int index = 0;
            List<Integer> batchLineList = new ArrayList<>(BATCH_SIZE);
            String line;
            while ((line = reader.readLine()) != null) {
                batchLineList.add(Integer.valueOf(line));
                if (batchLineList.size() == BATCH_SIZE) {
                    // 内容排序
                    batchLineList.sort(Comparator.comparingInt(a -> a));
                    // 写小文件
                    String fileName = BIG_FILE_NAME + ".tmp." + index++;
                    try (FileWriter tmpWriter = new FileWriter(fileName)) {
                        for (Integer val : batchLineList) {
                            tmpWriter.write(val + LINE_SEPARATOR);
                        }
                    }
                    fileNameList.add(fileName);
                    batchLineList.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNameList;
    }

    /**
     * 合并文件
     * @param fileNameList
     */
    private static void mergeFile(List<String> fileNameList) {
        Map<BufferedReader, String> map = new HashMap<>();
        try (FileWriter writer = new FileWriter(SORT_FILE_NAME)) {
            for (String fileName : fileNameList) {
                BufferedReader tmpReader = new BufferedReader(new FileReader(fileName));
                map.put(tmpReader, tmpReader.readLine());
            }
            while (true) {
                boolean canRead = false;
                Map.Entry<BufferedReader, String> minEntry = null;
                for (Map.Entry<BufferedReader, String> entry : map.entrySet()) {
                    String value = entry.getValue();
                    if (value == null) {
                        continue;
                    }
                    // 获取当前 reader 内容最小 entry
                    if ((minEntry == null) || (Integer.valueOf(value) < Integer.valueOf(minEntry.getValue()))) {
                        minEntry = entry;
                    }
                    canRead = true;
                }
                // 当且仅当所有 reader 内容为空时，跳出循环
                if (!canRead) {
                    break;
                }
                writer.write(minEntry.getValue() + LINE_SEPARATOR);
                minEntry.setValue(minEntry.getKey().readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 注意关闭分片文件输入流
            for (BufferedReader reader : map.keySet()) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
