package routing;

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
 * @create: 2023-12-07 21:55
 **/

public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道绑定
        channel.exchangeDeclare("logs_direct", "direct");
        // 临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(queueName, "logs_direct", "error");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: " + new String(body));
                // 参数1:确认队列中那个具体消息 参数2:是否开启多个消息同时确实
//                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
