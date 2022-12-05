package com.livk.autoconfigure.useragent.yauaa.support;

import nl.basjes.parse.useragent.UserAgent;

/**
 * <p>
 * UserAgentContextHolder
 * </p>
 *
 * @author livk
 *
 */
public class UserAgentContextHolder {
    private static final ThreadLocal<UserAgent> context = new ThreadLocal<>();

    private static final ThreadLocal<UserAgent> inheritableContext = new InheritableThreadLocal<>();

    public static void set(UserAgent userAgent) {
        context.set(userAgent);
        inheritableContext.set(userAgent);
    }

    public static UserAgent get() {
        UserAgent userAgent = context.get();
        if (userAgent == null) {
            userAgent = inheritableContext.get();
        }
        return userAgent;
    }

    public static void remove() {
        context.remove();
        inheritableContext.remove();
    }
}
