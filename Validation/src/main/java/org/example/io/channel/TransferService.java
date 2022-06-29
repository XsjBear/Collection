package org.example.io.channel;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.example.io.channel.Enum.FileTypeEnum;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Xsj
 * @Descript:文件传输服务端
 * @date 2022年06月24日 18:15
 */
public class TransferService {




    /**
     * 文件接收路径
     */
    private static final String RECEIVE_FILE_PATH = "";

    /**
     * 编码
     */
    private Charset charset = Charset.forName("UTF-8");


    /**
     * @Description: 服务端保存的客户端文件，一个对象代表一个客户端文件
     * @author: Xsj
     * @date: 2022/6/24 18:39
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Session{

        /**
         * 读取文件类型
         * @see FileTypeEnum
         */
        int type;

        /**
         * 文件名称
         */
        String fileName;

        /**
         * 文件名长度
         */
        int fileNameLength;

        /**
         * 文件长度
         */
        int fileLength;

        /**
         * 开始传输时间
         */
        long startTime;

        /**
         * 客户端地址
         */
        InetSocketAddress clientAddress;

        /**
         * 文件输出通道
         */
        FileChannel fileChannel;

        /**
         * 文件接收长度
         */
        long receiveLength;

    }

    /**
     * 十字节
     */
    private ByteBuffer buffer = ByteBuffer.allocate(10240);


    /**
     * 客户端传输对象
     */
    Map<SelectableChannel,Session> clientMap = new HashMap<SelectableChannel,Session>();


    public void startService() throws IOException {
        // 获取 Selector 选择器
        Selector selector = Selector.open();

        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();

        // 设置为非阻塞连接
        serverSocketChannel.configureBlocking(false);
        // 服务端绑定端口
        InetSocketAddress socketAddress = new InetSocketAddress(18899);
        serverSocket.bind(socketAddress);

        // 监听连接事件
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        // 有新的io事件
        while (selector.select() > 0){
            if (null == selector.selectedKeys()){
                continue;
            }

            System.out.println();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                // 获取选择键并处理
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()){
                    // 新连接事件
                    System.out.println("新");
                    ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();

                    SocketChannel accept = socketChannel.accept();
                    if (accept == null){
                        continue;
                    }
                    accept.configureBlocking(false);

                    accept.setOption(StandardSocketOptions.TCP_NODELAY,true);
                    accept.register(selector,SelectionKey.OP_READ);

                    Session session = new Session();
                    session.clientAddress = (InetSocketAddress) accept.getRemoteAddress();
                    clientMap.put(accept,session);
                    System.out.println(accept.getRemoteAddress() + "连接成功");


                } else if (selectionKey.isReadable()) {
                    // 读事件
                    System.out.println("读");
                } else if (selectionKey.isWritable()) {
                    // 写事件
                    System.out.println("写");
                } else if (selectionKey.isConnectable()) {
                    // 是否已连接
                    System.out.println("已连接");
                }
                // 手动移除事件，如果不手动移除 Selector 不会自己移除，会导致重复处理.
                iterator.remove();
            }
        }

    }


    public static void main(String[] args) {

        TransferService transferService = new TransferService();
        try {
            transferService.startService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
