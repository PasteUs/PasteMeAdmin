package cn.pasteme.admin.entity;

import cn.pasteme.admin.dto.AbstractRiskCheckResultDTO;
import cn.pasteme.admin.dto.IntegerRiskCheckResultDTO;
import cn.pasteme.admin.dto.PairListRiskCheckResultDTO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RiskCheckResultDOTest {

    @Test
    public void testInteger() {
        IntegerRiskCheckResultDTO riskCheckResultDTO = new IntegerRiskCheckResultDTO(
                100L,
                RiskCheckResultType.TEXT_CLASSIFICATION,
                0
        );

        assertNotNull(riskCheckResultDTO);
        assertEquals(Long.valueOf(100L), riskCheckResultDTO.getKey());
        assertEquals(RiskCheckResultType.TEXT_CLASSIFICATION, riskCheckResultDTO.getType());
        assertEquals(Integer.valueOf(0), riskCheckResultDTO.getResult());
    }

    @Test
    public void testPairList() {
        PairListRiskCheckResultDTO riskCheckResultDTO = new PairListRiskCheckResultDTO(
                100L,
                RiskCheckResultType.KEYWORD_COUNT,
                new ArrayList<>()
        );

        assertNotNull(riskCheckResultDTO);
        assertEquals(Long.valueOf(100L), riskCheckResultDTO.getKey());
        assertEquals(RiskCheckResultType.KEYWORD_COUNT, riskCheckResultDTO.getType());
        assertEquals(new ArrayList<>(), riskCheckResultDTO.getResult());
    }

    @Test
    public void test() {
        ArrayList<Pair<String, Long>> list = new ArrayList<>();
        list.add(new Pair<>("Hello", 1L));
        list.add(new Pair<>("World!", 2L));

        AbstractRiskCheckResultDTO abstractRiskCheckResultDTO = new PairListRiskCheckResultDTO(
                100L,
                RiskCheckResultType.KEYWORD_COUNT,
                list
        );

        System.out.println(JSON.toJSONString(abstractRiskCheckResultDTO));
    }
}