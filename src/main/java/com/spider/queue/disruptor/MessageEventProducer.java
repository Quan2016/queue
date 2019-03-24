package com.spider.queue.disruptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class MessageEventProducer {
	
	private final RingBuffer<MessageEvent> ringBuffer;
	
	private final static Logger LOGGER = LogManager.getLogger(MessageEventProducer.class);
	
	public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}

	private static final EventTranslatorOneArg<MessageEvent,MessageEvent> TRANSLATOR = new EventTranslatorOneArg<MessageEvent, MessageEvent>() {

		@Override
		public void translateTo(MessageEvent event, long sequence, MessageEvent msgEvent) {
			LOGGER.info("message put in queue:[{}]" , msgEvent.getMessage());
			event.setMessage(msgEvent.getMessage());
		}
	};
	
	public void put(MessageEvent message){
		ringBuffer.publishEvent(TRANSLATOR, message);
	}
}
