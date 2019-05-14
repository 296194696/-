package com.water.irrigation.entity.net.passenger;

import com.water.irrigation.entity.base.BaseEntity;
import com.water.irrigation.entity.net.comment.NetComment;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "net_passenger")
@Data
public class NetPassenger extends BaseEntity {


    /** 乘客姓名 */
    private String sname;
    /** 生日 */
    private Date dbrithday;
    /** 性别 */
    private Integer isex;
    /** 身份证 */
    private String identity;
    /** 手机号码 */
    private String sphone;
    /** 状态(0未审核，1已审核，2失效) */
    private Integer itype;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 评论子表 */
//    @OneToMany(fetch= FetchType.EAGER,cascade={CascadeType.REMOVE},mappedBy="netDriver")
    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="netPassenger")
    private List<NetComment> netComments=new ArrayList<NetComment>();
}
