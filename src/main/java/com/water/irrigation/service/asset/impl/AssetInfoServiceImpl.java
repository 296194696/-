package com.water.irrigation.service.asset.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.water.irrigation.dao.asset.AssetInfoDao;
import com.water.irrigation.entity.asset.AssetInfo;
import com.water.irrigation.entity.dto.asset.AssetInfoDto;
import com.water.irrigation.service.asset.AssetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * 资产信息服务层
 * @author lichunlei
 *
 */
@Service
public class AssetInfoServiceImpl implements AssetInfoService {

    @Autowired
    private AssetInfoDao assetInfoDao;

    /**
     * 根据条件获取对应的资产信息数据
     *
     * @param conditions 资产信息过滤条件
     * @param pageable   分页
     * @return 指定的资产信息列表
     */
    @Override
    public Page<AssetInfo> findAll(AssetInfoDto conditions, Pageable pageable) {
        return assetInfoDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的资产数据
     * @param indocno 主键
     * @return 指定的资产信息
     */
    @Override
    public AssetInfo findOne(Long indocno) {
        return assetInfoDao.findById(indocno).get();
    }

    /**
     * 添加资产
     * @param assetInfo 要添加的资产信息
     * @return 添加的资产信息
     */
    @Override
    public AssetInfo add(AssetInfo assetInfo) {
        return assetInfoDao.save(assetInfo);
    }

    /**
     * 修改资产
     * @param assetInfo 资产信息
     * @return  更新后资产信息
     */
    @Override
    public AssetInfo update(AssetInfo assetInfo) {
        AssetInfo orgObj =
                this.assetInfoDao.findById(assetInfo.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, assetInfo);
        return assetInfoDao.save(assetInfo);
    }

    /**
     * 删除资产信息
     * @param sysCompanys 需要删除的资产信息
     */
    @Override
    public void delete(List<AssetInfo> sysCompanys) {
        for(AssetInfo assetInfo : sysCompanys ) {
            assetInfoDao.deleteById(assetInfo.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<AssetInfo> getSpecification(AssetInfoDto condition){
        // 查询条件构造
        Specification<AssetInfo> spec = new Specification<AssetInfo>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<AssetInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sunitname").as(String.class),
                            "%"+condition.getSname()+"%"));
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
