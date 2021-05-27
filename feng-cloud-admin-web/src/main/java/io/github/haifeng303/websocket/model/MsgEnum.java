package io.github.haifeng303.websocket.model;

/**
 * @PACKAGE_NAME: io.github.haifeng303.websocket.model
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/15
 **/
public enum MsgEnum {
    /**
     * 关闭
     */
    CLOSE(0),
    /**
     * 开始连接
     */
    START(1),
    /**
     * 提示
     */
    TIPS(2),
    /**
     * 健康检查
     */
    HEALTH(3);

    public Integer type;

    MsgEnum(Integer type) {
        this.type = type;
    }
}
