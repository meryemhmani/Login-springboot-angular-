package Repository;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>

    {
        @Override
        Optional<User> findById(Long aLong);

        @Override
        <S extends User> List<S> saveAll(Iterable<S> entities);

        @Override
        <S extends User> S save(S entity);

        @Override
        void deleteById(Long aLong);

        @Override
        void delete(User entity);

        @Override
        void deleteAll();

        @Override
        <S extends User> boolean exists(Example<S> example);

        @Override
        boolean existsById(Long aLong);


         List<User> findByFirstNameLike(String firstName);

         boolean existsByEmail(String email);

         Optional<User> findByUsername(String username);

          }
