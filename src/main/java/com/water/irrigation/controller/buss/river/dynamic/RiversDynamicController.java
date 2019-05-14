package com.water.irrigation.controller.buss.river.dynamic;


import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.river.dynamic.RiversDynamicDto;
import com.water.irrigation.entity.river.dynamic.RiversDynamic;
import com.water.irrigation.service.river.dynamic.RiversDynamicService;
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
 * 河流动态信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("dynamic/info")
@RestController
@Slf4j
public class RiversDynamicController {

    @Autowired
    private RiversDynamicService riversDynamicService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 河流动态数据列表
     * @param riversDynamicDto  河流动态数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(RiversDynamicDto riversDynamicDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = riversDynamicDto.getPage();
            Integer pagesize = riversDynamicDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<RiversDynamic> classifies =
                    this.riversDynamicService.findAll(riversDynamicDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(classifies.getContent());
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 河流动态数据出错:"+e.getMessage());
            log.error("获取河流动态数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 河流动态信息
     * @param riversDynamicDto 河流动态表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(RiversDynamicDto riversDynamicDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            RiversDynamic riversDynamic =
                    dozerHelperUtils.convert(riversDynamicDto, RiversDynamic.class);

            RiversDynamic result = this.riversDynamicService.add(riversDynamic);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加河流动态信息出错:"+e.getMessage());
            log.error("添加 河流动态信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 河流动态表信息
     * @param riversDynamicDto 河流动态表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(RiversDynamicDto riversDynamicDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            RiversDynamic riversDynamic =
                    dozerHelperUtils.convert(riversDynamicDto, RiversDynamic.class);
            RiversDynamic result = this.riversDynamicService.
                    update(riversDynamic);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 河流动态信息出错:"+e.getMessage());
            log.error("修改 河流动态信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 河流动态表信息
     * @param riversDynamicDto 河流动态表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(RiversDynamicDto riversDynamicDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<RiversDynamic> ghjfComtypr =
                    riversDynamicDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.riversDynamicService.delete(ghjfComtypr);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 河流动态信息出错:"+e.getMessage());
            log.error("删除 河流动态信息出错", e);
        }
        return resultEntity;
    }
}
