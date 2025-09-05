package com.example.oca42.repository;

import com.example.oca42.entity.UserAccount;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findOneById(Long id);

    @EntityGraph(attributePaths = {"contact", "addresses", "roles" })
Optional<UserAccount> getUserAccountById(long id);

    //BURDA OZUMUZ YAZA BILERIK QUERYNI
    @Query(value = """
           select UserAccount
        from UserAccount us1_0
            left join us1_0.contact
            left join us1_0.addresses
        where us1_0.id = :id
        """)
    Optional<UserAccount> getUserAccountByIdQuery(@Param("id") Long id);


    boolean existsByUsername(String username);
}
