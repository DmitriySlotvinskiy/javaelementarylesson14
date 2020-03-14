package com.slotvinskiy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

//1) Написать класс в котором есть 4 метода: +-/*
//Которые принимают 2 параметра типа int.
//Нужно написать метод который по строковому имени метода найдет его и вызовет, при помощи Reflection.
//Если такого метода нет выдать ошибку.
public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        String mName = sc.nextLine();
        int x = sc.nextInt();
        int y = sc.nextInt();
        invokeMethodByName(mName, calculator, x, y);
    }

    private static void invokeMethodByName(String mName, Calculator calculator, int x, int y) {

        Class clazz = calculator.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        Method method = null;
        String methodName = null;
        Object[] params = new Object[methods.length];
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(mName)) {
                method = methods[i];
                params = methods[i].getParameterTypes();
                break;
            }
        }
        try {
            double res = (double) method.invoke(calculator, x, y);
            System.out.println(res);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

