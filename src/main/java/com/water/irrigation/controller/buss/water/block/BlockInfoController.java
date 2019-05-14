package com.water.irrigation.controller.buss.water.block;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.water.block.BlockInfoDto;
import com.water.irrigation.entity.water.block.BlockInfo;
import com.water.irrigation.service.water.block.BlockInfoService;
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

import java.util.List;

/**
 * 地块信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("block/info")
@RestController
@Slf4j
public class BlockInfoController {

    @Autowired
    private BlockInfoService blockInfoService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 地块数据列表
     * @param blockInfoDto  地块数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(BlockInfoDto blockInfoDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = blockInfoDto.getPage();
            Integer pagesize = blockInfoDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<BlockInfo> classifies =
                    this.blockInfoService.findAll(blockInfoDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(classifies.getContent());
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 地块数据出错:"+e.getMessage());
            log.error("获取地块数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 地块信息
     * @param blockInfoDto 地块表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(BlockInfoDto blockInfoDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            BlockInfo blockInfo =
                dozerHelperUtils.convert(blockInfoDto, BlockInfo.class);

        BlockInfo result = this.blockInfoService.add(blockInfo);

        resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
        resultEntity.setContent(result);
    }catch (Exception e) {
        resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
        resultEntity.setMsg("添加地块信息出错:"+e.getMessage());
        log.error("添加 地块信息出错", e);
    }
        return resultEntity;
    }

    /**
     * 修改 地块表信息
     * @param blockInfoDto 地块表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(BlockInfoDto blockInfoDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            BlockInfo blockInfo =
                    dozerHelperUtils.convert(blockInfoDto, BlockInfo.class);
            BlockInfo result = this.blockInfoService.
                    update(blockInfo);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 地块信息出错:"+e.getMessage());
            log.error("修改 地块信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 地块表信息
     * @param blockInfoDto 地块表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(BlockInfoDto blockInfoDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<BlockInfo> ghjfComtypr =
                    blockInfoDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.blockInfoService.delete(ghjfComtypr);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 地块信息出错:"+e.getMessage());
            log.error("删除 地块信息出错", e);
        }
        return resultEntity;
    }
}
