package com.server.integratedserver;

import com.server.integratedserver.webSocket.Server_Socket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IntegratedServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratedServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(Server_Socket serverSocket) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                try{
                    Thread thread = new Thread(serverSocket);
                    thread.start();
                } catch ( Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

}
