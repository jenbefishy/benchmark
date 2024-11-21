package com.example.benchmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.openjdk.jmh.Main;

import java.io.IOException;

@SpringBootApplication
public class BenchmarkApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(BenchmarkApplication.class, args);
		Main.main(args);
	}

}
