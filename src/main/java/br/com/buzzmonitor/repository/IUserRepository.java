package br.com.buzzmonitor.repository;

import br.com.buzzmonitor.models.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends ElasticsearchRepository<User, UUID> {

    User findByUsername(String username);
}

