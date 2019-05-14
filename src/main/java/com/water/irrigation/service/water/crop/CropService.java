package com.water.irrigation.service.water.crop;

import com.water.irrigation.entity.dto.water.crop.CropDto;
import com.water.irrigation.entity.water.crops.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 作物信息服务层接口
 * @author lichunlei
 *
 */
public interface CropService {
    /**
     * 根据条件获取对应的作物信息数据
     *
     * @param conditions 作物信息过滤条件
     * @param pageable   分页
     * @return 指定的作物信息列表
     */
    Page<Crop> findAll(CropDto conditions, Pageable pageable);

    Crop findOne(Long indocno);

    Crop add(Crop crop);

    Crop update(Crop crop);

    void delete(List<Crop> sysCompanys);
}
