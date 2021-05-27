package io.github.haifeng303.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PACKAGE_NAME: io.github.haifeng303.pojo.dto
 * @author: rhf
 * @ProjectName: feng-cloud
 * @description:
 * @DATE: 2021/5/15
 **/
@Data
//@NoArgsConstructor(staticName="of")
public class SystemCoreDto {
    private String ip;
    private String macAddress;
    private String useRate;
    private String useMemory;
    private String freeMemory;
    private String totalMemory;
    private int threadCount;
}
