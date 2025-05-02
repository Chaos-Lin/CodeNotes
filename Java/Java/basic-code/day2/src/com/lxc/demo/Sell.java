package com.lxc.demo;

import java.util.Scanner;

public class Sell {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入机票原价");
        int ticket = sc.nextInt();
        System.out.println("请输入乘坐月份");
        int month = sc.nextInt();
        System.out.println("请输入当前购买的舱位：0 头等舱 1 经济舱");
        int type = sc.nextInt();
        if (month >= 5 && month <= 10) {
            getTicket(type, ticket, 0.9, 0.85);
        } else {
            getTicket(type, ticket, 0.7, 0.65);
        }

    }

    private static void getTicket(int type, int ticket, double x, double x1) {
        if (type == 0) {
            System.out.println("头等舱优惠为：" + (ticket * x));
        } else {
            System.out.println("经济舱优惠为：" + (ticket * x1));
        }
    }


}