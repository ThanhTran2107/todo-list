package todo.list.auth.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import todo.list.auth.dto.response.EmailResponse;
import todo.list.auth.entity.PasswordResetToken;
import todo.list.auth.repository.PasswordResetTokenRepository;
import todo.list.common.exception.InvalidTokenException;
import todo.list.common.exception.TooManyTokenRequestException;
import todo.list.common.security.PasswordEncoder;
import todo.list.user.entity.User;
import todo.list.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ForgotPasswordService {
    @Inject
    UserRepository userRepository;

    @Inject
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Inject
    Mailer mailer;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    @Location("reset-password-email.html")
    Template template;

    @Transactional
    public PasswordResetToken createToken(User user) throws Exception {
        Optional<PasswordResetToken> existing = passwordResetTokenRepository.findByUser(user);
        if (existing.isPresent()) {
            PasswordResetToken oldToken = existing.get();
            if (oldToken.createdDate.plusSeconds(60).isAfter(LocalDateTime.now())) {
                throw new TooManyTokenRequestException("Too many Token request, please wait 60s before sending.");
            }
        }
        passwordResetTokenRepository.deleteByUser(user);
        PasswordResetToken passwordResetToken = new PasswordResetToken(null, UUID.randomUUID().toString(),
                LocalDateTime.now().plusMinutes(30), user,
                LocalDateTime.now());
        passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
        return passwordResetToken;
    }

    public void sendMessageToEmail(String email, String token) {
        String resetLink = "http://localhost:3000/resetPassword?token=" + token;
        EmailResponse emailResponse = new EmailResponse(email, resetLink);
        String htmlContent = template.data("emailResponse", emailResponse).render();
        mailer.send(Mail.withHtml(email, "Account recovery", htmlContent));
    }

    public User getUserInfoByToken(String token) throws Exception {
        PasswordResetToken passwordResetToken = getTokenIfValid(token);
        if (passwordResetToken == null) {
            throw new InvalidTokenException("Invalid token or expired. ");
        }
        return passwordResetToken.user;
    }

    @Transactional
    public void resetPassword(String token, String newPassword) throws Exception {
        if (newPassword.isBlank() || newPassword.length() < 10) {
            throw new Exception("Password must be at least 10 characters long");
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).*$";
        if (!newPassword.matches(passwordRegex)) {
            throw new Exception(
                    "Password must contain at least one number, one lowercase letter, one uppercase letter, and one special character");
        }
        PasswordResetToken passwordResetToken = getTokenIfValid(token);
        User user = passwordResetToken.user;
        user.passwordHash = passwordEncoder.hash(newPassword);
        userRepository.saveUser(user);
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    private PasswordResetToken getTokenIfValid(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }
        Optional<PasswordResetToken> optional = passwordResetTokenRepository.findByToken(token);
        if (optional.isEmpty() || optional.get().isExpired()) {
            return null;
        }
        return optional.get();
    }
}
