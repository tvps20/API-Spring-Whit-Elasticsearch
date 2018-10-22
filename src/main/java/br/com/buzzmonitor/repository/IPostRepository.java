package br.com.buzzmonitor.repository;

import br.com.buzzmonitor.models.Post;
import br.com.buzzmonitor.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public interface IPostRepository extends ElasticsearchRepository<Post, UUID> {

    @Query("{ \"bool\" : { \"must\" : [{ \"match\" : { \"author.username\" : \"?0\" }}, { \"match\" : { \"service\" : \"?1\" }}]}}")
    Page<Post> getByUserAndMedia(String author, String service, Pageable pegeable);
}
