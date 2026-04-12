package todo.list.auth.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import todo.list.auth.entity.PasswordResetToken;
import todo.list.user.entity.User;

import java.util.Optional;

@ApplicationScoped
public class PasswordResetTokenRepository implements PanacheRepository<PasswordResetToken> {

    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        persist(passwordResetToken);
        return passwordResetToken;
    }

    public Optional<PasswordResetToken> findByUser(User user) {
        return find("user", user).firstResultOptional();
    }

    public Optional<PasswordResetToken> findByToken(String token) {
        return find("token", token).firstResultOptional();
    }

    public void deleteByUser(User user) {
        delete("user", user);
    }
}
