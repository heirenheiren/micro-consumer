package com.micro.consumer;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedDemo {
	public static void main(String[] args) throws IOException {
		PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        //将输入输出流进行链接，否则会抛出 IOException
        pipedWriter.connect(pipedReader);
        Thread printThread = new Thread(new PrintRunnable(pipedReader));
        printThread.start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                pipedWriter.write(receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            pipedWriter.close();
        }
	}
	
	static class PrintRunnable implements Runnable {
        private PipedReader pipedReader;

        public PrintRunnable(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = pipedReader.read()) != -1) {
                    System.out.println("输出：" + (char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
