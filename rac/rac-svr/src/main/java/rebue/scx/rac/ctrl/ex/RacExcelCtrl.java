package rebue.scx.rac.ctrl.ex;

import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.scx.rac.api.ex.RacExcelApi;
import rebue.scx.rac.co.RacCookieCo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.turing.JwtUtils;

/**
 * excel相关控制器
 * 
 * @author yuanman
 *
 */
@RestController
@Slf4j
public class RacExcelCtrl {

    @Resource
    private RacExcelApi api;

    /**
     * excel 模板文件下载
     * 
     * @param orpType  模板类型
     * @param response
     */
    @GetMapping("/rac/excel/template-download/{type}")
    public Mono<Void> getExcelTemplate(@PathVariable("type") final String type, final ServerHttpResponse response) {
        return api.getExcelTemplate(type, response);
    }

    /**
     * 上传excel
     * 
     * @ignoreParams request
     * 
     * @throws IOException
     */
    @PostMapping(value = "/rac/excel/{type}/template-upload")
    public Mono<?> getExcelContent(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken, @PathVariable("type") final String type,
            @RequestPart("excel") final Flux<FilePart> filePartFlux, final ServerHttpRequest request,
            final ServerHttpResponse response) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        final Long curAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (curAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到账户ID");
        }
        // 从Headers中获取应用ID
        final List<String> list  = request.getHeaders().get(RacCookieCo.HEADERS_APP_ID_KEY);
        final String       appId = list != null && list.size() > 0 ? list.get(0) : null;
        if (StringUtils.isBlank(appId)) {
            throw new RuntimeExceptionX("在Headers中找不到应用ID");
        }
        return filePartFlux.flatMap(filePart -> {
            final String             fileName           = filePart.filename();
            final ContentDisposition contentDisposition = filePart.headers().getContentDisposition();
            final MediaType          contentType        = filePart.headers().getContentType();
            log.info(fileName);
            log.info(contentDisposition.toString());
            log.info(contentType.toString());
            return filePart.content().map(dataBuffer -> dataBuffer.asInputStream(true))
                    .reduce(SequenceInputStream::new)
                    .map(inputStream -> {
                        return api.getExcelContent(inputStream, fileName);
                    });
        }).next();
    }

}
