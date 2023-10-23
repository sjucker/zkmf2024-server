package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.dto.LoginRequestDTO;
import ch.zkmf2024.server.dto.LoginResponseDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.security.JwtService;
import ch.zkmf2024.server.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static ch.zkmf2024.server.dto.UserRole.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AuthEndpointTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        userRepository.insert(new Zkmf2024UserPojo("email", ADMIN.name(), "{noop}pass1234", null, DateUtil.now(), null, null, null));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void login() throws Exception {
        var request = new LoginRequestDTO("email", "pass1234");

        var mvcResult = mockMvc.perform(post("/public/auth")
                                                .contentType(APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(request)))
                               .andDo(print())
                               .andExpect(status().isOk())
                               .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                               .andExpect(jsonPath("$.email", equalTo("email")))
                               .andExpect(jsonPath("$.role", equalTo("ADMIN")))
                               .andExpect(jsonPath("$.jwt", is(notNullValue())))
                               .andReturn();

        var response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), LoginResponseDTO.class);
        assertThat(jwtService.validateJwt(response.jwt())).isNotEmpty();
        assertThat(jwtService.validateJwt(response.jwt().repeat(2))).isEmpty();
    }

    @Test
    void login_Unauthorized() throws Exception {
        var request = new LoginRequestDTO("email", "pass123");

        mockMvc.perform(post("/public/auth")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(request)))
               .andDo(print())
               .andExpect(status().isUnauthorized());

    }
}
