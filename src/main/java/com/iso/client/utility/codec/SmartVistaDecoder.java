package com.iso.client.utility.codec;

import com.iso.client.configuration.Constants;
import com.iso.client.configuration.SwitcherCustomException;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.netty.ChannelHandlerFactory;

@Slf4j
public class SmartVistaDecoder extends LengthFieldBasedFrameDecoder implements ChannelHandlerFactory {


    public SmartVistaDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    public SmartVistaDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("DECODE ERROR " + cause.getMessage());
        throw new SwitcherCustomException(Constants.RC_INTERNAL_ERROR, cause.getMessage(), cause);
    }

    @Override
    public ChannelHandler newChannelHandler() {
        return (ChannelHandler) new SmartVistaDecoder(Integer.MAX_VALUE, 0, 2, 0, 2);
    }

}
