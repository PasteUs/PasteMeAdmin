package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskDictionaryType;

import lombok.Data;

import java.util.List;

/**
 * @author Lucien
 * @version 1.1.0
 */
@Data
public class RiskDictionaryDO {

    Long id;

    RiskDictionaryType type;

    List<String> dictionary;
}
