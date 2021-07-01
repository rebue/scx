package rebue.scx.oss.svc.impl;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.Files;

import io.minio.BucketExistsArgs;
import io.minio.GetBucketPolicyArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SetBucketPolicyArgs;
import lombok.SneakyThrows;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.oss.co.OssMinioCo;
import rebue.scx.oss.dao.OssObjDao;
import rebue.scx.oss.jo.OssObjJo;
import rebue.scx.oss.mapper.OssObjMapper;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.svc.OssObjSvc;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjDelTo;
import rebue.scx.oss.to.OssObjListTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjOneTo;
import rebue.scx.oss.to.OssObjPageTo;

/**
 * 对象服务实现
 *
 * <pre>
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class OssObjSvcImpl
    extends BaseSvcImpl<java.lang.Long, OssObjAddTo, OssObjModifyTo, OssObjDelTo, OssObjOneTo, OssObjListTo, OssObjPageTo, OssObjMo, OssObjJo, OssObjMapper, OssObjDao>
    implements OssObjSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private OssObjSvc   thisSvc;

    @Resource
    private MinioClient minioClient;

    @Value("${minio.endpoint:http://127.0.0.1:9000}")
    private String      minioEndpoint;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, OssObjAddTo, OssObjModifyTo, OssObjDelTo, OssObjOneTo, OssObjListTo, OssObjPageTo, OssObjMo, OssObjJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<OssObjMo> getMoClass() {
        return OssObjMo.class;
    }

    /**
     * 上传文件
     *
     * @param curAccountId       当前账户ID
     * @param fileName           文件名称
     * @param contentDisposition 请求头中的 Content-Disposition
     * @param contentType        请求头中的 Content-Type
     * @param inputStream        文件输入流
     */
    @Override
    @SneakyThrows
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<?> upload(final Long curAccountId, final String fileName, final String contentDisposition, final String contentType, final InputStream inputStream) {
        final Long id = _idWorker.getId();
        final String fileExt = Files.getFileExtension(fileName);
        final String objectName = id.toString() + "." + fileExt;
        final boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(OssMinioCo.OBJ_BUCKET).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(OssMinioCo.OBJ_BUCKET).build());
            final String policyJson = String.format(
                "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:ListBucket\",\"s3:GetBucketLocation\"],\"Resource\":[\"arn:aws:s3:::%1$s\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::%1$s/*\"]}]}\n",
                OssMinioCo.OBJ_BUCKET);
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(OssMinioCo.OBJ_BUCKET).config(policyJson).build());
        }
        final String bucketPolicy = minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(OssMinioCo.OBJ_BUCKET).build());
        System.out.println(bucketPolicy);
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Disposition", contentDisposition);
        headers.put("Content-Type", contentType);
        minioClient.putObject(
            PutObjectArgs.builder().bucket(OssMinioCo.OBJ_BUCKET).contentType(contentType).headers(headers).object(objectName).stream(inputStream, -1, 10485760).build());
        OssObjMo mo = new OssObjMo();
        mo.setId(id);
        mo.setName(fileName);
        mo.setObjType(fileExt);
        mo.setObjSize((long) inputStream.available());
        mo.setCreatorId(curAccountId);
        mo.setCreateDatetime(LocalDateTime.now());
        mo.setUrl(String.format("%s/%s/%s?a=%s", minioEndpoint, OssMinioCo.OBJ_BUCKET, objectName, System.currentTimeMillis()));
        mo = thisSvc.addMo(mo);
        return new Ro<>(ResultDic.SUCCESS, "上传对象成功");
    }
}