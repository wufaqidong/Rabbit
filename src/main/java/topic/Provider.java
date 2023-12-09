package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-08 13:03
 **/

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topic_logs", "topic");
        String routeKey = "user.save";
        channel.basicPublish("topic_logs", routeKey, null, ("这里是topic动态路由模型：【" + routeKey + "】").getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
