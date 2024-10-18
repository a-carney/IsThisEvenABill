package com.alex.isthisevenabill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IsThisActuallyABillApplication {
    public static void main(String[] args) {
        SpringApplication.run(IsThisActuallyABillApplication.class, args);
    }
}
