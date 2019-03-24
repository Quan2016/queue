package com.spider.queue.disruptor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 * @author jq
 */
public class MessageEventHandler implements EventHandler<MessageEvent>{
	
	private static final Logger LOGGER = LogManager.getLogger(MessageEventHandler.class);
	@Override
	public void onEvent(MessageEvent messageEvent,long sequence, boolean endOfBatch) throws Exception {
		LOGGER.info("message [{}] was consumed." , messageEvent.getMessage());
	}

}
