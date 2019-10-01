package cn.pasteme.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lucienshui
 * @date 2019/9/30 00:42
 */
@SpringBootApplication
@ComponentScan({"cn.pasteme.admin", "cn.pasteme.common"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
