package com.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 情感分析工具类
 */
public class EmotionAnalysisUtil {

    private static final Set<String> POSITIVE_WORDS = new HashSet<>(Arrays.asList(
            "喜欢", "满意", "开心", "优秀", "不错", "好", "成功", "棒", "幸福", "赞", "值得"
    ));

    private static final Set<String> NEGATIVE_WORDS = new HashSet<>(Arrays.asList(
            "失望", "差", "糟糕", "失败", "问题", "不满", "讨厌", "愤怒", "不推荐", "垃圾", "无聊"
    ));

    public static String analyzeEmotion(String content) {
        if (content == null || content.trim().isEmpty()) {
            return "中性";
        }

        int positiveScore = 0;
        int negativeScore = 0;

        for (String word : content.split("\\s+|，|。|！|？|；|:|：|\\.")) {
            if (POSITIVE_WORDS.contains(word)) {
                positiveScore++;
            } else if (NEGATIVE_WORDS.contains(word)) {
                negativeScore++;
            }
        }

        if (positiveScore > negativeScore) {
            return "正面";
        } else if (negativeScore > positiveScore) {
            return "负面";
        } else {
            return "中性";
        }
    }
}

