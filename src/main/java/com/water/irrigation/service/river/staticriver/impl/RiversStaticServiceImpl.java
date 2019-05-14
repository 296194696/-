package com.water.irrigation.service.river.staticriver.impl;

import com.water.irrigation.dao.river.staticriver.RiversStaticDao;
import com.water.irrigation.entity.dto.river.staticriver.RiversStaticDto;
import com.water.irrigation.entity.river.staticriver.RiversStatic;
import com.water.irrigation.service.river.staticriver.RiversStaticService;
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
 * 河流静态信息服务层
 * @author lichunlei
 *
 */
@Service
public class RiversStaticServiceImpl implements RiversStaticService {

    @Autowired
    private RiversStaticDao riversStaticDao;

    /**
     * 根据条件获取对应的河流静态信息数据
     *
     * @param conditions 河流静态信息过滤条件
     * @param pageable   分页
     * @return 指定的河流静态信息列表
     */
    @Override
    public Page<RiversStatic> findAll(RiversStaticDto conditions, Pageable pageable) {
        return riversStaticDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的河流静态数据
     * @param indocno 主键
     * @return 指定的河流静态信息
     */
    @Override
    public RiversStatic findOne(Long indocno) {
        return riversStaticDao.findById(indocno).get();
    }

    /**
     * 添加河流静态
     * @param riversStatic 要添加的河流静态信息
     * @return 添加的河流静态信息
     */
    @Override
    public RiversStatic add(RiversStatic riversStatic) {
        return riversStaticDao.save(riversStatic);
    }

    /**
     * 修改河流静态
     * @param riversStatic 河流静态信息
     * @return  更新后河流静态信息
     */
    @Override
    public RiversStatic update(RiversStatic riversStatic) {
        RiversStatic orgObj =
                this.riversStaticDao.findById(riversStatic.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, RiversStatic);
        return riversStaticDao.save(riversStatic);
    }

    /**
     * 删除河流静态信息
     * @param sysCompanys 需要删除的河流静态信息
     */
    @Override
    public void delete(List<RiversStatic> sysCompanys) {
        for(RiversStatic riversStatic : sysCompanys ) {
            riversStaticDao.deleteById(riversStatic.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<RiversStatic> getSpecification(RiversStaticDto condition){
        // 查询条件构造
        Specification<RiversStatic> spec = new Specification<RiversStatic>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<RiversStatic> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                if (condition.getLenth()!=null && !"".equals(condition.getLenth())) {
                    listPredicates.add(cb.equal(root.get("lenth").as(Double.class),
                            condition.getLenth()));
                }
                if (condition.getSbasin()!=null && !"".equals(condition.getSbasin())) {
                    listPredicates.add(cb.like(root.get("sbasin").as(String.class),
                            "%"+condition.getSbasin()+"%"));
                }
                if (condition.getState()!=null && !"".equals(condition.getState())) {
                    listPredicates.add(cb.like(root.get("state").as(String.class),
                            "%"+condition.getState()+"%"));
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
