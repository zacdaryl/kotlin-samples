package com.jzm;

import com.jzm.util.Console;

import java.util.Objects;

public class EqualTest {
    public static void main(String[] args) {
        Computer computerA = new Computer(1);
        Computer computerB = new Computer(1);

        int a = 1, b = 1;
        System.out.println( a == b); //值类型比较
        System.out.println(computerA == computerB); //引用类型比较，比较的是两个变量的引用地址
        System.out.println(computerA.equals(computerB)); //equals默认比较的是引用地址

        computerB = computerA;
        System.out.println(computerA.equals(computerB)); //equals默认比较的是引用地址

        Console.Companion.print("computerA hashCode = "  + computerA.hashCode());
        Console.Companion.print("computerB hashCode = "  + computerB.hashCode());
    }
}

class Computer {
    private int serial;

    public Computer(int serial) {
        this.serial = serial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        System.out.println("getClass: " + getClass().getName());
        System.out.println("o.getClass: " + o.getClass().getName());

        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return serial == computer.serial;
    }

    //java约定，两个对象equals 相等，必须保证hashCode也相等
    @Override
    public int hashCode() {
        return Objects.hash(serial);
    }
}