package com.water.irrigation.controller.buss.river.staticriver;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.river.staticriver.RiversStaticDto;
import com.water.irrigation.entity.river.staticriver.RiversStatic;
import com.water.irrigation.service.river.staticriver.RiversStaticService;
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
 * 河流静态信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("static/info")
@RestController
@Slf4j
public class RiversStaticController {
    @Autowired
    private RiversStaticService riversStaticService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 河流静态数据列表
     * @param riversStaticDto  河流静态数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(RiversStaticDto riversStaticDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = riversStaticDto.getPage();
            Integer pagesize = riversStaticDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<RiversStatic> classifies =
                    this.riversStaticService.findAll(riversStaticDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(classifies.getContent());
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 河流静态数据出错:"+e.getMessage());
            log.error("获取河流静态数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 河流静态信息
     * @param riversStaticDto 河流静态表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(RiversStaticDto riversStaticDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            RiversStatic riversStatic =
                    dozerHelperUtils.convert(riversStaticDto, RiversStatic.class);

            RiversStatic result = this.riversStaticService.add(riversStatic);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加河流静态信息出错:"+e.getMessage());
            log.error("添加 河流静态信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 河流静态表信息
     * @param riversStaticDto 河流静态表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(RiversStaticDto riversStaticDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            RiversStatic riversStatic =
                    dozerHelperUtils.convert(riversStaticDto, RiversStatic.class);
            RiversStatic result = this.riversStaticService.
                    update(riversStatic);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 河流静态信息出错:"+e.getMessage());
            log.error("修改 河流静态信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 河流静态表信息
     * @param riversStaticDto 河流静态表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(RiversStaticDto riversStaticDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<RiversStatic> ghjfComtypr =
                    riversStaticDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.riversStaticService.delete(ghjfComtypr);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 河流静态信息出错:"+e.getMessage());
            log.error("删除 河流静态信息出错", e);
        }
        return resultEntity;
    }
}
