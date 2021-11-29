package rebue.scx.rac.svc.ex;

import java.io.InputStream;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.annotation.Validated;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.Ro;

/**
 * Excel的服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 */
@Validated
public interface RacExcelSvc {

    Mono<Void> getExcelTemplate(String type, ServerHttpResponse response);

    Mono<?> getExcelContent(Flux<FilePart> filePartFlux);

    Ro<?> getExcelContent(InputStream inputStream, String fileName);

}
