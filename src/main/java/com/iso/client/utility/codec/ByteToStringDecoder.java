package com.iso.client.utility.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@ChannelHandler.Sharable
@Component
@Slf4j
public class ByteToStringDecoder extends MessageToMessageDecoder<ByteBuf> {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		try {
			out.add(buf.toString(StandardCharsets.ISO_8859_1));
			log.info("DECODED MSG ["+buf.toString(StandardCharsets.ISO_8859_1)+"]");
		} catch(Exception e) {
			log.error("ERROR WHEN DECODING BYTE TO STRING "+e.getCause().getMessage()+" | "+e.getMessage());
		}
	}
}
