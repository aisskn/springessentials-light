package org.formation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {
	private String topicName = "TICKET";
	 @Autowired
	 public KafkaTemplate <Long, TicketEvent> kafkaTemplate;

	public void notify(TicketEvent ticketEvent) {

		kafkaTemplate.send(topicName,ticketEvent);

	}
}
