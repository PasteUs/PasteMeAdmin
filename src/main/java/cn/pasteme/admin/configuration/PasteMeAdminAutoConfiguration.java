package cn.pasteme.admin.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Configuration
@ComponentScan(value = {"cn.pasteme.admin", "cn.pasteme.common"})
public class PasteMeAdminAutoConfiguration {
}
