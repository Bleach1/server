package com.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public Server() {
    }

    private ThreadPoolExecutor threadPoolExecutor;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public void server() {
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("HardWareClient");
            return thread;
        };

        threadPoolExecutor = new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1024),
                threadFactory
        );

        try {
            serverSocket = new ServerSocket(6000);
            while (true) {
                socket = serverSocket.accept();
                threadPoolExecutor.execute(() -> {
                    String lineString = "";
                    try {
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//读取客户端消息
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));//向客户端写消息
                        while (!(lineString = reader.readLine()).equals("bye")) {
                            System.out.print("收到来自客户端的发送的消息是：" + lineString);
                            writer.write("服务器返回：" + lineString + "\n");
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (reader != null) {
                                reader.close();
                            }
                            if (writer != null) {
                                writer.close();
                            }
                            if (socket != null) {
                                socket.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }


                });


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}