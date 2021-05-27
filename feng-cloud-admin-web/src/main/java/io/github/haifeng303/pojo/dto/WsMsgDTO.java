package io.github.haifeng303.pojo.dto;

import io.github.haifeng303.websocket.model.MsgEnum;
import lombok.Data;

/**
 * @PACKAGE_NAME: io.github.haifeng303.pojo.dto
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/15
 **/
@Data
public class WsMsgDTO {

    /**
     * 数据类型
     */
    private Integer type;

    /**
     * 数据内容
     */
    private Object msg;

    /**
     * 提示消息
     * @param msg
     * @return
     */
    public static WsMsgDTO buildMsg(MsgEnum type, Object msg) {
        WsMsgDTO resp = new WsMsgDTO();
        resp.setType(type.type);
        resp.setMsg(msg);
        return resp;
    }

}
