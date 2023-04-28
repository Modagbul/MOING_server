package com.modagbul.BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeApplication.class, args);

		// 메모리 사용량 출력
		long heapSize = Runtime.getRuntime().totalMemory();
		System.out.println("HEAP Size(M) : "+ heapSize / (1024*1024) + " MB");
	}

}
