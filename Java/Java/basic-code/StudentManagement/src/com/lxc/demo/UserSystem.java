package com.lxc.demo;

import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class UserSystem {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        while(true){
            System.out.println("welcome to the Student Management System");
            System.out.println("1:log in");
            System.out.println("2:new user");
            System.out.println("3:foget password");
            System.out.println("4:exit");
            System.out.println("please choose the option");
            Scanner sc = new Scanner(System.in);
            String in = sc.next();
            switch(in){
                case "1" -> logIn(list);
                case "2" -> newUser(list);
                case "3" -> revisePassword(list);
                case "4" -> {
                    System.out.println("exit");
//                    break loop;
                    System.exit(0);
                }
                default -> System.out.println("no option");
            }
        }
    }

    public static void logIn(ArrayList<User> list){
        //todo type id get the user
        Scanner sc = new Scanner(System.in);
        //todo tyep password
    }

    public static void newUser(ArrayList<User> list){
        //todo idcheck
        //todo number
        //todo id number
    }

    public static void revisePassword(ArrayList<User> list){
        //todo 检查用户名是否存在
        //todo check number
        //todo check id number
        //todo revise password
    }

}
