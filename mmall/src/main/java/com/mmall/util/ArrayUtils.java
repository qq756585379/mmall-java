package com.mmall.util;

import java.util.Arrays;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static boolean isArray(Object object) {
        return object == null ? false : object.getClass().isArray();
    }

    public static <E> boolean isNull(E[] array) {
        return null == array;
    }

    public static boolean isNull(byte[] array) {
        return null == array;
    }

    public static boolean isNull(short[] array) {
        return null == array;
    }

    public static boolean isNull(int[] array) {
        return null == array;
    }

    public static boolean isNull(long[] array) {
        return null == array;
    }

    public static boolean isNull(float[] array) {
        return null == array;
    }

    public static boolean isNull(double[] array) {
        return null == array;
    }

    public static boolean isNull(char[] array) {
        return null == array;
    }

    public static boolean isNull(boolean[] array) {
        return null == array;
    }

    public static <E> boolean isNotNull(E[] array) {
        return null != array;
    }

    public static boolean isNotNull(byte[] array) {
        return null != array;
    }

    public static boolean isNotNull(short[] array) {
        return null != array;
    }

    public static boolean isNotNull(int[] array) {
        return null != array;
    }

    public static boolean isNotNull(long[] array) {
        return null != array;
    }

    public static boolean isNotNull(float[] array) {
        return null != array;
    }

    public static boolean isNotNull(double[] array) {
        return null != array;
    }

    public static boolean isNotNull(char[] array) {
        return null != array;
    }

    public static boolean isNotNull(boolean[] array) {
        return null != array;
    }

    public static <E> boolean isEmpty(E[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(boolean[] array) {
        return null == array || array.length == 0;
    }

    public static <E> boolean isNotEmpty(E[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(byte[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(short[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(int[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(long[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(float[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(double[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(char[] array) {
        return null != array && array.length > 0;
    }

    public static boolean isNotEmpty(boolean[] array) {
        return null != array && array.length > 0;
    }

    public static <E> String toString(E[] array) {
        return Arrays.deepToString(array);
    }

    public static String toString(byte[] array) {
        return Arrays.toString(array);
    }

    public static String toString(short[] array) {
        return Arrays.toString(array);
    }

    public static String toString(int[] array) {
        return Arrays.toString(array);
    }

    public static String toString(long[] array) {
        return Arrays.toString(array);
    }

    public static String toString(float[] array) {
        return Arrays.toString(array);
    }

    public static String toString(double[] array) {
        return Arrays.toString(array);
    }

    public static String toString(char[] array) {
        return Arrays.toString(array);
    }

    public static String toString(boolean[] array) {
        return Arrays.toString(array);
    }
}
