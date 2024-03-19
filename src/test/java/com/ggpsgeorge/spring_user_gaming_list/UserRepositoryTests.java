package com.ggpsgeorge.spring_user_gaming_list;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    
    @Autowired UserRepository userRepository;

    User testUser = User.builder()
        .id(1L)
        .userName("son_goku")
        .email("sonGoku@dbz.com")
        .password("Kamehameha")
        .games(new ArrayList<Game>())
        .build();

    @Test
    public void testSave_shouldReturnUser() {
        User savedUser = userRepository.save(testUser);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.getUserName()).isEqualTo("son_goku");
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("sonGoku@dbz.com");
    }

    @Test
    public void testFindById_shouldReturnUser() {
        User tempUser = userRepository.save(testUser);

        User savedUser = userRepository.findById(tempUser.getId()).get();

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.getUserName()).isEqualTo("son_goku");
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("sonGoku@dbz.com");
    }

    @Test
    public void testFindById_shouldReturnNull() {

        User savedUser = userRepository.findById(0L).orElse(null);

        Assertions.assertThat(savedUser).isNull();
    }

    @Test
    public void testDeleteById() {
        userRepository.save(testUser);

        userRepository.deleteById(testUser.getId());

        Assertions.assertThat(userRepository.findById(testUser.getId())).isEqualTo(Optional.empty());
    }
}
