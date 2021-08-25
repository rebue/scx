## JWT

### 生成公私钥

参考 https://connect2id.com/products/nimbus-jose-jwt/openssl-key-generation

注意原文的 openssl genrsa 命令中，参数顺序不对

生成的文件是 PEM 格式

### 生成公私钥命令

```shell
openssl genrsa -out rsa-2048bit-key-pair.pem 2048
```