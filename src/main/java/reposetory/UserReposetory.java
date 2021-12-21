package reposetory;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserReposetory extends JpaRepository<User, Long>
{

    User findByUsername(String username);

}
