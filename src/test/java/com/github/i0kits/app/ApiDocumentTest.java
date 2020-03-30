package com.github.i0kits.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class ApiDocumentTest {
  private static final Logger log = LoggerFactory.getLogger(ApiDocumentTest.class);
  private MockMvc mockMvc;

  @BeforeEach
  void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
    log.debug("==========>:" + restDocumentation.beforeOperation().getOutputDirectory());
    this.mockMvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(documentationConfiguration(restDocumentation))
      .build();
  }

  @Test
  void index() throws Exception {
    mockMvc.perform(RestDocumentationRequestBuilders.get("/api/departments"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("application/hal+json"))
      .andDo(document("departments/{methodName}"));
//    this.mockMvc.perform(get("/api/departments"))
//      .andExpect(status().isOk())
//      .andDo(document("department",
//        links(
//          linkWithRel("search").description("The ALPS profile for the service"),
//          linkWithRel("self").description("The <<resources-tags,Tags resource>>"),
//          linkWithRel("profile").description("The <<resources-tags,Tags resource>>")
//        )
//      ));
  }
}
