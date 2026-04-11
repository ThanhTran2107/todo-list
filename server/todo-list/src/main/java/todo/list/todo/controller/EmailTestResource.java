package todo.list.todo.controller;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/test-mail")
public class EmailTestResource {

    @Inject
    Mailer mailer;

    @GET
    public Response testEmail(@QueryParam("to") String toEmail) {
        if (toEmail == null)
            return Response.status(400).entity("Missing 'to' query param").build();

        try {
            mailer.send(Mail.withText(toEmail,
                    "Test Email from TodoApp",
                    "Chào Hieu, nếu bạn nhận được mail này thì cấu hình SMTP đã chuẩn rồi nhé!"));
            return Response.ok("Email sent successfully to " + toEmail).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Failed to send email: " + e.getMessage()).build();
        }
    }
}