package com.example.demo;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Data;

@EnableFeignClients

@Data //to sting mehods

public class Item {
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@FeignClient("item-catalog-service")
	public interface ItemClient {
		 
		 @GetMapping("/items")
		 Resources<Item> readItems();			
			 
		 }
	public Item(){}

}
