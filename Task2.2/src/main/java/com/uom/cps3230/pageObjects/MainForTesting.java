package com.uom.cps3230.pageObjects;

import com.uom.cps3230.ScottsOperator;

public class MainForTesting {
    public static void main(String[] args)  {
        ScottsOperator sut = new ScottsOperator();
        sut.loginUser("salibakris03@gmail.com", "testingCps3230");
        sut.searchProduct("bread");
        sut.addFirstProductToCart();
    }
}
