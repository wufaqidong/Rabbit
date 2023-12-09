package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-07 21:33
 **/

public class Provider {
    public static void main(String[] args) throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明交换机
        // 参数一：交换机名称
        // 参数二：交换机类型 fanout 广播
        channel.exchangeDeclare("logs", "fanout");
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);

    }
}
