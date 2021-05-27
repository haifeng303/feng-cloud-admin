package io.github.haifeng303.websocket;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import io.github.haifeng303.pojo.dto.SystemCoreDto;
import io.github.haifeng303.pojo.dto.WsMsgDTO;
import io.github.haifeng303.util.SystemCoreUtil;
import io.github.haifeng303.websocket.model.MsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @PACKAGE_NAME: io.github.haifeng303.websocket
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/15
 **/
@Slf4j
@Component
@ServerEndpoint("/health/core/{sid}")
public class HealthCoreWebSocket extends BaseWsSocket<Integer> {

    @Scheduled(cron = "*/5 * * * * ?")
    public void healthCore() {// 健康推送-5秒
        // 判断用户是否连接
        if (webSocketSet.size() > 0) {
            SystemCoreDto systemCoreDto = SystemCoreUtil.obtainSystemCoreParams();
            // 发送给所有人
            sendAll(WsMsgDTO.buildMsg(MsgEnum.HEALTH, systemCoreDto));
        }
    }

    /**
     * 连接建立成功调用的方法
     *
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") Integer sid) {
        boolean exist = super.exist(sid);
        if (exist) {// 关闭连接
            this.alreadyLogin(session);
            return;
        }
        super.open(session, sid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param msg 客户端发送过来的消息
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String msg, Session session) {
        super.onMessage(msg, session);
    }

    @OnClose
    public void onClose() {
        super.onClose();
    }

    // 已经登录
    private void alreadyLogin(Session session) {
        try {
            session.getBasicRemote().sendText(JSONUtil.toJsonStr(WsMsgDTO.buildMsg(MsgEnum.CLOSE, "您已经登录")));
            if (session.isOpen()) {
                session.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 给所有人发送消息
     *
     * @param msg
     */
    public void sendAll(WsMsgDTO msg) {
        webSocketSet.forEach((sid, item) -> {
            try {
                item.sendMessage(msg);
            } catch (Exception e) {
                log.error("[消息发送失败: user={},msg={},error={}]", sid, msg, e.getMessage());
            }
        });

    }

    /**
     * 群发自定义消息
     */
    public void sendMsg(WsMsgDTO msg, Collection<Integer> sids) {
        StaticLog.info("[push-msg-user={},msg={}.]", sids, msg);
        webSocketSet.forEach((sid, item) -> {
            try {
                boolean pass = CollUtil.isNotEmpty(sids) && sids.contains(sid);
                // 这里可以设定只推送给这个sid的，为null则全部推送
                if (pass) {
                    item.sendMessage(msg);
                }
            } catch (Exception e) {
                log.error("[消息发送失败: user={},msg={},error={}]", sid, msg, e.getMessage());
            }
        });

    }

    @OnError
    public void onError(Session session, Throwable error) {
        super.onError(session, error);
    }

    public Map<Integer, Long> onlineList() {
        return webSocketSet.keySet().stream().collect(Collectors.groupingBy(k -> k, Collectors.counting()));

    }

    public Set<Integer> onlineKey() {
        return webSocketSet.keySet();
    }

}
