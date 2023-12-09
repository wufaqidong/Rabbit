package workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-07 20:29
 **/

public class Provider {
    public static void main(String[] args) throws IOException {
        // 1. 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 2. 获取通道
        Channel channel = connection.createChannel();
        // 3. 声明队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 1; i <= 20; i++) {
            // 4. 发送消息
            channel.basicPublish("", "work", null, ("hello world " + i).getBytes());
        }
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
