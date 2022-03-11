package service;

import client.ServiceDiscoveryRequestManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MovieDto;
import dto.PersonDto;
import lombok.SneakyThrows;
import org.glassfish.jersey.client.ClientProperties;
import paging.Page;
import paging.Pageable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

import static java.lang.String.format;


@RequestScoped
public class BusinessServiceImpl implements BusinessService {

    @EJB
    private ServiceDiscoveryRequestManager requestManager;

    private static final String PERSON = "/person";
    private static final String MOVIE = "/movie";
    private static final String MOVIE_ID = MOVIE + "/%d";
    private static final String MOVIE_BY_GENRE = MOVIE + "/by-genre/%s";
    private static final String MOVIE_BY_DIRECTOR = MOVIE + "/by-director/%d";

    @Override
    public Page<PersonDto> findAllDirectors(Pageable pageable) {
        return getPagedPerson(target(PERSON));
    }

    @Override
    public Page<MovieDto> findMoviesByGenre(String genre, Pageable pageable) {
        return getPagedMovie(target(expandUrlWithParam(MOVIE_BY_GENRE, genre)));
    }

    @Override
    public Page<MovieDto> findMoviesByDirectorId(Integer id, Pageable pageable) {
        return getPagedMovie(target(expandUrlWithParam(MOVIE_BY_DIRECTOR, id)));
    }

    @Override
    public MovieDto updateMovie(Integer id, MovieDto updateDto) {
        return put(target(expandUrlWithParam(MOVIE_ID, id)), updateDto);
    }

    public Client client() {

        return ClientBuilder.newBuilder()
//                            .sslContext(sslConfigurator.createSSLContext())
                            .hostnameVerifier(((hostname, session) -> hostname.equals("localhost") ||
                                    hostname.equals("127.0.0.1")))
                            .property(ClientProperties.FOLLOW_REDIRECTS, Boolean.TRUE)
                            .build();
    }

    @SneakyThrows
    private Page<PersonDto> getPagedPerson(WebTarget target) {
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                     .get(new GenericType<Page<PersonDto>>() {
                     });

    }

    private Page<MovieDto> getPagedMovie(WebTarget target) {
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                     .get(new GenericType<Page<MovieDto>>() {
                     });

    }

    private MovieDto put(WebTarget target, MovieDto entity) {
        return target.request(MediaType.APPLICATION_JSON_TYPE)
                     .put(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE), MovieDto.class);
    }

    private WebTarget target(String path) {
        return Objects.requireNonNull(client())
                      .target(requestManager.discoverServiceUrl())
                      .path(path);
    }

    private String expandUrlWithParam(String url, Object param) {
        return format(url, param);
    }
}
