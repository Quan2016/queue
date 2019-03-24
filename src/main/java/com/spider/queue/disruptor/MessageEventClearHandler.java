package com.spider.queue.disruptor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmax.disruptor.EventHandler;

public class MessageEventClearHandler implements EventHandler<MessageEvent>{
	
	private static final Logger LOGGER = LogManager.getLogger(MessageEventClearHandler.class);
	@Override
	public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
		LOGGER.info("clear message sequence :{}", sequence);
		event.setMessage(null);//清理消息,如果队列里面的对象很大，队列比较场，很有必要释放内存
	}

}
