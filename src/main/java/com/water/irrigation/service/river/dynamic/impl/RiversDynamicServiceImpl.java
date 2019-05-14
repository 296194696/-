package com.water.irrigation.service.river.dynamic.impl;

import com.water.irrigation.dao.river.dynamic.RiversDynamicDao;
import com.water.irrigation.entity.dto.river.dynamic.RiversDynamicDto;
import com.water.irrigation.entity.river.dynamic.RiversDynamic;
import com.water.irrigation.service.river.dynamic.RiversDynamicService;
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
 * 河流动态信息服务层
 * @author lichunlei
 *
 */
@Service
public class RiversDynamicServiceImpl implements RiversDynamicService {

    @Autowired
    private RiversDynamicDao riversDynamicDao;

    /**
     * 根据条件获取对应的河流动态信息数据
     *
     * @param conditions 河流动态信息过滤条件
     * @param pageable   分页
     * @return 指定的河流动态信息列表
     */
    @Override
    public Page<RiversDynamic> findAll(RiversDynamicDto conditions, Pageable pageable) {
        return riversDynamicDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的河流动态数据
     * @param indocno 主键
     * @return 指定的河流动态信息
     */
    @Override
    public RiversDynamic findOne(Long indocno) {
        return riversDynamicDao.findById(indocno).get();
    }

    /**
     * 添加河流动态
     * @param riversDynamic 要添加的河流动态信息
     * @return 添加的河流动态信息
     */
    @Override
    public RiversDynamic add(RiversDynamic riversDynamic) {
        return riversDynamicDao.save(riversDynamic);
    }

    /**
     * 修改河流动态
     * @param riversDynamic 河流动态信息
     * @return  更新后河流动态信息
     */
    @Override
    public RiversDynamic update(RiversDynamic riversDynamic) {
        RiversDynamic orgObj =
                this.riversDynamicDao.findById(riversDynamic.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, RiversDynamic);
        return riversDynamicDao.save(riversDynamic);
    }

    /**
     * 删除河流动态信息
     * @param sysCompanys 需要删除的河流动态信息
     */
    @Override
    public void delete(List<RiversDynamic> sysCompanys) {
        for(RiversDynamic riversDynamic : sysCompanys ) {
            riversDynamicDao.deleteById(riversDynamic.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<RiversDynamic> getSpecification(RiversDynamicDto condition){
        // 查询条件构造
        Specification<RiversDynamic> spec = new Specification<RiversDynamic>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<RiversDynamic> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                if (condition.getLenth()!=null && !"".equals(condition.getLenth())) {
                    listPredicates.add(cb.equal(root.get("lenth").as(Double.class),
                            condition.getLenth()));
                }
                if (condition.getQuality()!=null && !"".equals(condition.getQuality())) {
                    listPredicates.add(cb.equal(root.get("quality").as(Integer.class),
                            condition.getQuality()));
                }
                if (condition.getPollution()!=null && !"".equals(condition.getPollution())) {
                    listPredicates.add(cb.equal(root.get("pollution").as(Integer.class),
                            condition.getPollution()));
                }
                if (condition.getSourcesnumber()!=null && !"".equals(condition.getSourcesnumber())) {
                    listPredicates.add(cb.equal(root.get("sourcesnumber").as(Integer.class),
                            condition.getSourcesnumber()));
                }
                if (condition.getUsername()!=null && !"".equals(condition.getUsername())) {
                    listPredicates.add(cb.like(root.get("username").as(String.class),
                            "%"+condition.getUsername()+"%"));
                }
//                listPredicates.add(cb.equal(root.get("idel").as(Integer.class),0 ));
                Predicate[] arrayPredicates = new Predicate[listPredicates.size()];
                Predicate p = cb.and(listPredicates.toArray(arrayPredicates));
                return p;
            }
        };
        return spec;
    }
}
