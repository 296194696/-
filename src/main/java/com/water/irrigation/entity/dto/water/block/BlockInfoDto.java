package com.water.irrigation.entity.dto.water.block;

import com.water.irrigation.entity.water.block.BlockInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlockInfoDto extends BlockInfo {
    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 每页记录数
     */
    private Integer limit = 10;

    /**
     * 需要删除的数据列表
     */
    private List<BlockInfo> delLists;
}
