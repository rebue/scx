package rebue.scx.orp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrpSbSvrApplication {

    public static void main(String[] args) {
        try {

            SpringApplication.run(OrpSbSvrApplication.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
