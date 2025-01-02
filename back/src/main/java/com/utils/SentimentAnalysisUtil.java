package com.utils;

public class SentimentAnalysisUtil {

    public static String analyze(String content) {
        if (content.contains("喜欢") || content.contains("好")) {
            return "正面";
        } else if (content.contains("差") || content.contains("混乱")) {
            return "负面";
        }
        return "中性";
    }
}

