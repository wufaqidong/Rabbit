package helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.junit.jupiter.api.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-07 14:54
 **/

public class Customer {
    // 消费消息
    @Test
    public static void main(String[] args) throws IOException, TimeoutException {

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
        //通过工具类获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 4. 创建通道
        Channel channel = connection.createChannel();
        // 5. 通道绑定队列
        // 参数1：队列名称
        // 参数2：是否持久化,true 表示持久化，false 表示不持久化
        // 参数3：是否独占队列,true 表示这个队列只有一个消费者，false 表示这个队列可以多个消费者消费
        //  参数4：是否自动删除,true 表示如果最后一个消费者断开连接，队列则自动删除
        channel.queueDeclare("hello", false, false, false, null);
        // 6. 消费消息
        // 参数1：队列名称
        // 参数2：是否自动确认,true 表示自动确认，false 表示手动确认
        // 参数3：消费者
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者收到消息：" + new String(body));
            }
        });

    }
}
