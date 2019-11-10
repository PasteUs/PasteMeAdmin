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
public class Database implements CommandLineRunner {

    private final TableMapper tableMapper;

    public Database(TableMapper tableMapper) {
        this.tableMapper = tableMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        tableMapper.createPasteMeAdminRiskState();
        tableMapper.createPasteMeAdminDictionary();
        tableMapper.createPasteMeAdminAccessCount();
        tableMapper.createPasteMeAdminRiskCheckResult();
    }
}
