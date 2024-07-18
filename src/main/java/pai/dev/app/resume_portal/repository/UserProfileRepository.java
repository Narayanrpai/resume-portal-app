package pai.dev.app.resume_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pai.dev.app.resume_portal.models.User;
import pai.dev.app.resume_portal.models.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName);
}
