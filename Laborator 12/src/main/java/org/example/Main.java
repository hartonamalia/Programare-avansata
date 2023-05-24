package org.example;

import java.lang.reflect.Method;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("C:Programare-Avansata-main/Laborator 12/TestClass/TestClass/target/classes/org/example/TestClass.class");

        File rootDir = new File("C:Programare-Avansata-main/Laborator 12/TestClass/TestClass/target/classes/org/example/TestClass.class");
        URL url = rootDir.toURI().toURL();
        URL[] urls = new URL[]{url};

        ClassLoader loader = new URLClassLoader(urls);

        String className = file.getPath()
                .replace(rootDir.getPath(), "")
                .replace(".class", "")
                .replace(File.separator, ".");

        if (className.startsWith(".")) {
            className = className.substring(1);
        }
        Class<?> clazz = loader.loadClass(className);

        System.out.println("Class Name: " + clazz.getName());
        System.out.println("Package Name: " + clazz.getPackage().getName());

        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                if (java.lang.reflect.Modifier.isStatic(method.getModifiers())
                        && method.getParameterCount() == 0) {
                    try {
                        method.invoke(null);
                        System.out.println("Method " + method.getName() + " executed successfully.");
                    } catch (Exception e) {
                        System.out.println("Exception occurred while executing method " + method.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
