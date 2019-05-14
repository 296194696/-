package com.water.irrigation.controller.buss.net.passenger;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.net.passenger.NetPassengerDto;
import com.water.irrigation.entity.net.passenger.NetPassenger;
import com.water.irrigation.service.net.passenger.NetPassengerService;
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
 *  乘客信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("passenger/info")
@RestController
@Slf4j
public class NetPassengerController {

    @Autowired
    private NetPassengerService netPassengerService;
    @Autowired
    private DozerHelperUtils dozerHelperUtils;

//    @Autowired
//    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 乘客数据列表
     * @param netPassengerDto  乘客数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(NetPassengerDto netPassengerDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = netPassengerDto.getPageno();
            Integer pagesize = netPassengerDto.getPagesize();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<NetPassenger> classifies =
                    this.netPassengerService.findAll(netPassengerDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setPageno(pageno+1);
            resultEntity.setPagesize(pagesize);
            resultEntity.setCount(classifies.getTotalElements());
            resultEntity.setContent(classifies.getContent());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 乘客数据出错:"+e.getMessage());
            log.error("获取乘客数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 乘客信息
     * @param netPassengerDto 乘客表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(@RequestBody NetPassengerDto netPassengerDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetPassenger netPassenger =
                    dozerHelperUtils.convert(netPassengerDto, NetPassenger.class);

            NetPassenger result = this.netPassengerService.add(netPassenger);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加乘客信息出错:"+e.getMessage());
            log.error("添加 乘客信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 乘客表信息
     * @param netPassengerDto 乘客表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(@RequestBody NetPassengerDto netPassengerDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetPassenger netPassenger =
                    dozerHelperUtils.convert(netPassengerDto, NetPassenger.class);
            NetPassenger result = this.netPassengerService.
                    update(netPassenger);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 乘客信息出错:"+e.getMessage());
            log.error("修改 乘客信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 乘客表信息
     * @param netPassengerDto 乘客表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(@RequestBody NetPassengerDto netPassengerDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<NetPassenger> netPassengers =
                    netPassengerDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.netPassengerService.delete(netPassengers);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 乘客信息出错:"+e.getMessage());
            log.error("删除 乘客信息出错", e);
        }
        return resultEntity;
    }
}
