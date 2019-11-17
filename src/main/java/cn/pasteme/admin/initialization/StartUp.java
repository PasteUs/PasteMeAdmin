package cn.pasteme.admin.initialization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Component
@Slf4j
public class StartUp implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("\n _____          _       __  __                    _           _       \n" +
                "|  __ \\        | |     |  \\/  |          /\\      | |         (_)      \n" +
                "| |__) |_ _ ___| |_ ___| \\  / | ___     /  \\   __| |_ __ ___  _ _ __  \n" +
                "|  ___/ _` / __| __/ _ \\ |\\/| |/ _ \\   / /\\ \\ / _` | '_ ` _ \\| | '_ \\ \n" +
                "| |  | (_| \\__ \\ ||  __/ |  | |  __/  / ____ \\ (_| | | | | | | | | | |\n" +
                "|_|   \\__,_|___/\\__\\___|_|  |_|\\___| /_/    \\_\\__,_|_| |_| |_|_|_| |_|\n");
    }
}
