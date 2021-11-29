package rebue.scx.rac.api.impl.ex;

import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacExcelApi;
import rebue.scx.rac.svc.ex.RacExcelSvc;

/**
 * Excel的实现类
 * 
 * @author yuanman
 *
 */
@DubboService
public class RacExcelApiImpl implements RacExcelApi {

    @Resource
    private RacExcelSvc svc;

    @Override
    public Mono<Void> getExcelTemplate(final String type, final ServerHttpResponse response) {
        return svc.getExcelTemplate(type, response);
    }

    @Override
    public Mono<?> getExcelContent(Flux<FilePart> filePartFlux) {
        return svc.getExcelContent(filePartFlux);
    }

    @Override
    public Ro<?> getExcelContent(InputStream inputStream, String fileName) {
        return svc.getExcelContent(inputStream, fileName);
    }
}
