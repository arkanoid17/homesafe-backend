package com.arka.homesafe.threadlocal;

public class RequestContext {
    private static final ThreadLocal<Long> userId = new ThreadLocal<>();

    public static void setUserId(Long id) {
        userId.set(id);
    }

    public static Long getUserId() {
        return userId.get();
    }

    public static void clear() {
        userId.remove();
    }
}

