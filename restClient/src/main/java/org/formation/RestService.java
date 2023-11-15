package org.formation;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestService {

    private WebClient client;

    public RestService(WebClient.Builder builder){
        this.client = builder
                .baseUrl("http://localhost:8080")
                .build();
    }

  public Mono<User> loadUser(Long id){
        return this.client.get()
                .uri("/members/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class);
  }


}
