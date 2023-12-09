package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-07 12:06
 **/

public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

//        // RabbitMQ 生产者的代码
//        // 1. 创建连接工厂
//        ConnectionFactory factory = new ConnectionFactory();
//        // 2. 设置连接地址
//        factory.setHost("localhost");
//        factory.setPort(5672);
//        // 3. 设置虚拟主机
//        factory.setVirtualHost("ems");
//        factory.setUsername("ems");
//        factory.setPassword("123");
//
//        //  3. 创建连接
//        Connection connection = factory.newConnection();
        Connection connection = RabbitMQUtils.getConnection();
        // 4. 创建通道
        Channel channel = connection.createChannel();
        // 5. 通道绑定队列
        // 参数1：队列名称
        // 参数2：是否持久化,true 表示持久化，false 表示不持久化
        // 参数3：是否独占队列,true 表示这个队列只有一个消费者，false 表示这个队列可以多个消费者消费
        //  参数4：是否自动删除,true 表示如果最后一个消费者断开连接，队列则自动删除
        channel.queueDeclare("aaaa", true, false, false, null);
        // 6. 发送消息
        // 参数1：交换机名称
        // 参数2：队列名称
        // 参数3：消息额外属性
        // 参数4：消息内容
//        channel.basicPublish("", "hello", null, "hello world".getBytes());
        channel.basicPublish("","aaaa", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq".getBytes());
//        channel.close();
//        connection.close();

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);


    }
}
