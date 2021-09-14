package rebue.scx.oss.ctrl;

import java.io.SequenceInputStream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.oss.api.OssObjApi;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjPageTo;
import rebue.wheel.turing.JwtUtils;

/**
 * 对象控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OssObjCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OssObjApi api;

    /**
     * 添加对象
     *
     * @param to 添加的具体信息
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/oss/obj")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OssObjAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改对象的信息
     *
     * @param to 修改的具体数据
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/oss/obj")
    public Mono<Ro<?>> modify(@RequestBody final OssObjModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除对象
     *
     * @param id 对象ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/oss/obj")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个对象的信息
     *
     * @param id 对象ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oss/obj/get-by-id")
    public Mono<Ro<PojoRa<OssObjMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断对象是否存在
     *
     * @param id 对象ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oss/obj/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询对象的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oss/obj/page")
    public Mono<Ro<PageRa<OssObjMo>>> page(final OssObjPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 上传文件
     * 
     * @param jwtToken
     * @param filePartFlux  向服务器发送的文件属性name="avatar"
     * @param path          文件存储桶名称只能由小写字母、数字、句点 (.) 和连字符 (-) 组成。存储桶名称必须以字母或数字开头和结尾。
     * @param orignFileName 文件名称
     * @param response
     * 
     * @return
     */
    @PostMapping(value = "/oss/obj/upload")
    public Mono<?> upload(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken, @RequestPart("avatar") final Flux<FilePart> filePartFlux,
            @RequestPart(value = "path", required = false) final String path, @RequestPart(value = "orignFileName", required = false) final String orignFileName,
            final ServerHttpResponse response) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        final Long curAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (curAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到账户ID");
        }
        return filePartFlux.flatMap(filePart -> {
            // 判断是否使用控件默认文件名
            final String             fileName           = orignFileName != null && !orignFileName.equals("") ? orignFileName : filePart.filename();
            final ContentDisposition contentDisposition = filePart.headers().getContentDisposition();
            final MediaType          contentType        = filePart.headers().getContentType();
            return filePart.content().map(dataBuffer -> dataBuffer.asInputStream(true)).reduce(SequenceInputStream::new).map(inputStream -> {
                final Ro<?> ro = api.upload(path, curAccountId, fileName, contentDisposition.toString(), contentType.toString(), inputStream);
                if (!ResultDic.SUCCESS.equals(ro.getResult())) {
                    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return ro;
            });
        }).next();
    }
}
