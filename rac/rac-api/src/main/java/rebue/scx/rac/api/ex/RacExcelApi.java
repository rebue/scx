package rebue.scx.rac.api.ex;

import java.io.InputStream;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;

/**
 * excel 相关API
 * 
 * @author yuanman
 *
 */
public interface RacExcelApi {

    Mono<Void> getExcelTemplate(String type, ServerHttpResponse response);

    Mono<?> getExcelContent(Flux<FilePart> filePartFlux);

    Ro<?> getExcelContent(InputStream inputStream, String type, String fileName);

}
