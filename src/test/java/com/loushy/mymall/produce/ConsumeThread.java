package com.loushy.mymall.produce;

public class ConsumeThread implements Runnable {

    private Product product;

    public void run(){
        product.productNumDeduct();
    }


    public void setProduct(Product product) {
        this.product = product;
    }
}
