package com.water.irrigation.controller.buss.water.crop;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.water.crop.CropDto;
import com.water.irrigation.entity.water.crops.Crop;
import com.water.irrigation.service.water.crop.CropService;
import com.water.irrigation.utils.dozer.DozerHelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作物信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("crop/info")
@RestController
@Slf4j
public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 作物数据列表
     * @param cropDto  作物数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(CropDto cropDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = cropDto.getPage();
            Integer pagesize = cropDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<Crop> classifies =
                    this.cropService.findAll(cropDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(classifies.getContent());
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 作物数据出错:"+e.getMessage());
            log.error("获取作物数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 作物信息
     * @param cropDto 作物表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(CropDto cropDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Crop crop =
                    dozerHelperUtils.convert(cropDto, Crop.class);

            Crop result = this.cropService.add(crop);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加作物信息出错:"+e.getMessage());
            log.error("添加 作物信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 作物表信息
     * @param cropDto 作物表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(CropDto cropDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Crop crop =
                    dozerHelperUtils.convert(cropDto, Crop.class);
            Crop result = this.cropService.
                    update(crop);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 作物信息出错:"+e.getMessage());
            log.error("修改 作物信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 作物表信息
     * @param cropDto 作物表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(CropDto cropDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<Crop> crops =
                    cropDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.cropService.delete(crops);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 作物信息出错:"+e.getMessage());
            log.error("删除 作物信息出错", e);
        }
        return resultEntity;
    }
}
