package io.github.haifeng303.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import io.github.haifeng303.pojo.dto.SystemCoreDto;
import com.fengzai.common.util.Builder;

/**
 * @PACKAGE_NAME: io.github.haifeng303.util
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/15
 **/
public class SystemCoreUtil {
    /**
     * @description 获取系统核心参数
     * @date 2021/5/15
     * @return io.github.haifeng303.pojo.dto.SystemCoreDto
     */
    public static SystemCoreDto obtainSystemCoreParams(){
        RuntimeInfo runtime = SystemUtil.getRuntimeInfo();
        long useMemory = runtime.getTotalMemory() - runtime.getFreeMemory();
        String useRate = NumberUtil.decimalFormat("#.##%", NumberUtil.div((float)useMemory, (float)runtime.getTotalMemory(), 2));
        SystemCoreDto systemCoreDto = Builder.of(SystemCoreDto::new)
                .with(SystemCoreDto::setUseRate, useRate)
                .with(SystemCoreDto::setUseMemory, FileUtil.readableFileSize(useMemory))
                .with(SystemCoreDto::setFreeMemory, FileUtil.readableFileSize(runtime.getFreeMemory()))
                .with(SystemCoreDto::setTotalMemory, FileUtil.readableFileSize(runtime.getTotalMemory()))
                .with(SystemCoreDto::setThreadCount, SystemUtil.getTotalThreadCount())
                .with(SystemCoreDto::setIp, NetUtil.getLocalhostStr())
                .with(SystemCoreDto::setMacAddress, NetUtil.getLocalMacAddress())
                .build();
        return systemCoreDto;
    }
}
