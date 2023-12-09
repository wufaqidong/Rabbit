package routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-08 08:29
 **/

public class Provider {
    public static void main(String[] args) throws IOException {
        // 获取连接
        Connection connection = RabbitMQUtils.getConnection();
        //  创建通道
        Channel channel = connection.createChannel();
        // 声明交换机 参数一：交换机名称 参数二：交换机类型 direct 发布订阅模式 fanout 广播模式 topic 主题模式
        channel.exchangeDeclare("logs_direct", "direct");
        // 发布消息 参数一：交换机名称 参数二：路由键 参数三：消息内容 参数四：消息属性
        String routeKey = "info";
        channel.basicPublish("logs_direct", routeKey, null, ("这是direct模式发布消息route key:[" + routeKey + "]").getBytes());
        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
