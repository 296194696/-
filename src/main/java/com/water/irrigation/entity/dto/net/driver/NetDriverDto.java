package com.water.irrigation.entity.dto.net.driver;

import com.water.irrigation.entity.net.driver.NetDriver;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class NetDriverDto extends NetDriver {

    /**
     * 页数
     */
    private Integer pageno = 1;

    /**
     * 每页记录数
     */
    private Integer pagesize = 10;

    /**
     * 需要删除的数据列表
     */
    private List<NetDriver> delLists;


}
