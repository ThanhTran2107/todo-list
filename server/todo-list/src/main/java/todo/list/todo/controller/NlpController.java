package todo.list.todo.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import todo.list.common.dto.ErrorResponse;
import todo.list.todo.dto.request.ParsePromptRequest;
import todo.list.todo.dto.response.ParsePromptResponse;
import todo.list.todo.service.nlp.NlpService;

@Path("/nlp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("User")
public class NlpController {
    @Inject
    NlpService nlpService;

    private static final String PARSE_RESPONSE_EXAMPLE = "{\n" +
            "  \"intent\": \"CREATE\",\n" +
            "  \"task\": {\n" +
            "    \"title\": \"họp nhóm\",\n" +
            "    \"description\": \"Thêm họp nhóm lúc 9h sáng mai, ưu tiên cao\",\n" +
            "    \"dueDate\": \"2026-04-07T09:00:00Z\",\n" +
            "    \"priority\": \"HIGH\",\n" +
            "    \"status\": \"PENDING\",\n" +
            "    \"completed\": false\n" +
            "  }\n" +
            "}";

    @POST
    @Path("/parse")
    @APIResponse(responseCode = "200", description = "Parsed prompt result", content = @Content(mediaType = MediaType.APPLICATION_JSON, examples = @ExampleObject(name = "ParsePromptResponse Example", value = PARSE_RESPONSE_EXAMPLE)))
    public Response parsePrompt(@Valid ParsePromptRequest request) {
        System.out.println("[NlpController] parsePrompt request received. prompt=" + request.getPrompt());
        try {
            ParsePromptResponse response = nlpService.parsePrompt(request.getPrompt());

            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode()))
                    .build();
        }
    }
}
