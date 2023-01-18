package com.team.semiTravelRecommend.controller.record;

import com.team.semiTravelRecommend.config.MybatisConfig;
import com.team.semiTravelRecommend.config.SemiTravelRecommendApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ContextConfiguration(classes = {SemiTravelRecommendApplication.class, MybatisConfig.class, ContextConfiguration.class})
class RecordControllerTest {

    @Autowired
    private RecordController recordController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(recordController).build();
    }

    @Test
    public void testInit(){
        assertNotNull(recordController);
        assertNotNull(mockMvc);
//        정상작동 확인 완료 23.01.02
    }



//    @Test
//    void 여행기록물_한개_조회용_컨트롤러_동작_확인() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/record/RecordDetail")) //요청오는 url
////                .andExpect(MockMvcResultMatchers.status().isOk()) // 확인할 상태값 // andExpet : 응답결과 검증을 위한 메소드
//                .andExpect(MockMvcResultMatchers.forwardedUrl("/record/RecordDetail")) // 포워드해주는 url 확인
//                .andDo(MockMvcResultHandlers.print()); // andDo : 추가로 실행할 메소드 작성
//
//    }


}