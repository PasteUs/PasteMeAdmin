package cn.pasteme.admin.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Data
public class RiskDictionaryDO {

    Long id;

    List<String> dictionary;
}
