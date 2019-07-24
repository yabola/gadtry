package com.github.harbby.gadtry.java8Test;

import java.util.Arrays;
import java.util.List;

import static com.github.harbby.gadtry.java8Test.LambdaBean.testLambda;

/**
 * Created By lcl on 2019/7/24
 */
public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("hello world");
//        System.out::println("?");
       testLambda(bean -> bean.bind());

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);
//        LambdaBean lambdaBean = LambdaBean.testLambda(bean -> ());
    }
}
