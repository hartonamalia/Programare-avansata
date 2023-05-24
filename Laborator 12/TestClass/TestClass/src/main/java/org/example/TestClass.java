package org.example;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestClass {

    @Test
    public static void testMethod1() {
        System.out.println("Test method 1 executed!");
    }

    @Test
    public static void testMethod2() {
        System.out.println("Test method 2 executed!");
    }

    public static void nonTestMethod() {
        System.out.println("This is not a test method.");
    }

    public static void main(String[] args) {
        Class<?> clazz = TestClass.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0) {
                try {
                    method.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
