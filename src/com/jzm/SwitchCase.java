package com.jzm;

public class SwitchCase {
    public void test() {
        String val = "helloworld";
        switch (val) {
            case "helloworld":
                System.out.println("helloworld");
                break;
            case "hello":
                System.out.println("hello");
                break;
        }
    }

    public static void main(String[] args) {
        SwitchCase sc = new SwitchCase();
        sc.test();

        int[] nums = {1,2,3,4};
    }
}

