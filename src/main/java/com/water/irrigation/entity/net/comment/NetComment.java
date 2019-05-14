package com.water.irrigation.entity.net.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.water.irrigation.entity.base.BaseEntity;
import com.water.irrigation.entity.net.driver.NetDriver;
import com.water.irrigation.entity.net.passenger.NetPassenger;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "net_comment")
@Data
public class NetComment extends BaseEntity {

    /** 订单号 */
    private String sno;
    /** 下单时间 */
    private Date dstarttime;
    /** 完成时间 */
    private Date dendtime;
    /** 金额*/
    private Double money;
    /** 评论*/
    private String scomment;
    /** 星级 */
    private Integer istar;
    /** 状态（0下单，1接单，2完成） */
    private Integer itype;
    /** 起始地点 */
    private String startplace;
    /** 目的地 */
    private String endplace;
    /** 评论子表 */
    @JsonIgnore//防止死循环，导致栈溢出
    @JoinColumn(name="idriverid")//外键列的列名
    @ManyToOne(fetch= FetchType.LAZY)
    private NetDriver netDriver;
    /** 评论子表 */
    @JsonIgnore//防止死循环，导致栈溢出
    @JoinColumn(name="ipassengerid")//外键列的列名
    @ManyToOne(fetch= FetchType.LAZY)
    private NetPassenger netPassenger;
}
