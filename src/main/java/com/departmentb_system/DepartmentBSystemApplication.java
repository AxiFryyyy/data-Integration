package com.departmentb_system;

import com.departmentb_system.socket.ClientB_Receive_Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DepartmentBSystemApplication {
    

    public static void main(String[] args) {
        SpringApplication.run(DepartmentBSystemApplication.class, args);

    }

    @Bean
    public CommandLineRunner dataLoader(ClientB_Receive_Socket clientB_receive_socket) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                try{
                    Thread thread = new Thread(clientB_receive_socket);
                    thread.start();
                } catch ( Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

}
