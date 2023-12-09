package workquene;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-07 20:35
 **/

public class Customer1 {
    public static void main(String[] args) throws IOException {
        // 1. 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 获取通道
        Channel channel = connection.createChannel();
        // 每次消费一个消息
        channel.basicQos(1);
        // 3. 声明队列
        channel.queueDeclare("work", true, false, false, null);
        // 4. 绑定队列
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: " + new String(body));
                // 参数1:确认队列中那个具体消息 参数2:是否开启多个消息同时确实
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
