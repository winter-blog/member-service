package com.devwinter.memberservice.adapter.input.api;

import com.epages.restdocs.apispec.HeaderDescriptorWithType;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.constraints.ResourceBundleConstraintDescriptionResolver;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestPartDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestPartBody;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;

@AutoConfigureRestDocs
public abstract class AbstractRestDocs {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    protected static final String BASE_URL = "/api/v1/members";

//    @BeforeEach
//    void setup() throws ServletException {
//        DelegatingFilterProxy delegateProxyFilter = new DelegatingFilterProxy();
//        delegateProxyFilter.init(new MockFilterConfig(context.getServletContext(), "JwtTokenFilter"));
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
//                                      .addFilter(delegateProxyFilter)
//                                      .build();
//    }

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    protected String generateAccessToken() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    protected String requestToJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    protected HttpHeaders auth() {
        HttpHeaders header = new HttpHeaders();
        header.set("MemberId", String.valueOf(1));
        header.setBearerAuth(generateAccessToken());
        return header;
    }

    protected RestDocumentationResultHandler document(
            MemberApiDocumentInfo documentInfo,
            Class<?> requestClass,
            Class<?> responseClass,
            List<FieldDescriptorDto> requestFields,
            List<FieldDescriptorDto> responseFields) {

        return MockMvcRestDocumentation.document(
                documentInfo.getIdentifier(),
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                        snippet(documentInfo, requestClass, responseClass, requestFields, responseFields)
                )
        );
    }

    protected RestDocumentationResultHandler document(
            MemberApiDocumentInfo documentInfo,
            Class<?> responseClass,
            List<RequestPartDescriptor> requestParts,
            List<FieldDescriptorDto> responseFields) {

        return MockMvcRestDocumentation.document(
                documentInfo.getIdentifier(),
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestParts(requestParts),
                resource(
                        snippet(documentInfo, StandardMultipartHttpServletRequest.class, responseClass, null, responseFields)
                )
        );
    }

    private ResourceSnippetParameters snippet(MemberApiDocumentInfo documentInfo, Class<?> requestClass, Class<?> responseClass, List<FieldDescriptorDto> requestFields, List<FieldDescriptorDto> responseFields) {

        return ResourceSnippetParameters.builder()
                                        .tag(documentInfo.getTag())
                                        .summary(documentInfo.getSummary())
                                        .description(documentInfo.getDescription())
                                        .requestSchema((requestClass != null) ? schema(requestClass.getName()) : null)
                                        .responseSchema((responseClass != null) ? schema(responseClass.getName()) : null)
                                        .requestFields((requestFields != null) ? fields(requestFields) : new ArrayList<>())
                                        .responseFields((responseFields != null) ? fields(responseFields) : new ArrayList<>())
                                        .requestHeaders(
                                                (documentInfo.isCertification()) ?
                                                List.of(
                                                        new HeaderDescriptorWithType("MemberId").description("회원 Id")
                                                        // new HeaderDescriptorWithType(AUTHORIZATION).description("JWT Access Token")
                                                )
                                                : Collections.emptyList()
                                        )
                                        .build();
    }

    private List<FieldDescriptor> fields(List<FieldDescriptorDto> fieldDescriptors) {
        return fieldDescriptors.stream()
                               .map(field ->
                                       fieldWithPath(field.path())
                                               .type(field.fieldType())
                                               .description(field.description()))
                               .collect(Collectors.toList());
    }

    private ConstraintDescriptions getConstraintDescriptions(Class<?> cls) {
        ResourceBundleConstraintDescriptionResolver fallback = new ResourceBundleConstraintDescriptionResolver();
        return new ConstraintDescriptions(cls, (constraint) -> {
            String message = (String) constraint.getConfiguration()
                                                .get("message");
            if (message != null) {
                return message;
            }
            return fallback.resolveDescription(constraint);
        });
    }

    protected record FieldDescriptorDto(
            String path,
            JsonFieldType fieldType,
            String description,
            Attributes.Attribute attributes) {
        static FieldDescriptorDto descriptor(String path, JsonFieldType fieldType, String description) {
            return new FieldDescriptorDto(path, fieldType, description, null);
        }

        static FieldDescriptorDto descriptor(String path, JsonFieldType fieldType, String description, Attributes.Attribute attributes) {
            return new FieldDescriptorDto(path, fieldType, description, attributes);
        }
    }
}
