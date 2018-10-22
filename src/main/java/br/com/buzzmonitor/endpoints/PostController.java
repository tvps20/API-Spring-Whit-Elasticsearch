package br.com.buzzmonitor.endpoints;

import br.com.buzzmonitor.erros.ResourceNotFoundException;
import br.com.buzzmonitor.models.Post;
import br.com.buzzmonitor.models.User;
import br.com.buzzmonitor.repository.IPostRepository;
import br.com.buzzmonitor.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("v1")
public class PostController {

    // Injeção de Dependecia e instanciando o atributo
    @Autowired
    private IPostRepository repository;
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("protected/posts/add")
    public ResponseEntity<?> addPost(@RequestBody Post post, Authentication authentication){
        org.springframework.security.core.userdetails.User author = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User usuario = userRepository.findByUsername(author.getUsername());
        post.setId(UUID.randomUUID());
        //post.setCreated_At(makeDate());
        post.setAuthor(usuario);

        repository.save(post);
        return new ResponseEntity(post, HttpStatus.OK);
    }

    @GetMapping("protected/posts")
    public ResponseEntity<?> getPosts(){
        Iterator<Post> iterator = repository.findAll().iterator();
        List<Post> posts = new ArrayList<>();

        while (iterator.hasNext()){
            posts.add(iterator.next());
        }

        return new ResponseEntity<List>(posts, HttpStatus.OK);
    }

    @GetMapping("protected/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable UUID id){
        Optional<Post> post = repository.findById(id);
        verifyIfPostExists(post, id);
        return new ResponseEntity<Optional>(repository.findById(id), HttpStatus.OK);
    }

    @GetMapping("posts")
    public @ResponseBody ResponseEntity<?>  getPostByUser(@RequestParam("user") String user, @RequestParam("rs") String redeSocial, @RequestParam("qtd") int quantidade, @RequestParam("page") int page) {
        // int indice = (page -1) * quantidade; //Claculo do indice
        Page<Post> posts = repository.getByUserAndMedia(user, redeSocial , PageRequest.of(page,quantidade));

        if(posts.getTotalElements() <= 0) throw new ResourceNotFoundException("Users not found!");

        return new ResponseEntity<List>(posts.getContent(), HttpStatus.OK);
    }

    @PutMapping("protected/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable UUID id, @RequestBody Post post){
        Optional<Post> postRepository = repository.findById(id);
        verifyIfPostExists(postRepository, id);
        repository.save(upPost(post, postRepository.get()));
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("protected/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable UUID id){
        Optional<Post> post = repository.findById(id);
        verifyIfPostExists(post, id);
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfPostExists(Optional post, UUID id){
        if(!post.isPresent()){
            throw new ResourceNotFoundException("User not found for ID: "+id);
        }
    }

    private String makeDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        return date;
    }

    private Post upPost(Post antigo, Post novo){

        novo.setContent(antigo.getContent());
        novo.setRt(antigo.getRt());
        novo.setService(antigo.getService());
        novo.setUrls(antigo.getUrls());
        novo.setApplication(antigo.getApplication());
        novo.setIn_reply_to(antigo.getIn_reply_to());
        novo.setLanguage(antigo.getLanguage());
        novo.setUpload_At(makeDate());
        novo.setPicture_full_size(antigo.getPicture_full_size());
        novo.setTag(antigo.getTag());

        return novo;
    }
}
