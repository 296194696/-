package com.water.irrigation.service.net.passenger.impl;

import com.water.irrigation.dao.net.passenger.NetPassengerDao;
import com.water.irrigation.dao.sys.user.SysUserDao;
import com.water.irrigation.entity.dto.net.passenger.NetPassengerDto;
import com.water.irrigation.entity.net.passenger.NetPassenger;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.service.net.passenger.NetPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 乘客信息服务层接口
 * @author lichunlei
 *
 */
@Service
public class NetPassengerServiceImpl implements NetPassengerService {

    @Autowired
    private NetPassengerDao netPassengerDao;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据条件获取对应的乘客信息数据
     *
     * @param conditions 乘客信息过滤条件
     * @param pageable   分页
     * @return 指定的乘客信息列表
     */
    @Override
    public Page<NetPassenger> findAll(NetPassengerDto conditions, Pageable pageable) {
        return netPassengerDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的乘客数据
     * @param indocno 主键
     * @return 指定的乘客信息
     */
    @Override
    public NetPassenger findOne(Long indocno) {
        return netPassengerDao.findById(indocno).get();
    }

    /**
     * 添加乘客
     * @param netPassenger 要添加的乘客信息
     * @return 添加的乘客信息
     */
    @Override
    public NetPassenger add(NetPassenger netPassenger) {
        SysUser sysUser=new SysUser();
        sysUser.setUsername(netPassenger.getUsername());
        sysUser.setPassword(netPassenger.getPassword());
        sysUser.setRole("passenger");
        sysUserDao.save(sysUser);
        return netPassengerDao.save(netPassenger);
    }

    /**
     * 修改乘客
     * @param netPassenger 乘客信息
     * @return  更新后乘客信息
     */
    @Override
    public NetPassenger update(NetPassenger netPassenger) {
        NetPassenger orgObj =
                this.netPassengerDao.findById(netPassenger.getIndocno()).get();
        netPassenger.setUsername(orgObj.getUsername());
        netPassenger.setPassword(orgObj.getPassword());
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, NetPassenger);
        return netPassengerDao.save(netPassenger);
    }

    /**
     * 删除乘客信息
     * @param sysCompanys 需要删除的乘客信息
     */
    @Override
    public void delete(List<NetPassenger> sysCompanys) {
        for(NetPassenger NetPassenger : sysCompanys ) {
            netPassengerDao.deleteById(NetPassenger.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<NetPassenger> getSpecification(NetPassengerDto condition){
        // 查询条件构造
        Specification<NetPassenger> spec = new Specification<NetPassenger>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<NetPassenger> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sunitname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                //listPredicates.add(cb.equal(root.get("idel").as(Integer.class),0 ));
                Predicate[] arrayPredicates = new Predicate[listPredicates.size()];
                Predicate p = cb.and(listPredicates.toArray(arrayPredicates));
                return p;
            }
        };
        return spec;
    }

}
