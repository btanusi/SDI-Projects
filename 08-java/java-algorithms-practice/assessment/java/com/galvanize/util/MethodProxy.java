package com.galvanize.util;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodProxy {

    private final Method method;
    private final Type target;
    private final Object[] parameterTypes;

    /**
     *
     * @param method is the underlying reflection Method object
     * @param target is the underlying object to call the method on
     * @param parameterTypes is an array of _expected_ parameter types (some of which are TypeTokens)
     */
    public MethodProxy(Method method, Type target, Object[] parameterTypes) {
        this.method = method;
        this.target = target;
        this.parameterTypes = parameterTypes;
    }

    public Method getMethod() {
        return method;
    }
}
