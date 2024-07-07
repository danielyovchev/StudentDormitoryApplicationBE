package bg.tu_varna.sit;

import bg.tu_varna.sit.model.dto.RuleDTO;
import bg.tu_varna.sit.model.rule.UpdateRulesRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class RuleResourceTest {

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testGetRules() {
        given()
                .when().get("/rule/get")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(notNullValue());
    }

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    public void testUpdateRules() {
        RuleDTO rule = RuleDTO.builder()
                .id(1L)
                .name("Rule001")
                .description("This is a sample rule")
                .isActive(true)
                .defaultScore(100)
                .build();

        UpdateRulesRequest request = UpdateRulesRequest.builder()
                .rules(Collections.singletonList(rule))
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().put("/rule/update")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", is(notNullValue()));
    }


    @Test
    @TestSecurity(user = "user", roles = {"user"})
    public void testGetRulesForbidden() {
        given()
                .when().get("/rule/get")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "user", roles = {"user"})
    public void testUpdateRulesForbidden() {
        UpdateRulesRequest request = new UpdateRulesRequest();

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().put("/rule/update")
                .then()
                .statusCode(403);
    }
}