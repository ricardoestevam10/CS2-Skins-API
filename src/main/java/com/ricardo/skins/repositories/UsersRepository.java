package com.ricardo.skins.repositories;

import com.ricardo.skins.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    //Este metodo permite buscar um usuario atraves do email
    Optional<Users> findByEmail(String email);

    //Este metodo permite verificar se existe mais de uma steamid igual no banco
    boolean existsBySteamId(String steamId);

}