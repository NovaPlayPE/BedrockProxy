package eu.czechpmdevs.bedrockproxy.network.connection;

import eu.czechpmdevs.bedrockproxy.utils.Logger;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.util.concurrent.ConcurrentLinkedQueue;

public class UDPServerSocket extends ChannelInboundHandlerAdapter {

    protected final Logger logger;

    protected Bootstrap bootstrap;
    protected Channel channel;

    protected ConcurrentLinkedQueue<DatagramPacket> packets = new ConcurrentLinkedQueue<>();

    public UDPServerSocket(Logger logger, String ip, int port) {
        this.logger = logger;
        this.bootstrap = new Bootstrap();

        Class<? extends Channel> channel;
        EventLoopGroup group;
        if(Epoll.isAvailable()) {
            channel = EpollDatagramChannel.class;
            group = new EpollEventLoopGroup();
            this.logger.info("Epoll status is true");
        }
        else {
            channel = NioDatagramChannel.class;
            group = new NioEventLoopGroup();
            this.logger.info("Epoll status is false");
        }

        this.bootstrap.channel(channel);
        this.bootstrap.group(group);
        this.bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        this.bootstrap.handler(this);

        try {
            this.bootstrap.bind(ip, port).sync().channel();
        }
        catch (Exception e) {
            this.logger.error("Could not bind to " + ip + ":" + port + ". Maybe server is already running on that port.");
            System.exit(1);
        }

        this.logger.info("Successfully bound to " + ip + ":" + port + "!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof DatagramPacket) {
            this.packets.add((DatagramPacket) msg);
        }
    }

    public DatagramPacket readPacket() {
        return this.packets.poll();
    }
}
