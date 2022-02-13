package rebue.scx.etl.ctrl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.etl.api.EtlConnApi;
import rebue.scx.etl.mo.EtlConnMo;
import rebue.scx.etl.to.EtlConnAddTo;
import rebue.scx.etl.to.EtlConnModifyTo;
import rebue.scx.etl.to.EtlConnOneTo;
import rebue.scx.etl.to.EtlConnPageTo;

/**
 * 数据库连接器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class EtlConnCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private EtlConnApi api;

    /**
     * 添加数据库连接器
     *
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/etl/conn")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final EtlConnAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改数据库连接器的信息
     *
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/etl/conn")
    public Mono<Ro<?>> modify(@RequestBody final EtlConnModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除数据库连接器
     *
     * @param id 数据库连接器ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/etl/conn")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 通过ID获取单个数据库连接器的信息
     *
     * @param id 数据库连接器ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/conn/get-by-id")
    public Mono<Ro<PojoRa<EtlConnMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断数据库连接器是否存在
     *
     * @param id 数据库连接器ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/conn/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询数据库连接器的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/conn/page")
    public Mono<Ro<PageRa<EtlConnMo>>> page(final EtlConnPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 根据连接器ID查询表名
     *
     * @param id 数据库连接器ID
     */
    @GetMapping("/etl/conn/get-table-name-by-id")
    public Mono<Ro<?>> getTablesName(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getTablesName(id)));
    }

    /**
     * 根据连接器ID和表名查询列名
     *
     * @param id        数据库连接器ID
     * @param tableName 表名
     */
    @GetMapping("/etl/conn/get-columus-name-by-id")
    public Mono<Ro<?>> getColumnsByTableName(@RequestParam("id") final java.lang.Long id, @RequestParam("tableName") final String tableName) {
        return Mono.create(callback -> callback.success(api.getColumnsByTableName(id, tableName)));
    }

    /**
     * 测试连接
     *
     * @param id 数据库连接器ID
     *
     * @return Boolean
     */
    @GetMapping("/etl/conn/test-connection-by-id")
    public Mono<Ro<BooleanRa>> testConnectionById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.testConnectionById(id)));
    }

    /**
     * 通过条件获取单个数据库连接器的信息
     *
     * @param id 数据库连接器ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/etl/conn/get-one")
    public Mono<Ro<PojoRa<EtlConnMo>>> getOne(final EtlConnOneTo qo) {
        return Mono.create(callback -> callback.success(api.getOne(qo)));
    }
}
