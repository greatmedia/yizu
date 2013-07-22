package com.yizu.proj.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectUtil {
	public static Object invoke(Object target, String methodName, List<Class> ptypes, Object... paramters) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method method = target.getClass().getMethod(methodName, ptypes.toArray(new Class[] {}));
		return method.invoke(target, paramters);
	}

	public static String buildGetter(String propertyName) {
		return "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}

	public static String buildSetter(String propertyName) {
		return "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}

	public static void printMethods(Class clazz) throws SecurityException, NoSuchMethodException {
		Method[] ms = clazz.getMethods();
		Method mm = clazz.getMethod("setLimitStart", new Class[] { Integer.TYPE });
		System.out.println(mm.getName());
		System.out.println("--------" + clazz.getSimpleName() + "-----------");
		for (Method m : ms) {
			System.out.println(m.getName());
		}
	}

	public static void setProperty(Object target, String propertyName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field field = target.getClass().getField(propertyName);
		field.setAccessible(true);
		field.set(propertyName, value);
	}

	public static Object getProperty(Object target, String propertyName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method getMethod = target.getClass().getMethod(buildGetter(propertyName), null);
		return getMethod.invoke(target, null);
	}
}
