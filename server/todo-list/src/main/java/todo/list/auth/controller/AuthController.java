package todo.list.auth.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import todo.list.auth.dto.request.LoginRequest;
import todo.list.auth.dto.request.RegisterRequest;
import todo.list.auth.dto.response.LoginResponse;
import todo.list.common.dto.ErrorResponse;
import todo.list.common.dto.MessageResponse;
import todo.list.user.service.UserService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
    @Inject
    UserService userService;

    @POST
    @Path("/register")
    @APIResponse(responseCode = "200", description = "User register successfully", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "RegisterResponse Example", value = "{\n"
            +
            "   \"message\": \"Registered Successfully!\"\n" +
            "}")))
    public Response register(@Valid RegisterRequest request) {
        try {
            userService.register(request.getEmail(), request.getPassword());

            return Response.ok().entity(new MessageResponse("Registered Successfully!"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode()))
                    .build();
        }
    }

    @POST
    @Path("/login")
    @APIResponse(responseCode = "200", description = "User login successfully", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "LoginResponse Example", value = "{\n"
            +
            "   \"token\": \"string\"\n" +
            "}")))
    public Response login(@Valid LoginRequest request) {
        try {
            String token = userService.login(request.getEmail(), request.getPassword());

            return Response.ok(new LoginResponse(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse(e.getMessage(), Response.Status.UNAUTHORIZED.getStatusCode()))
                    .build();
        }
    }
}
