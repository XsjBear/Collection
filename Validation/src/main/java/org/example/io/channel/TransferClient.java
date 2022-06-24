package org.example.io.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @description: 管道传输文件客户端
 * @author: Xsj
 * @create: 2022-06-23 20:05
 **/
@Slf4j
public class TransferClient {

    /**
     * 发送文件路径
     */
    private static final String SEND_FILE_PATH = "";

    /**
     * 编码
     */
    private Charset charset = Charset.forName("UTF-8");



    /**
     * @Description: 发送文件
     * @author: Xsj
     * @date: 2022/6/24 20:05
     * @return void
     */
    public void sendFile() throws FileNotFoundException {

        File file = new File(SEND_FILE_PATH);
        if (!file.exists()){
//            file = new File(SEND_FILE_PATH);
            throw new RuntimeException("文件不存在");
        }

        // 获取文件管道
        FileChannel channel = new FileInputStream(file).getChannel();

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.setOption(StandardSocketOptions.TCP_NODELAY,true);
            // 连接远程主机
            socketChannel.socket().connect(new InetSocketAddress("127.0.0.1",18899));
            // 设置为非阻塞连接
            socketChannel.configureBlocking(false);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }










}
