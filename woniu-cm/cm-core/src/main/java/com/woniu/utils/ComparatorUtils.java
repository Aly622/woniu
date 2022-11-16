package com.woniu.utils;

public class ComparatorUtils {

    /**
     * @param x 被比较数
     * @param y 比较数
     * @return
     */
    public static int compareInt(int x, int y) {
        if (x == y)
            return 0;
        int result = x - y;
        return result > 0 ? 1 : -1;
    }

}
