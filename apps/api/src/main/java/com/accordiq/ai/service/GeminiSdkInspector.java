package com.accordiq.ai.service;

import com.google.genai.Client;

import java.lang.reflect.Method;

public class GeminiSdkInspector {

    public static void main(String[] args) {

        System.out.println("Client methods:\n");

        for (Method method : Client.class.getDeclaredMethods()) {
            System.out.println(method);
        }

    }
}