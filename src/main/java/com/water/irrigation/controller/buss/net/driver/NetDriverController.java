package com.water.irrigation.controller.buss.net.driver;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.net.driver.NetDriverDto;
import com.water.irrigation.entity.net.driver.NetDriver;
import com.water.irrigation.service.net.driver.NetDriverService;
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
 *  司机信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("driver/info")
@RestController
@Slf4j
public class NetDriverController {

    @Autowired
    private NetDriverService netDriverService;
    @Autowired
    private DozerHelperUtils dozerHelperUtils;

//    @Autowired
//    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 司机数据列表
     * @param netDriverDto  司机数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(NetDriverDto netDriverDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = netDriverDto.getPageno();
            Integer pagesize = netDriverDto.getPagesize();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<NetDriver> classifies =
                    this.netDriverService.findAll(netDriverDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setPageno(pageno+1);
            resultEntity.setPagesize(pagesize);
            resultEntity.setCount(classifies.getTotalElements());
            resultEntity.setContent(classifies.getContent());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 司机数据出错:"+e.getMessage());
            log.error("获取司机数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 司机信息
     * @param netDriverDto 司机表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(@RequestBody NetDriverDto netDriverDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetDriver netDriver =
                    dozerHelperUtils.convert(netDriverDto, NetDriver.class);
            netDriver.setItype(0);
            NetDriver result = this.netDriverService.add(netDriver);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加司机信息出错:"+e.getMessage());
            log.error("添加 司机信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 司机表信息
     * @param netDriverDto 司机表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(@RequestBody NetDriverDto netDriverDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetDriver netDriver =
                    dozerHelperUtils.convert(netDriverDto, NetDriver.class);
            NetDriver result = this.netDriverService.
                    update(netDriver);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 司机信息出错:"+e.getMessage());
            log.error("修改 司机信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 司机表信息
     * @param netDriverDto 司机表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(@RequestBody NetDriverDto netDriverDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<NetDriver> ghjfComtypr =
                    netDriverDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.netDriverService.delete(ghjfComtypr);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 司机信息出错:"+e.getMessage());
            log.error("删除 司机信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 审核 司机信息
     * @param netDriverDto 司机表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("check")
    public ResultEntity check(@RequestBody NetDriverDto netDriverDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetDriver netDriver =
                    dozerHelperUtils.convert(netDriverDto, NetDriver.class);

            NetDriver result = this.netDriverService.check(netDriver);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("审核司机信息出错:"+e.getMessage());
            log.error("审核 司机信息出错", e);
        }
        return resultEntity;
    }
}
