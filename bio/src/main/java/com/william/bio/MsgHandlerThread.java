package com.william.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MsgHandlerThread implements Runnable{

    private Socket socket;

    public MsgHandlerThread(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            int result;
            while (true) {
                //通过BufferedReader读取一行
                //如果已经读到输入流尾部，返回null,退出循环
                //如果得到非空值，就尝试计算结果并返回
                if ((expression = in.readLine()) == null) break;
                System.out.println(("服务端收到信息：" + expression));

                out.println("回复消息："+expression);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println((e.getLocalizedMessage()));
        } finally {
            //一些必要的清理工作
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                in = null;

            }

            if (out != null) {

                out.close();
                out = null;

            }

            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;

            }
        }


    }
	
}
