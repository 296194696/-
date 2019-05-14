package com.water.irrigation.service.water.crop.impl;

import com.water.irrigation.dao.sys.user.SysUserDao;
import com.water.irrigation.dao.water.block.BlockInfoDao;
import com.water.irrigation.dao.water.charge.ChargeDao;
import com.water.irrigation.dao.water.crop.CropDao;
import com.water.irrigation.dao.water.rate.RateDao;
import com.water.irrigation.entity.dto.water.crop.CropDto;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.entity.water.block.BlockInfo;
import com.water.irrigation.entity.water.charge.Charge;
import com.water.irrigation.entity.water.crops.Crop;
import com.water.irrigation.entity.water.rate.Rate;
import com.water.irrigation.service.water.crop.CropService;
import com.water.irrigation.utils.dozer.DozerHelperUtils;
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
 * 作物信息服务层
 * @author lichunlei
 *
 */
@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDao cropDao;
    @Autowired
    private BlockInfoDao blockInfoDao;
    @Autowired
    private ChargeDao chargeDao;
    @Autowired
    private DozerHelperUtils dozerHelperUtils;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private RateDao rateDao;
    /**
     * 根据条件获取对应的作物信息数据
     *
     * @param conditions 作物信息过滤条件
     * @param pageable   分页
     * @return 指定的作物信息列表
     */
    @Override
    public Page<Crop> findAll(CropDto conditions, Pageable pageable) {
        return cropDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的作物数据
     * @param indocno 主键
     * @return 指定的作物信息
     */
    @Override
    public Crop findOne(Long indocno) {

        return cropDao.findById(indocno).get();
    }

    /**
     * 添加作物
     * @param crop 要添加的作物信息
     * @return 添加的作物信息
     */
    @Override
    public Crop add(Crop crop) {

        BlockInfo blockInfo=blockInfoDao.findById(crop.getIblockid()).get();
        Double area=blockInfo.getSarea();
        crop.setNeedwater(area*crop.getIquantity());
        chargeDao.save(saveDetail(crop));
        return cropDao.save(crop);
    }


    /**
     * 修改作物
     * @param crop 作物信息
     * @return  更新后作物信息
     */
    @Override
    public Crop update(Crop crop) {
        Crop cropObj =
                this.cropDao.findById(crop.getIndocno()).get();
        //UpdateHelperUtils.copyNullProperties(orgObj, Crop);
        BlockInfo blockInfo=blockInfoDao.findById(cropObj.getIblockid()).get();
        Double area=blockInfo.getSarea();
        cropObj.setNeedwater(area*crop.getIquantity());
        return cropDao.save(cropObj);
    }

    /**
     * 删除作物信息
     * @param sysCompanys 需要删除的作物信息
     */
    @Override
    public void delete(List<Crop> sysCompanys) {
        for(Crop crop : sysCompanys ) {
            cropDao.deleteById(crop.getIndocno());
        }
    }

    /**
     * 保存缴费明细子表信息
     * @param crop
     * @return
     */
    private Charge saveDetail(Crop crop){
        Charge charge =
                dozerHelperUtils.convert(crop, Charge.class);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username=userDetails.getUsername();
        SysUser sysUser=sysUserDao.findByUsername(username);
        charge.setSysUserCharge(sysUser);
        Rate rate=rateDao.findMaxIndocno();
        Double suserrate=null;
        if(sysUser.getIroleid()==3){
            suserrate=rate.getSdeptrate();
        }else {
            suserrate=rate.getSuserrate();
        }
        charge.setWatermoney(crop.getNeedwater()*suserrate);
        charge.setItype(0);
        charge.setCrop(crop);
        return charge;
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<Crop> getSpecification(CropDto condition){
        // 查询条件构造
        Specification<Crop> spec = new Specification<Crop>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<Crop> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                if (condition.getIblockid()!=null && !"".equals(condition.getIblockid())) {
                    listPredicates.add(cb.equal(root.get("iblockid").as(Long.class),
                            condition.getIblockid()));
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
