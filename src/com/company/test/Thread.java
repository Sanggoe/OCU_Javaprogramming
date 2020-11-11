package com.company.test;

public class Thread {
    public static void main(String arg[]) {

        int i = 1;
        int j = i++;
        if((i>++j)&&(i++==j))
            i+=j;
        System.out.println("i=" + i + ", j=" + j);
    }
}
