package com.loushy.mymall.produce;

public class Product {
    private int productNum;

    private int productMinNum;

    private int productMaxNum;


    public int getProductMaxNum() {
        return productMaxNum;
    }
    public void setProductMaxNum(int productMaxNum) {
        this.productMaxNum = productMaxNum;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getProductMinNum() {
        return productMinNum;
    }

    public void setProductMinNum(int productMinNum) {
        this.productMinNum = productMinNum;
    }

    public synchronized void productNumAdd(){
            if (productNum>=productMaxNum){
                try{
                    System.out.println("产品数量达到最大值，无法生产。"+Thread.currentThread().getName()+"等待中");
                    wait();
                    System.out.println(Thread.currentThread().getName()+"等待结束,准备生产产品。");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            productNum++;
            System.out.println(Thread.currentThread().getName()+"生产第"+productNum+"个产品。");
            notifyAll();
    }

    public synchronized   void productNumDeduct(){
            if (productNum<=productMinNum){
                try{
                    System.out.println("产品数量不足，"+Thread.currentThread().getName()+"等待中。");
                    wait();
                    System.out.println(Thread.currentThread().getName()+"等待结束,准备提取产品。");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"提取了第"+productNum+"个产品。");
            productNum--;
            notifyAll();
    }


}
