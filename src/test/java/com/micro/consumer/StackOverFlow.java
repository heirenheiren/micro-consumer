package com.micro.consumer;

/**
 * StackOverflowError代表的是，当栈深度超过虚拟机分配给线程的栈大小时就会出现此error。
 * @Described：栈层级不足
 * @VM args:-Xss128k
 */
public class StackOverFlow {
    private int i;

    public void plus() {
        i++;
        plus();
    }

    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.plus();
        } catch (Error e) {
            System.out.println("Error:stack length:" + stackOverFlow.i);
            e.printStackTrace();
        }
    }
}
