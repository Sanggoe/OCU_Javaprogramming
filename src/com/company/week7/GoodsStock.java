package com.company.week7;

public class GoodsStock {
    String goodsCode;
    int stockNum;

    GoodsStock(String goodsCode, int stockNum) {
        this.goodsCode = goodsCode;
        this.stockNum = stockNum;
    }

    void addStock(int amount) {
        stockNum += amount;
    }

    int subtractStock(int amount) {
        if (stockNum < amount)
            return 0;
        stockNum -= amount;
        return amount;
    }
}
