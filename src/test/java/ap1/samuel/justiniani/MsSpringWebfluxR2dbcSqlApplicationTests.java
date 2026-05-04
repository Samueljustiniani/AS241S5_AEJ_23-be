package ap1.samuel.justiniani;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.r2dbc.url=r2dbc:postgresql://localhost:5432/testdb",
    "spring.r2dbc.username=test",
    "spring.r2dbc.password=test",
    "spring.sql.init.mode=never",
    "ia.llama.url=http://localhost:9999/mock",
    "ia.llama.key=test",
    "ia.tts.url=http://localhost:9999/mock",
    "ia.tts.key=test"
})
class MsSpringWebfluxR2dbcSqlApplicationTests {

    @Test
    void contextLoads() {
    }

}