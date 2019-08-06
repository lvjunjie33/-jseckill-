package com.liushaoming.jseckill.backend.mq;

import com.liushaoming.jseckill.backend.bean.MQConfigBean;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 管理当前线程使用的Rabbitmq通道.
 * 使用了ThreadLocal
 */
@Component
public class MQChannelManager {
    private static final Logger logger = LoggerFactory.getLogger(MQChannelManager.class);

    @Resource(name = "mqConnectionSeckill")
    private Connection connection;

    @Autowired

    private MQConfigBean mqConfigBean;
    private ThreadLocal<Channel> localSendChannel = new ThreadLocal<Channel>() {
        public Channel initialValue() {
            try {
            	//创建通道
                Channel channelInst = connection.createChannel();
                /*
	              * 声明（创建）队列
	              * 参数1：队列名称
	              * 参数2：为true时server重启队列不会消失
	              * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
	              * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
	              * 参数5：建立队列时的其他参数
	             */
                channelInst.queueDeclare(mqConfigBean.getQueue(), true, false, false, null);
                return channelInst;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    };

    /**
     * 获取当前线程使用的Rabbitmq通道
     *
     * @return
     */
    public Channel getSendChannel() {
        logger.info("Send_CurThread.id={}--->", Thread.currentThread().getId());
//        Channel channel = localSendChannel.get();
        Channel channel = null;
//        if (channel == null) {
            //申明队列
            try {
                channel = connection.createChannel();
                /*
	              * 声明（创建）队列
	              * 参数1：队列名称
	              * 参数2：为true时server重启队列不会消失
	              * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
	              * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
	              * 参数5：建立队列时的其他参数
	             */
                channel.queueDeclare(mqConfigBean.getQueue(), true, false, false, null);
                localSendChannel.set(channel);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
        return channel;
    }
//    public void connectionClose() throws IOException {
//    	connection.close();
//    
//    }
}
