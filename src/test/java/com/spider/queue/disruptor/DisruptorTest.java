package com.spider.queue.disruptor;

import org.junit.Before;
import org.junit.Test;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class DisruptorTest {
	Disruptor<MessageEvent> disruptor = null;
	
	@Before
	public void init(){
		MessageEventFactory factory = new MessageEventFactory();
		int bufferSize = 1024;
		disruptor = new Disruptor<MessageEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE);
		disruptor.handleEventsWith(new MessageEventHandler());
		disruptor.start();
	}
	@Test
	public void productMessage(){
		RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();
		MessageEventProducer producer = new MessageEventProducer(ringBuffer);
		String msg = "this is messg in sequence";
		for(int i = 0;i < 10;i++){
			MessageEvent messageEvent = new MessageEvent();
			messageEvent.setMessage(msg + i);
			producer.put(messageEvent);
		}
		 
	}
}
