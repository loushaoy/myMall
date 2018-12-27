package com.loushy.mymall.produce;
/**
 * 生产者线程
 * */
public class ProduceThread implements Runnable {

    private Product product;

    public void run(){
        product.productNumAdd();
    }

    public void setProduct(Product product){
        this.product =product;
    }


}
