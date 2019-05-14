package com.water.irrigation.controller.buss.water.rate;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.water.rate.RateDto;
import com.water.irrigation.entity.water.rate.Rate;
import com.water.irrigation.service.water.ratr.RateService;
import com.water.irrigation.utils.dozer.DozerHelperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 水率信息控制器
 * @author lichunlei
 *
 */
//@RequestMapping("rate/info")
@Controller
@Slf4j
public class RateController {
    @Autowired
    private RateService rateService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    @RequestMapping("/rate")
    public String rate(ModelMap map){

        Rate rate=rateService.findMaxIndocno();
        map.addAttribute("rate", rate);
        return "my/rate";
    }

    /**
     * 获取 水率数据列表
     * @param RateDto  水率数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("rate/info/readall")
    @ResponseBody
    public ResultEntity readAll(RateDto RateDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = RateDto.getPage();
            Integer pagesize = RateDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<Rate> classifies =
                    this.rateService.findAll(RateDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(classifies.getContent());
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 水率数据出错:"+e.getMessage());
            log.error("获取水率数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 水率信息
     * @param rateDto 水率表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("rate/info/add")
    @ResponseBody
    public ResultEntity add(RateDto rateDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Rate rate =
                    dozerHelperUtils.convert(rateDto, Rate.class);

            Rate result = this.rateService.add(rate);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加水率信息出错:"+e.getMessage());
            log.error("添加 水率信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 水率表信息
     * @param rateDto 水率表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("rate/info/update")
    @ResponseBody
    public ResultEntity update(RateDto rateDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Rate rate =
                    dozerHelperUtils.convert(rateDto, Rate.class);
            Rate result = this.rateService.
                    update(rate);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 水率信息出错:"+e.getMessage());
            log.error("修改 水率信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 水率表信息
     * @param rateDto 水率表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("rate/info/delete")
    @ResponseBody
    public ResultEntity delete(RateDto rateDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<Rate> rates =
                    rateDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.rateService.delete(rates);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 水率信息出错:"+e.getMessage());
            log.error("删除 水率信息出错", e);
        }
        return resultEntity;
    }
}
