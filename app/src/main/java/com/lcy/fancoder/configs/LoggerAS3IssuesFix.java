package com.lcy.fancoder.configs;

import android.util.Log;

import com.orhanobut.logger.LogStrategy;

public class LoggerAS3IssuesFix implements LogStrategy {

    /**
     * 保存最后的随机数,避免连续两次出现相同随机数.
     */
    private int lastRandomValue;

    /**
     * Low-level logging call.
     *
     * @param priority The priority/type of this log message
     * @param tag      Used to identify the source of a log message.  It usually identifies
     *                 the class or activity where the log call occurs.
     * @param message  The message you would like logged.
     */
    @Override
    public void log(int priority, String tag, String message) {
        Log.println(priority, String.format("%s-%s", tag, randomKey()), message);
    }

    /**
     * 生成一个10以内的随机整数.
     *
     * @return 一个10以内的随机整数.
     */
    private String randomKey() {
        int random = (int) (10 * Math.random());
        if (random == lastRandomValue) {
            random = (random + 1) % 10;
        }
        lastRandomValue = random;
        return String.valueOf(random);
    }


}