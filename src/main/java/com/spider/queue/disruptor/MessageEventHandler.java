package com.spider.queue.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 * @author jq
 */
public class MessageEventHandler implements EventHandler<MessageEvent>{

	@Override
	public void onEvent(MessageEvent messageEvent,long sequence, boolean endOfBatch) throws Exception {
		System.out.println("consume message:" + messageEvent.getMessage() + " sequence:" + sequence + " endOfBatch:" + endOfBatch);
	}

}
