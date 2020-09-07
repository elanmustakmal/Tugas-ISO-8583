package com.iso.client.utility.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ChannelHandler.Sharable
@Component
@Slf4j
public class StringToByteEncoder extends MessageToMessageEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String requestData, List<Object> out) throws Exception {
//		out.add(requestData);
		out.add(Unpooled.copiedBuffer(requestData.getBytes(StandardCharsets.ISO_8859_1),0, requestData.getBytes(StandardCharsets.ISO_8859_1).length));
		log.info("FINISH ENCODE : ["+requestData+"]");
		
		
	}
}
