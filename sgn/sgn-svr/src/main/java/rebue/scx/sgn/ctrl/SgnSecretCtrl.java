package rebue.scx.sgn.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.StringRa;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.api.SgnSecretApi;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;

/**
 * 签名密钥控制器
 */
@RestController
public class SgnSecretCtrl {

    @Resource
    private SgnSecretApi api;

    /**
     * 添加签名密钥
     *
     * @param to 添加的具体信息
     */
    @PostMapping("/sgn/secret")
    public Mono<Ro<?>> add(@RequestBody final SgnSecretAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改签名密钥的信息
     *
     * @param to 修改的具体数据
     */
    @PutMapping("/sgn/secret")
    public Mono<Ro<?>> modify(@RequestBody final SgnSecretModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除签名密钥
     *
     * @param id 签名密钥ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/sgn/secret")
    public Mono<Ro<?>> del(@RequestParam("id") final String id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个签名密钥的信息
     *
     * @param id 签名密钥ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/sgn/secret/get-secret-by-id")
    public Mono<Ro<StringRa>> getSecretById(@RequestParam("id") final String id) {
        return Mono.create(callback -> callback.success(api.getSecretById(id)));
    }

}
