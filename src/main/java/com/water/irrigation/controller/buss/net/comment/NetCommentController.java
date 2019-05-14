package com.water.irrigation.controller.buss.net.comment;

import com.water.irrigation.entity.base.ResultEntity;
import com.water.irrigation.entity.dto.net.comment.NetCommentDto;
import com.water.irrigation.entity.net.comment.NetComment;
import com.water.irrigation.service.net.comment.NetCommentService;
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
 *  订单信息控制器
 * @author lichunlei
 *
 */
@RequestMapping("comment/info")
@RestController
@Slf4j
public class NetCommentController {

    @Autowired
    private NetCommentService netCommentService;
    @Autowired
    private DozerHelperUtils dozerHelperUtils;

//    @Autowired
//    private DozerHelperUtils dozerHelperUtils;

    /**
     * 获取 订单数据列表
     * @param netCommentDto  订单数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("readall")
    public ResultEntity readAll(NetCommentDto netCommentDto){

        ResultEntity resultEntity = new ResultEntity();
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"dregt");
            Integer pageno = netCommentDto.getPageno();
            Integer pagesize = netCommentDto.getPagesize();
            pageno = pageno >= 1 ? pageno-1 : 0;

            Pageable pageable = PageRequest.of(pageno,pagesize,sort);
            Page<NetComment> classifies =
                    this.netCommentService.findAll(netCommentDto,pageable);
            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setPageno(pageno+1);
            resultEntity.setPagesize(pagesize);
            resultEntity.setCount(classifies.getTotalElements());
            resultEntity.setContent(classifies.getContent());
        }catch(Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("获取 订单数据出错:"+e.getMessage());
            log.error("获取订单数据出错", e);
        }
        return resultEntity;
    }

    /**
     * 添加 订单信息
     * @param netCommentDto 订单表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("add")
    public ResultEntity add(@RequestBody NetCommentDto netCommentDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetComment netComment =
                    dozerHelperUtils.convert(netCommentDto, NetComment.class);
            netComment.setItype(0);
            NetComment result = this.netCommentService.add(netComment,netCommentDto.getUsername());

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("添加订单信息出错:"+e.getMessage());
            log.error("添加 订单信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 修改 订单表信息
     * @param netCommentDto 订单表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("update")
    public ResultEntity update(@RequestBody NetCommentDto netCommentDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetComment netComment =
                    dozerHelperUtils.convert(netCommentDto, NetComment.class);
            NetComment result = this.netCommentService.
                    update(netComment);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("修改 订单信息出错:"+e.getMessage());
            log.error("修改 订单信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 删除 订单表信息
     * @param netCommentDto 订单表数据传输对象
     * @return 返回删除是否成功得对象
     */
    @RequestMapping("delete")
    public ResultEntity delete(@RequestBody NetCommentDto netCommentDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {
            List<NetComment> netComment =
                    netCommentDto.getDelLists();

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            this.netCommentService.delete(netComment);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("删除 订单信息出错:"+e.getMessage());
            log.error("删除 订单信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 接受 订单信息
     * @param netCommentDto 订单表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("order")
    public ResultEntity order(@RequestBody NetCommentDto netCommentDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetComment netComment =
                    dozerHelperUtils.convert(netCommentDto, NetComment.class);
            NetComment result = this.netCommentService.order(netComment);

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("接受订单信息出错:"+e.getMessage());
            log.error("接收 订单信息出错", e);
        }
        return resultEntity;
    }

    /**
     * 完成 订单信息
     * @param netCommentDto 订单表数据传输对象
     * @return 请求返回实体信息
     */
    @RequestMapping("commit")
    public ResultEntity commit(@RequestBody NetCommentDto netCommentDto) {
        ResultEntity resultEntity = new ResultEntity();
        try {

            NetComment netComment =
                    dozerHelperUtils.convert(netCommentDto, NetComment.class);
            NetComment result = this.netCommentService.commit(netComment,netCommentDto.getUsername());

            resultEntity.setCode(ResultEntity.StatusCode.SUCCESS.getCode());
            resultEntity.setContent(result);
        }catch (Exception e) {
            resultEntity.setCode(ResultEntity.StatusCode.FAILURE.getCode());
            resultEntity.setMsg("完成订单信息出错:"+e.getMessage());
            log.error("完成 订单信息出错", e);
        }
        return resultEntity;
    }
}
