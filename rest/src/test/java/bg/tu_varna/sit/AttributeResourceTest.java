package bg.tu_varna.sit;

import bg.tu_varna.sit.model.attribute.GetAttributesRequest;
import bg.tu_varna.sit.model.attribute.GetAttributesResponse;
import bg.tu_varna.sit.model.attribute.UpdateAttributesRequest;
import bg.tu_varna.sit.model.attribute.UpdateAttributesResponse;
import bg.tu_varna.sit.model.dto.AttributeDTO;
import bg.tu_varna.sit.operation.attribute.GetAttributesOperation;
import bg.tu_varna.sit.operation.attribute.UpdateAttributesOperation;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@QuarkusTest
public class AttributeResourceTest {

    @InjectMock
    GetAttributesOperation getAttributesOperation;

    @InjectMock
    UpdateAttributesOperation updateAttributesOperation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    @TestSecurity(user = "admin", roles = {"admin"})
//    @TestTransaction
//    public void testUpdateAttributes() {
//        AttributeDTO attribute1 = AttributeDTO.builder()
//                .id(1L)
//                .name("Attribute 1")
//                .description("Description 1")
//                .defaultValue("Default 1")
//                .build();
//
//        AttributeDTO attribute2 = AttributeDTO.builder()
//                .id(2L)
//                .name("Attribute 2")
//                .description("Description 2")
//                .defaultValue("Default 2")
//                .build();
//
//        List<AttributeDTO> attributes = Arrays.asList(attribute1, attribute2);
//
//        UpdateAttributesRequest request = new UpdateAttributesRequest();
//        request.setAttributes(attributes);
//
//        UpdateAttributesResponse response = new UpdateAttributesResponse("Successful update");
//        //response.setMessage("Attributes updated successfully");
//
//        when(updateAttributesOperation.process(request)).thenReturn(Either.right(response));
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(request)
//                .when().put("/attributes/update")
//                .then()
//                .statusCode(200)
//                .body("message", is("Attributes updated successfully"));
//
//        verify(updateAttributesOperation).process(request);
//    }

    @Test
    @TestSecurity(user = "admin", roles = {"admin"})
    @TestTransaction
    public void testGetAttributes() {
        AttributeDTO attribute1 = AttributeDTO.builder()
                .id(1L)
                .name("Attribute 1")
                .description("Description 1")
                .defaultValue("Default 1")
                .build();

        AttributeDTO attribute2 = AttributeDTO.builder()
                .id(2L)
                .name("Attribute 2")
                .description("Description 2")
                .defaultValue("Default 2")
                .build();

        List<AttributeDTO> attributes = Arrays.asList(attribute1, attribute2);

        GetAttributesResponse response = new GetAttributesResponse(attributes);
        //response.setAttributes(attributes);

        when(getAttributesOperation.process(any(GetAttributesRequest.class))).thenReturn(Either.right(response));

        given()
                .when().get("/attributes/get")
                .then()
                .statusCode(200)
                .body("attributes", hasSize(2));
//                .body("attributes[0].id", is(1))
//                .body("attributes[0].name", is("Attribute 1"))
//                .body("attributes[0].description", is("Description 1"))
//                .body("attributes[0].defaultValue", is("Default 1"))
//                .body("attributes[1].id", is(2))
//                .body("attributes[1].name", is("Attribute 2"))
//                .body("attributes[1].description", is("Description 2"))
//                .body("attributes[1].defaultValue", is("Default 2"));

        verify(getAttributesOperation).process(any(GetAttributesRequest.class));
    }
}