package com.lxc.demo;

import java.util.Random;

public class rand {
    public static void main(String[] args){
      char[] chs = new char[52];
      for(int i = 0 ; i<chs.length; i++){
          if(i<=25){
              chs[i] = (char)((int)('a') + i);
          }else{
              chs[i] = (char) ((int)('A') + i - 26);
              //这里的-26
          }
        }
//      for(int i = 0 ; i<chs.length; i++){
//          System.out.println(chs[i]);
//      }
      String results = "";
      Random r = new Random();
//    int randomIndex = 0;
      for(int i = 0; i<4; i++){
          int randomIndex = r.nextInt(chs.length);
          results = results + chs[randomIndex];
//          System.out.println(chs[randomIndex]);

      }
      int randomIndex = r.nextInt(10);
      results = results + randomIndex;
        System.out.println(results);
//        System.out.println(randomIndex);
    }
}
