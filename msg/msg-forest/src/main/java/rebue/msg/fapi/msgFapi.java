package rebue.msg.fapi;

import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;

public interface msgFapi {

    /**
     * 普通邮箱发送
     * 
     * @param json
     * 
     * @return
     */
    @Post(url = "${1}", headers = { "Accept-Charset: UTF-8",
            "Content-Type: application/json",
            "Authorization: ${appKey}",
    })
    String sendEmailOrdinary(@JSONBody() Object json, Object url, @Var("appKey") String appKey);

    /**
     * 模板邮箱发送
     * 
     * @param json
     * 
     * @return
     */
    @Post(url = "${1}", headers = { "Accept-Charset: UTF-8",
            "Content-Type: application/json",
            "Authorization:${appKey}",
    })
    String sendEmailTemplet(@JSONBody() Object json, String url, @Var("appKey") String appKey);
}
