package com.example.demo;


import java.util.Collection;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Item.ItemClient;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy


@RestController

public class GoodItemApiAdapterRestController {

	
	private final ItemClient itemClient;

	public GoodItemApiAdapterRestController(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

	public ItemClient getItemClient() {
		return itemClient;
	}
	
		
	 @GetMapping("/top-brands")	 
	 public Collection<Item> goodItems() {
	        return itemClient.readItems()
	                .getContent()
	                .stream()
	                .filter(this::isGreat)
	                .collect(Collectors.toList());
	    }
	 
	 private boolean isGreat(Item item) {
	        return !item.getName().equals("Nike") &&
	                !item.getName().equals("Adidas") &&
	                !item.getName().equals("Reebok");
	    }
	
}
