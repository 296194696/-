package com.water.irrigation.service.water.ratr.impl;

import com.water.irrigation.dao.water.rate.RateDao;
import com.water.irrigation.entity.dto.water.rate.RateDto;
import com.water.irrigation.entity.water.rate.Rate;
import com.water.irrigation.service.water.ratr.RateService;
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
 * 水率信息服务层
 * @author lichunlei
 *
 */
@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateDao rateDao;

    /**
     * 根据条件获取对应的水率信息数据
     *
     * @param conditions 水率信息过滤条件
     * @param pageable   分页
     * @return 指定的水率信息列表
     */
    @Override
    public Page<Rate> findAll(RateDto conditions, Pageable pageable) {
        return rateDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的水率组织数据
     * @param indocno 主键
     * @return 指定的水率组织信息
     */
    @Override
    public Rate findOne(Long indocno) {
        return rateDao.findById(indocno).get();
    }

    /**
     * 添加水率组织
     * @param rate 要添加的水率组织信息
     * @return 添加的水率组织信息
     */
    @Override
    public Rate add(Rate rate) {
        return rateDao.save(rate);
    }

    /**
     * 修改水率组织
     * @param rate 水率组织信息
     * @return  更新后水率组织信息
     */
    @Override
    public Rate update(Rate rate) {
        Rate orgObj =
                this.rateDao.findById(rate.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, Rate);
        return rateDao.save(rate);
    }

    /**
     * 删除水率组织信息
     * @param sysCompanys 需要删除的水率信息
     */
    @Override
    public void delete(List<Rate> sysCompanys) {
        for(Rate rate : sysCompanys ) {
            rateDao.deleteById(rate.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<Rate> getSpecification(RateDto condition){
        // 查询条件构造
        Specification<Rate> spec = new Specification<Rate>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<Rate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSuserrate()!=null && !"".equals(condition.getSuserrate())) {
                    listPredicates.add(cb.like(root.get("suserrate").as(String.class),
                            "%"+condition.getSuserrate()+"%"));
                }
                if (condition.getIyear()!=null && !"".equals(condition.getIyear())) {
                    listPredicates.add(cb.equal(root.get("iyear").as(Long.class),
                            condition.getIyear()));
                }
//                listPredicates.add(cb.equal(root.get("idel").as(Integer.class),0 ));
                Predicate[] arrayPredicates = new Predicate[listPredicates.size()];
                Predicate p = cb.and(listPredicates.toArray(arrayPredicates));
                return p;
            }
        };
        return spec;
    }

    public Rate findMaxIndocno(){
        return rateDao.findMaxIndocno();
    }
}
