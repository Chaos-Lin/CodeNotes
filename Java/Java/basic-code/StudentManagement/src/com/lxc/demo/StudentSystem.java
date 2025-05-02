package com.lxc.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        loop: while(true){
            System.out.println("welcome to the Student Management System");
            System.out.println("1:add");
            System.out.println("2:remove");
            System.out.println("3:revise");
            System.out.println("4:require");
            System.out.println("5:exit");
            System.out.println("please choose the option");
            Scanner sc = new Scanner(System.in);
            String in = sc.next();
            switch(in){
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> reviseStudent(list);
                case "4" -> requireStudent(list);
                case "5" -> {
                    System.out.println("exit");
                    break loop;
//                    System.exit(0);
                }
                default -> System.out.println("no option");

            }
        }
    }

    public static void addStudent(ArrayList<Student> list){
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("please type the id");
        int id = sc.nextInt();
        //todo check the list
        while(checkId(list,id)){
            System.out.println("repetitive id");
            System.out.println("retype the id:");
            id = sc.nextInt();
        }
        stu.setId(id);
        System.out.println("please type the name");
        String name = sc.next();
        stu.setName(name);
        System.out.println("please type the age");
        int age = sc.nextInt();
        stu.setAge(age);
        System.out.println("please type the address");
        String address = sc.next();
        stu.setAddress(address);
//        Student stu = new Student(id,name,age,address);
        list.add(stu);
        System.out.println("already added");
    }

    public static boolean checkId(ArrayList<Student> list, int id){
        Boolean result = Boolean.FALSE;
//        for(int i = 0 ; i < list.size(); i++){
//            Student stu = list.get(i);
//            if(stu.getId() == id){
//                result = Boolean.TRUE;
//                // 重复
//                break;
//            }
//        }
        int index = getIndex(list,id);
        if(index != -1){
            result = Boolean.TRUE;
        }
        return result;
    }

    public static void deleteStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        if(checkId(list ,id)){
            list.remove(getIndex(list, id));
            System.out.println("id:"+id +"has been deleted");
        }else{
            System.out.println(" id is not existed");
        }

    }
    private static int getIndex(ArrayList<Student> list, int id){
        int result = -1;
        for(int i = 0 ; i < list.size(); i++){
            Student stu = list.get(i);
            if(stu.getId() == id){
                result = i;
                // 重复
                break;
            }
        }
        return result;
    }
    public static void reviseStudent(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("please type the needed revised id");
        int id = sc.nextInt();
        if(checkId(list ,id)){
            Student stu = list.get(getIndex(list, id));
            System.out.println("please type the name");
            String name = sc.next();
            stu.setName(name);
            System.out.println("please type the age");
            int age = sc.nextInt();
            stu.setAge(age);
            System.out.println("please type the address");
            String address = sc.next();
            stu.setAddress(address);
        }else{
            System.out.println(" id is not existed");
        }
    }

    public static void requireStudent(ArrayList<Student> list){
        if(list.size() == 0 ){
            System.out.println("no any Student");

        }else{
            System.out.println("id\t\tname\tage\taddress");
            // \t table
            for(int i =0; i< list.size(); i++){
                Student stu = list.get(i);
                // from list to get stu
                System.out.println(stu.getId()+"\t" + stu.getName()+"\t"+stu.getAge()+"\t"+stu.getAddress());
            }
        }
        return;
    }
}
