package cn.pasteme.admin.controller;

import cn.pasteme.admin.mapper.PasteAdminTestMapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PasteAdminTestMapper pasteAdminTestMapper;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void main() throws Exception {
        pasteAdminTestMapper.delete("pasteme_admin_access_count", 101L);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/paste/101")).andReturn();
        Assert.assertEquals("{\"code\":0,\"message\":\"success\",\"data\":null,\"success\":true}", mvcResult.getResponse().getContentAsString());

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/paste/101")).andReturn();
        Assert.assertEquals("{\"code\":0,\"message\":\"success\",\"data\":null,\"success\":true}", mvcResult.getResponse().getContentAsString());
    }
}
