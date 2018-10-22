package br.com.buzzmonitor.endpoints;

import br.com.buzzmonitor.erros.ResourceNotFoundException;
import br.com.buzzmonitor.models.User;
import br.com.buzzmonitor.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("v1")
public class UserController {

    // Injeção de Dependecia e instanciando o atributo
    @Autowired
    private IUserRepository repository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("protected/users/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setId(UUID.randomUUID());
        user.setPassword(cryptSenha(user.getPassword()));
        user.setCreated_at(makeDate());
        repository.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @GetMapping("admin/users")
    public ResponseEntity<?> getUsers(){
        Iterator<User> iterator = repository.findAll().iterator();
        List<User> users = new ArrayList<>();

        while (iterator.hasNext()){
            users.add(iterator.next());
        }

        return new ResponseEntity<List>(users, HttpStatus.OK);
    }

    @GetMapping("admin/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id){
        Optional<User> usuario = repository.findById(id);
        verifyIfUserExists(usuario, id);
        return new ResponseEntity<Optional>(repository.findById(id), HttpStatus.OK);
    }

    @PutMapping("protected/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User user){
        Optional<User> usuario = repository.findById(id);
        verifyIfUserExists(usuario, id);

        repository.save(upUser(user, usuario.get()));
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("admin/users/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id){
        Optional<User> usuario = repository.findById(id);
        verifyIfUserExists(usuario, id);
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfUserExists(Optional user, UUID id){
        if(!user.isPresent()){
            throw new ResourceNotFoundException("User not fount for ID: "+id);
        }
    }

    private String makeDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        return date;
    }

    private String cryptSenha(String password){
        String crypSenha = passwordEncoder.encode(password);
        return crypSenha;
    }

    private User upUser(User antigo, User novo){
        novo.setNome(antigo.getNome());
        novo.setUsername(antigo.getUsername());
        novo.setAdmin(antigo.isAdmin());
        novo.setDescricao(antigo.getDescricao());
        novo.setBios(antigo.getBios());
        novo.setFollowers(antigo.getFollowers());
        novo.setFriends(antigo.getFriends());
        novo.setLocation(antigo.getLocation());
        novo.setGender(antigo.getGender());
        novo.setUpdate_at(makeDate());
        novo.setProfile_image_url(antigo.getProfile_image_url());
        novo.setTweets(antigo.getTweets());

        return novo;
    }
}
