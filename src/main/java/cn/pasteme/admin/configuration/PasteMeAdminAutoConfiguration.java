package cn.pasteme.admin.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucien
 * @version 1.0.1
 */
@Configuration
@ComponentScan(value = {"cn.pasteme.admin", "cn.pasteme.common", "cn.pasteme.algorithm"})
public class PasteMeAdminAutoConfiguration {
}
