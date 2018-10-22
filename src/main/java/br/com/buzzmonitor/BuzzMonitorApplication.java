package br.com.buzzmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "br.com.buzzmonitor.endpoints")
public class BuzzMonitorApplication {

    public static void main(String[] args) {

        SpringApplication.run(BuzzMonitorApplication.class, args);
    }
}
