package com.water.irrigation.entity.dto.asset;

import com.water.irrigation.entity.asset.AssetInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AssetInfoDto extends AssetInfo {

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
    private List<AssetInfo> delLists;
}
