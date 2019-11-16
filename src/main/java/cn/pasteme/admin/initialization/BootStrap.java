package cn.pasteme.admin.initialization;

import cn.pasteme.admin.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Component
public class BootStrap implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");
    }
}
