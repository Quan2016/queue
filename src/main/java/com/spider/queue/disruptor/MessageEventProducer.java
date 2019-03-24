package com.spider.queue.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class MessageEventProducer {
	
	private final RingBuffer<MessageEvent> ringBuffer;
	
	public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer){
		this.ringBuffer = ringBuffer;
	}

	private static final EventTranslatorOneArg<MessageEvent,MessageEvent> TRANSLATOR = new EventTranslatorOneArg<MessageEvent, MessageEvent>() {

		@Override
		public void translateTo(MessageEvent event, long sequence, MessageEvent msgEvent) {
			System.out.println(msgEvent.getMessage());
			System.out.println(event.getMessage());
			event.setMessage(msgEvent.getMessage());
		}
	};
	
	public void put(MessageEvent message){
		ringBuffer.publishEvent(TRANSLATOR, message);
	}
}
