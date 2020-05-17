package com.ua.serveping.service.security.threadlocal;

public class LocalContextHolder {

    private static ThreadLocal<LocalContext> contextThreadLocal = new ThreadLocal<>();

    public static void setContextThreadLocal(LocalContext localContext) {
        contextThreadLocal.set(localContext);
    }

    public static LocalContext getLocalContext() {
        return contextThreadLocal.get();
    }

    public static void clear() {
        contextThreadLocal.remove();;
    }
}
