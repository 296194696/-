package com.water.irrigation.service.net.comment.impl;

import com.water.irrigation.dao.net.comment.NetCommentDao;
import com.water.irrigation.dao.net.driver.NetDriverDao;
import com.water.irrigation.dao.net.passenger.NetPassengerDao;
import com.water.irrigation.entity.dto.net.comment.NetCommentDto;
import com.water.irrigation.entity.net.comment.NetComment;
import com.water.irrigation.entity.net.driver.NetDriver;
import com.water.irrigation.entity.net.passenger.NetPassenger;
import com.water.irrigation.service.net.comment.NetCommentService;
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
public class NetCommentServiceImpl implements NetCommentService {

    @Autowired
    private NetCommentDao netCommentDao;
    @Autowired
    private NetDriverDao netDriverDao;
    @Autowired
    private NetPassengerDao netPassengerDao;

    /**
     * 根据条件获取对应的订单信息数据
     *
     * @param conditions 订单信息过滤条件
     * @param pageable   分页
     * @return 指定的订单信息列表
     */
    @Override
    public Page<NetComment> findAll(NetCommentDto conditions, Pageable pageable) {
        return netCommentDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的订单数据
     * @param indocno 主键
     * @return 指定的订单信息
     */
    @Override
    public NetComment findOne(Long indocno) {
        return netCommentDao.findById(indocno).get();
    }

    /**
     * 添加订单
     * @param netComment 要添加的订单信息
     * @return 添加的订单信息
     */
    @Override
    public NetComment add(NetComment netComment,String username) {
        NetPassenger netPassenger=netPassengerDao.findByUsername(username);
        netComment.setNetPassenger(netPassenger);
        return netCommentDao.save(netComment);
    }

    /**
     * 修改订单
     * @param netComment 订单信息
     * @return  更新后订单信息
     */
    @Override
    public NetComment update(NetComment netComment) {
        NetComment orgObj =
                this.netCommentDao.findById(netComment.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, NetComment);
        return netCommentDao.save(netComment);
    }

    /**
     * 删除订单信息
     * @param sysCompanys 需要删除的订单信息
     */
    @Override
    public void delete(List<NetComment> sysCompanys) {
        for(NetComment netComment : sysCompanys ) {
            netCommentDao.deleteById(netComment.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<NetComment> getSpecification(NetCommentDto condition){
        // 查询条件构造
        Specification<NetComment> spec = new Specification<NetComment>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<NetComment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSno()!=null && !"".equals(condition.getSno())) {
                    listPredicates.add(cb.like(root.get("sno").as(String.class),
                            "%"+condition.getSno()+"%"));
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
     * 接单订单
     * @param netComment 要接受的订单信息
     * @return 接受的订单信息
     */
    @Override
    public NetComment order(NetComment netComment) {
        Long indocno=netComment.getIndocno();
        NetComment netComment1=netCommentDao.findByIndocno(indocno);
        netComment1.setItype(1);
        return netCommentDao.save(netComment1);
    }

    /**
     * 完成订单
     * @param netComment 完成的订单信息
     * @return 完成的订单信息
     */
    @Override
    public NetComment commit(NetComment netComment,String username) {
        Long indocno=netComment.getIndocno();
        NetComment netComment1=netCommentDao.findByIndocno(indocno);
        netComment1.setItype(2);
        NetDriver netDriver=netDriverDao.findByUsername(username);
        netComment1.setNetDriver(netDriver);
        return netCommentDao.save(netComment1);
    }

}
