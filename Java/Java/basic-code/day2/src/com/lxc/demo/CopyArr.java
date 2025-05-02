package com.lxc.demo;

import java.util.Random;

public class CopyArr {
    public static void main(String[] args) {
        int[] Arr = new int[20];
        Random s = new Random();
        for(int i = 0; i<Arr.length; i++){
            Arr[i] = s.nextInt();
            System.out.println(Arr[i]);
        }

        int[] newArr = new int[Arr.length];
        for(int i = 0; i<Arr.length; i++){
            newArr[i] = Arr[i];
            System.out.println(newArr[i]);
        }
    }
}
