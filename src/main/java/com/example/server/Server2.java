package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public Server2() {
    }

    private ServerSocket serverSocket;
    private Socket socket;

    /**
     * 获取连接
     */
    public void getServer() {
        try {
            //绑定的端口为6666，此端口要与客户端请求的一致
            serverSocket = new ServerSocket(6000);
            while (true) {
                System.err.println("等待客户端连接......");
                //从队列中取出Socket或等待连接
                socket = serverSocket.accept();

                if (socket.isConnected()) {
                    System.out.println("连接成功！");
                }
                //开新的线程，为这个socket服务
                new HandlerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 实现一个Runnable接口，处理和client的一些事物
     */
    private class HandlerThread implements Runnable {
        private Socket socket;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;

        public HandlerThread(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {

                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //处理客户端发来的数据
                String clientMsg = null;
                while ((clientMsg = bufferedReader.readLine()) != null) {
                    System.out.println("客户端发来的消息：" + clientMsg);
                    //向客户端回复信息
                    printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println(clientMsg + "ok");
                    printWriter.flush();
                    System.out.println("向客户端回复信息：" + clientMsg + "ok/n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.err.println("服务器finally异常，异常信息：" + e.getMessage());
                    }
                }
            }
        }
    }

}


