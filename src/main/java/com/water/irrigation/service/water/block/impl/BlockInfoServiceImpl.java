package com.water.irrigation.service.water.block.impl;

import com.water.irrigation.dao.sys.user.SysUserDao;
import com.water.irrigation.dao.water.block.BlockInfoDao;
import com.water.irrigation.entity.dto.water.block.BlockInfoDto;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.entity.water.block.BlockInfo;
import com.water.irrigation.service.water.block.BlockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 地块信息服务层
 * @author lichunlei
 *
 */
@Service
public class BlockInfoServiceImpl implements BlockInfoService {

    @Autowired
    private BlockInfoDao blockInfoDao;
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 根据条件获取对应的地块信息数据
     *
     * @param conditions 地块信息过滤条件
     * @param pageable   分页
     * @return 指定的地块信息列表
     */
    @Override
    public Page<BlockInfo> findAll(BlockInfoDto conditions, Pageable pageable) {
        return blockInfoDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的地块组织数据
     * @param indocno 主键
     * @return 指定的地块组织信息
     */
    @Override
    public BlockInfo findOne(Long indocno) {
        return blockInfoDao.findById(indocno).get();
    }

    /**
     * 添加地块组织
     * @param blockInfo 要添加的地块组织信息
     * @return 添加的地块组织信息
     */
    @Override
    public BlockInfo add(BlockInfo blockInfo) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username=userDetails.getUsername();
        SysUser sysUser=sysUserDao.findByUsername(username);
        blockInfo.setSarea(blockInfo.getSquarex()*blockInfo.getSquarey());
        blockInfo.setSysUser(sysUser);
        return blockInfoDao.save(blockInfo);
    }

    /**
     * 修改地块组织
     * @param blockInfo 地块组织信息
     * @return  更新后地块组织信息
     */
    @Override
    public BlockInfo update(BlockInfo blockInfo) {
        BlockInfo orgObj =
                this.blockInfoDao.findById(blockInfo.getIndocno()).get();
        if(orgObj!=null) {
            System.out.println("无效的修改对象");
        }
        //UpdateHelperUtils.copyNullProperties(orgObj, BlockInfo);
        blockInfo.setSarea(blockInfo.getSquarex()*blockInfo.getSquarey());
        return blockInfoDao.save(blockInfo);
    }

    /**
     * 删除地块组织信息
     * @param sysCompanys 需要删除的地块信息
     */
    @Override
    public void delete(List<BlockInfo> sysCompanys) {
        for(BlockInfo BlockInfo : sysCompanys ) {
            blockInfoDao.deleteById(BlockInfo.getIndocno());
        }
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<BlockInfo> getSpecification(BlockInfoDto condition){
        // 查询条件构造
        Specification<BlockInfo> spec = new Specification<BlockInfo>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<BlockInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                if (condition.getSplace()!=null && !"".equals(condition.getSplace())) {
                    listPredicates.add(cb.like(root.get("splace").as(String.class),
                            "%"+condition.getSplace()+"%"));
                }
                if (condition.getSdept()!=null && !"".equals(condition.getSdept())) {
                    listPredicates.add(cb.like(root.get("sdept").as(String.class),
                            "%"+condition.getSdept()+"%"));
                }
                if (condition.getIbtype()!=null && !"".equals(condition.getIbtype())) {
                    listPredicates.add(cb.equal(root.get("ibtype").as(Integer.class),
                            condition.getIbtype()));
                }
                if (condition.getIyear()!=null && !"".equals(condition.getIyear())) {
                    listPredicates.add(cb.equal(root.get("iyear").as(Integer.class),
                            condition.getIyear()));
                }
                if (condition.getStownship()!=null && !"".equals(condition.getStownship())) {
                    listPredicates.add(cb.like(root.get("stownship").as(String.class),
                            "%"+condition.getStownship()+"%"));
                }
                if (condition.getSvillage()!=null && !"".equals(condition.getSvillage())) {
                    listPredicates.add(cb.like(root.get("svillage").as(String.class),
                            "%"+condition.getSvillage()+"%"));
                }
                if (condition.getScity()!=null && !"".equals(condition.getScity())) {
                    listPredicates.add(cb.like(root.get("scity").as(String.class),
                            "%"+condition.getScity()+"%"));
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
