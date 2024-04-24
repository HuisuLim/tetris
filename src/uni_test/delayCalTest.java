package uni_test;

import playscreen.utils.TimerDelay;

import java.util.Scanner;

public class delayCalTest {


    public static void main(String[] args) {
        System.out.println("5000일때 : "+TimerDelay.calDelay("normal", 5000));
        System.out.println("10000일때 : "+TimerDelay.calDelay("normal", 10000));
        System.out.println("20000일때 : "+TimerDelay.calDelay("normal", 20000));
        System.out.println("30000일때 : "+TimerDelay.calDelay("normal", 30000));
        System.out.println("40000일때 : "+TimerDelay.calDelay("normal", 40000));
        System.out.println("50000일때 : "+TimerDelay.calDelay("normal", 50000));
        System.out.println("100000일때 : "+TimerDelay.calDelay("normal", 100000));
        System.out.println("150000일때 : "+TimerDelay.calDelay("normal", 150000));
        System.out.println("200000일때 : "+TimerDelay.calDelay("normal", 200000));
        System.out.println("1000000일때 : "+TimerDelay.calDelay("normal", 1000000));
    }

}
