package com.water.irrigation.service.net.driver.impl;

import com.water.irrigation.dao.net.driver.NetDriverDao;
import com.water.irrigation.dao.sys.user.SysUserDao;
import com.water.irrigation.entity.dto.net.driver.NetDriverDto;
import com.water.irrigation.entity.net.driver.NetDriver;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.service.net.driver.NetDriverService;
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

@Service
public class NetDriverServiceImpl implements NetDriverService {

    @Autowired
    private NetDriverDao netDriverDao;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据条件获取对应的资产信息数据
     *
     * @param conditions 资产信息过滤条件
     * @param pageable   分页
     * @return 指定的资产信息列表
     */
    @Override
    public Page<NetDriver> findAll(NetDriverDto conditions, Pageable pageable) {
        return netDriverDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的工会组织数据
     * @param indocno 主键
     * @return 指定的工会组织信息
     */
    @Override
    public NetDriver findOne(Long indocno) {
        return netDriverDao.findById(indocno).get();
    }

    /**
     * 添加工会组织
     * @param NetDriver 要添加的工会组织信息
     * @return 添加的工会组织信息
     */
    @Override
    public NetDriver add(NetDriver NetDriver) {
        return netDriverDao.save(NetDriver);
    }

    /**
     * 修改工会组织
     * @param netDriver 工会组织信息
     * @return  更新后工会组织信息
     */
    @Override
    public NetDriver update(NetDriver netDriver) {
        NetDriver orgObj =
                this.netDriverDao.findById(netDriver.getIndocno()).get();
        netDriver.setUsername(orgObj.getUsername());
        netDriver.setPassword(orgObj.getPassword());
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, NetDriver);
        return netDriverDao.save(netDriver);
    }

    /**
     * 删除工会组织信息
     * @param sysCompanys 需要删除的资产信息
     */
    @Override
    public void delete(List<NetDriver> sysCompanys) {
        for(NetDriver NetDriver : sysCompanys ) {
            netDriverDao.deleteById(NetDriver.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<NetDriver> getSpecification(NetDriverDto condition){
        // 查询条件构造
        Specification<NetDriver> spec = new Specification<NetDriver>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<NetDriver> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                if (condition.getItype()!=null ) {
                    listPredicates.add(cb.equal(root.get("itype").as(Integer.class),
                            condition.getItype()));
                }
                //listPredicates.add(cb.equal(root.get("idel").as(Integer.class),0 ));
                Predicate[] arrayPredicates = new Predicate[listPredicates.size()];
                Predicate p = cb.and(listPredicates.toArray(arrayPredicates));
                return p;
            }
        };
        return spec;
    }

    /**
     * 添加工会组织
     * @param NetDriver 要添加的工会组织信息
     * @return 添加的工会组织信息
     */
    @Override
    public NetDriver check(NetDriver NetDriver) {
        NetDriver netDriver=netDriverDao.findByIndocno(NetDriver.getIndocno());
        netDriver.setItype(1);
        String username=NetDriver.getUsername();
        String password=NetDriver.getPassword();
        SysUser sysUser=new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        sysUser.setRole("driver");
        sysUserDao.save(sysUser);
        return netDriverDao.save(netDriver);


    }
}

