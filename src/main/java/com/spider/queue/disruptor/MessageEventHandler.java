package com.spider.queue.disruptor;

import com.lmax.disruptor.EventHandler;

public class MessageEventHandler implements EventHandler<MessageEvent>{

	@Override
	public void onEvent(MessageEvent messageEvent,long sequence, boolean endOfBatch) throws Exception {
		System.out.println(messageEvent);
	}

}
