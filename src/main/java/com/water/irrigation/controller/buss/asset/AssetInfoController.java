package com.water.irrigation.controller.buss.asset;


import java.util.List;

import com.water.irrigation.entity.asset.AssetInfo;
import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.asset.AssetInfoDto;
import com.water.irrigation.service.asset.AssetInfoService;
import com.water.irrigation.utils.dozer.DozerHelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 资产信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("asset/info")
@RestController
@Slf4j
public class AssetInfoController {

    @Autowired
    private AssetInfoService assetInfoService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 资产数据列表
     * @param AssetInfoDto  资产数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(AssetInfoDto AssetInfoDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = AssetInfoDto.getPageno();
            Integer pagesize = AssetInfoDto.getPagesize();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<AssetInfo> classifies =
                    this.assetInfoService.findAll(AssetInfoDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(classifies.getContent());
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 资产数据出错:"+e.getMessage());
            log.error("获取资产数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 资产信息
     * @param assetInfoDto 资产表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(AssetInfoDto assetInfoDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            AssetInfo assetInfo =
                    dozerHelperUtils.convert(assetInfoDto, AssetInfo.class);

            AssetInfo result = this.assetInfoService.add(assetInfo);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加资产信息出错:"+e.getMessage());
            log.error("添加 资产信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 资产表信息
     * @param assetInfoDto 资产表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(AssetInfoDto assetInfoDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            AssetInfo assetInfo =
                    dozerHelperUtils.convert(assetInfoDto, AssetInfo.class);
            AssetInfo result = this.assetInfoService.
                    update(assetInfo);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 资产信息出错:"+e.getMessage());
            log.error("修改 资产信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 资产表信息
     * @param AssetInfoDto 资产表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(AssetInfoDto AssetInfoDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<AssetInfo> ghjfComtypr =
                    AssetInfoDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.assetInfoService.delete(ghjfComtypr);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 资产信息出错:"+e.getMessage());
            log.error("删除 资产信息出错", e);
        }
        return resultEntity;
    }

}
