package com.spider.queue.disruptor;

import org.junit.Before;
import org.junit.Test;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class MessageEventTest {
	MessageEventProducer producer = null;	
	@Before
	public void init(){
		int bufferSize = 1024;//bufferSize必须是2的幂
		Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(), bufferSize, ProducerType.SINGLE,DaemonThreadFactory.INSTANCE);
		disruptor.handleEventsWith(new MessageEventHandler()).then(new MessageEventClearHandler());//定义由谁消费信息
//		disruptor.handleEventsWith(new MessageEventHandler());//定义由谁消费信息
		disruptor.start();
		producer = new MessageEventProducer(disruptor.getRingBuffer());
	}
	@Test
	public void productMessage(){
		String msg = "this is messg in sequence";
		for(int i = 0;i < 10;i++){
			MessageEvent messageEvent = new MessageEvent();
			messageEvent.setMessage(msg + i);
			producer.put(messageEvent);
		}
	}
}
