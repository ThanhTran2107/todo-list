package todo.list.auth.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import todo.list.auth.dto.request.*;
import todo.list.auth.dto.response.LoginResponse;
import todo.list.auth.dto.response.UserInformationResponse;
import todo.list.auth.entity.PasswordResetToken;
import todo.list.auth.service.ForgotPasswordService;
import todo.list.common.dto.ErrorResponse;
import todo.list.common.dto.MessageResponse;
import todo.list.common.exception.InvalidTokenException;
import todo.list.common.exception.TooManyTokenRequestException;
import todo.list.user.entity.User;
import todo.list.user.service.UserService;

import java.util.Optional;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
    @Inject
    UserService userService;

    @Inject
    ForgotPasswordService forgotPasswordService;

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
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode()))
                    .build();
        }
    }

    @POST
    @Path("/google")
    @APIResponse(responseCode = "200", description = "User login via Google", content = @Content(mediaType = "application/json"))
    public Response loginWithGoogle(@Valid GoogleLoginRequest request) {
        try {
            if (request.getAccessToken() == null || request.getAccessToken().isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Google login requires accessToken",
                                Response.Status.BAD_REQUEST.getStatusCode()))
                        .build();
            }

            JsonObject payload = fetchGoogleUserInfo(request.getAccessToken());
            String email = payload.getString("email", null);

            if (email == null || email.isBlank()) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorResponse("Google token is invalid or does not contain email",
                                Response.Status.UNAUTHORIZED.getStatusCode()))
                        .build();
            }

            String token = userService.loginOrRegisterSocial(email, payload.getString("name", email),
                    payload.getString("picture", null));

            return Response.ok(new LoginResponse(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Google login failed: " + e.getMessage(),
                            Response.Status.UNAUTHORIZED.getStatusCode()))
                    .build();
        }
    }

    @POST
    @Path("/facebook")
    @APIResponse(responseCode = "200", description = "User login via Facebook", content = @Content(mediaType = "application/json"))
    public Response loginWithFacebook(@Valid FacebookLoginRequest request) {
        try {
            JsonObject profile = fetchFacebookProfile(request.getUserId(), request.getAccessToken());
            String email = profile.getString("email", null);

            if (email == null || email.isBlank()) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorResponse("Facebook login failed: email not available",
                                Response.Status.UNAUTHORIZED.getStatusCode()))
                        .build();
            }

            String token = userService.loginOrRegisterSocial(email, profile.getString("name", email),
                    profile.getJsonObject("picture") != null
                            ? profile.getJsonObject("picture").getJsonObject("data").getString("url", null)
                            : null);

            return Response.ok(new LoginResponse(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Facebook login failed: " + e.getMessage(),
                            Response.Status.UNAUTHORIZED.getStatusCode()))
                    .build();
        }
    }

    @POST
    @Path("/forgot-password")
    @APIResponse(responseCode = "200", description = "Forgot password", content = @Content(mediaType = "application/json"))
    public Response forgotPassword(@Valid ForgotPasswordRequest request) {
        try {
            String email = request.getEmail();
            // Kiểm tra email có tồn tại trên hệ thống không
            Optional<User> user = userService.findByEmail(email);
            if (user.isEmpty()) {
                return Response.ok().build();
            }
            // Tạo token
            PasswordResetToken passwordResetToken = forgotPasswordService.createToken(user.get());
            // Gửi reset link + token cho email
            forgotPasswordService.sendMessageToEmail(email, passwordResetToken.token);
            return Response.ok().build();
        } catch (TooManyTokenRequestException ex) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(new ErrorResponse(ex.getMessage(), Response.Status.FORBIDDEN.getStatusCode()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("API failed: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
    }

    @GET
    @Path("/help-with-reset-password")
    @APIResponse(responseCode = "200", description = "Help With Reset Password - Load User Info")
    public Response helpWithResetPassword(@QueryParam("token") @NotBlank String token) {
        try {
            // Lấy thông tin user
            User user = forgotPasswordService.getUserInfoByToken(token);
            // Trả user về để cho trang reset password
            UserInformationResponse userInformationResponse = new UserInformationResponse(user.id, user.email);
            return Response.status(Response.Status.OK)
                    .entity(userInformationResponse)
                    .build();
        } catch (InvalidTokenException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("API failed: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
    }

    @POST
    @Path("/reset-password")
    @APIResponse(responseCode = "200", description = "Reset password", content = @Content(mediaType = "application/json"))
    public Response resetPassword(@Valid ResetPasswordRequest request) {
        try {
            forgotPasswordService.resetPassword(request.getToken(), request.getNewPassword());
            return Response.ok().build();
        } catch (InvalidTokenException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("API failed: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
    }

    private JsonObject fetchGoogleUserInfo(String accessToken) throws Exception {
        String url = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + accessToken;
        JsonObject data = fetchJson(url);

        if (!data.containsKey("email"))
            throw new IllegalArgumentException("Invalid Google profile data");

        return data;
    }

    private JsonObject fetchFacebookProfile(String userId, String accessToken) throws Exception {
        String url = String.format("https://graph.facebook.com/%s?access_token=%s&fields=id,name,email,picture", userId,
                accessToken);
        JsonObject data = fetchJson(url);

        if (!data.containsKey("id") || !data.containsKey("email"))
            throw new IllegalArgumentException("Invalid Facebook profile data");

        return data;
    }

    private JsonObject fetchJson(String urlString) throws Exception {
        java.net.URL url = new java.net.URL(urlString);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();

        try (java.io.InputStream is = status == 200 ? conn.getInputStream() : conn.getErrorStream();
                JsonReader reader = Json.createReader(is)) {
            JsonObject json = reader.readObject();
            if (status != 200)
                throw new IllegalArgumentException("External provider verification failed: " + json.toString());

            return json;
        } finally {
            conn.disconnect();
        }
    }

    @POST
    @Path("/logout")
    @RolesAllowed("User")
    @APIResponse(responseCode = "200", description = "User logout successfully", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "LogoutResponse Example", value = "{\n"
            +
            "   \"message\": \"Logged out successfully\"\n" +
            "}")))
    public Response logout() {
        return Response.ok().entity(new MessageResponse("Logged out successfully!"))
                .build();
    }
}
