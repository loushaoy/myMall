package com.loushy.mymall.produce;

public class ThreadTest {

    public static void main (String argus[]){

        Product product = new Product();
        product.setProductMaxNum(10);
        product.setProductMinNum(1);
        product.setProductNum(3);

        ConsumeThread  consume = new ConsumeThread();
        consume.setProduct(product);

        ProduceThread produce = new ProduceThread();
        produce.setProduct(product);

        Thread consumeThread1 = new Thread(consume,"ConsumeThread-1");
        Thread consumeThread2 = new Thread(consume,"ConsumeThread-2");
        Thread consumeThread3 = new Thread(consume,"ConsumeThread-3");

        Thread produceThread1 = new Thread(produce,"ProduceThread-1");
        Thread produceThread2 = new Thread(produce,"ProduceThread-2");
        Thread produceThread3 = new Thread(produce,"ProduceThread-3");

        consumeThread1.start();
        consumeThread2.start();
        consumeThread3.start();

        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }

        produceThread1.start();
        produceThread2.start();
        produceThread3.start();
    }
}
