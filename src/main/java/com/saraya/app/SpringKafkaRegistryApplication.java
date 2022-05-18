package com.saraya.app;

import com.github.javafaker.Faker;

//import com.saraya.HobbitQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


import java.time.Duration;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringKafkaRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaRegistryApplication.class, args);
	}

}


@Component
class Producer{
	//@Autowired
	//private KafkaTemplate<Integer, HobbitQ> template;

	Faker faker;

	@EventListener(ApplicationStartedEvent.class)
	public void generate(){
		faker=Faker.instance();
		Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
		Flux<String> thorinsCompany = Flux.fromStream(Stream.generate(() -> faker.hobbit().thorinsCompany()));
		//Flux.zip(interval,thorinsCompany).map(it -> template.send("hobbit", faker.random().nextInt(20),
		//		new HobbitQ(it.getT1()))).blockLast();
	}


}
