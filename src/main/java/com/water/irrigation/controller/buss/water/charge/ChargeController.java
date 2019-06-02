package com.water.irrigation.controller.buss.water.charge;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.water.charge.ChargeDto;
import com.water.irrigation.entity.dto.water.charge.ChargeVoDto;
import com.water.irrigation.entity.vo.charge.ChargeVo;
import com.water.irrigation.entity.water.charge.Charge;
import com.water.irrigation.service.water.charge.ChargeService;
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
import java.util.Map;

/**
 * 水费缴费控制器
 * @author lichunlei
 *
 */
@RequestMapping("charge/info")
@RestController
@Slf4j
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 作物数据列表
     * @param ChargeDto  作物数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(ChargeDto ChargeDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = ChargeDto.getPage();
            Integer pagesize = ChargeDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<Charge> classifies =
                    this.chargeService.findAll(ChargeDto,pageable);
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
     * 添加 水费缴费
     * @param chargeDto 作物表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(ChargeDto chargeDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Charge charge =
                    dozerHelperUtils.convert(chargeDto, Charge.class);

            Charge result = this.chargeService.add(charge);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加水费缴费出错:"+e.getMessage());
            log.error("添加 水费缴费出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 作物表信息
     * @param chargeDto 作物表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(ChargeDto chargeDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Charge charge =
                    dozerHelperUtils.convert(chargeDto, Charge.class);
            Charge result = this.chargeService.
                    update(charge);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 水费缴费出错:"+e.getMessage());
            log.error("修改 水费缴费出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 作物表信息
     * @param chargeDto 作物表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(ChargeDto chargeDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<Charge> charges =
                    chargeDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.chargeService.delete(charges);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 水费缴费出错:"+e.getMessage());
            log.error("删除 水费缴费出错", e);
        }
        return resultEntity;
    }

    @RequestMapping("readsum")
    public ResultEntity readSum(ChargeVoDto chargeVoDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            //Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = chargeVoDto.getPage();
            Integer pagesize = chargeVoDto.getLimit();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize);
            Page<ChargeVo> classifies =
                    this.chargeService.findUserByDyCondition(chargeVoDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            List<ChargeVo> listChargeVo=classifies.getContent();
            for(ChargeVo chargeVos:listChargeVo){
                if(chargeVos.getItype()==null){
                    chargeVos.setItype(1);
                    chargeVos.setNeed(Double.valueOf(0));
                }
            }
            resultEntity.setContent(listChargeVo);
            resultEntity.setCount(classifies.getTotalElements());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 总费用数据出错:"+e.getMessage());
            log.error("获取总费用数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 缴费按钮
     * @param chargeDto
     * @return
     */
    @RequestMapping("updatesum")
    public ResultEntity updateSum(ChargeDto chargeDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            Charge charge =
                    dozerHelperUtils.convert(chargeDto, Charge.class);
            List<Charge> result = this.chargeService.
                    updateSum(charge);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 水费缴费出错:"+e.getMessage());
            log.error("修改 水费缴费出错", e);
        }
        return resultEntity;
    }

    /**
     * 根据作物名称查询金额总和   渲染到饼状图
     * @return
     */
    @RequestMapping("findSumBySname")
    public ResultEntity findSumBySname(){
        ResultEntity resultEntity = new ResultEntity();
        try {
            Map<String,List> result=chargeService.findSumBySname();
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 水费缴费总和出错:"+e.getMessage());
            log.error("获取 水费缴费总和出错", e);
        }
        return resultEntity;
    }

    /**
     * 根据作物名称查询金额总和   渲染到饼状图
     * @return
     */
    @RequestMapping("findChargeCity")
    public ResultEntity findChargeCity(){
        ResultEntity resultEntity = new ResultEntity();
        try {
            Map result=chargeService.findChargeCity();
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 政区统计金额出错:"+e.getMessage());
            log.error("获取 政区统计金额出错", e);
        }
        return resultEntity;
    }
}
