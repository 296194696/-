package com.water.irrigation.service.water.charge.impl;

import com.water.irrigation.dao.water.charge.ChargeCityDao;
import com.water.irrigation.dao.water.charge.ChargeDao;
import com.water.irrigation.dao.water.charge.ChargeDaoImpl;
import com.water.irrigation.dao.water.charge.ChargeVoDao;
import com.water.irrigation.entity.dto.water.charge.ChargeDto;
import com.water.irrigation.entity.dto.water.charge.ChargeVoDto;
import com.water.irrigation.entity.sys.user.SysUser;
import com.water.irrigation.entity.vo.charge.ChargeCity;
import com.water.irrigation.entity.vo.charge.ChargeSumVo;
import com.water.irrigation.entity.vo.charge.ChargeVo;
import com.water.irrigation.entity.water.charge.Charge;
import com.water.irrigation.service.sys.user.SysUserService;
import com.water.irrigation.service.water.charge.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * 水费缴费服务层
 * @author lichunlei
 *
 */
@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    private ChargeDao chargeDao;
    @Autowired
    private ChargeVoDao chargeVoDao;
    @Autowired
    private ChargeDaoImpl chargeDaoImpl;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ChargeCityDao chargeCityDao;
    /**
     * 根据条件获取对应的水费缴费数据
     *
     * @param conditions 水费缴费过滤条件
     * @param pageable   分页
     * @return 指定的水费缴费列表
     */
    @Override
    public Page<Charge> findAll(ChargeDto conditions, Pageable pageable) {
        SysUser sysUser=sysUserService.getUser();
        conditions.setIuserid(sysUser.getIndocno());
        return chargeDao.findAll(getSpecification(conditions), pageable);
    }

    /**
     * 根据编号获取对应的缴费信息数据
     * @param indocno 主键
     * @return 指定的缴费信息信息
     */
    @Override
    public Charge findOne(Long indocno) {
        return chargeDao.findById(indocno).get();
    }

    /**
     * 添加缴费信息
     * @param charge 要添加的缴费信息信息
     * @return 添加的缴费信息信息
     */
    @Override
    public Charge add(Charge charge) {
        return chargeDao.save(charge);
    }

    /**
     * 修改缴费信息
     * @param charge 缴费信息信息
     * @return  更新后缴费信息信息
     */
    @Override
    public Charge update(Charge charge) {
        Charge chargeObj =
                this.chargeDao.findById(charge.getIndocno()).get();
        //UpdateHelperUtils.copyNullProperties(orgObj, Charge);
        return chargeDao.save(chargeObj);
    }

    /**
     * 删除缴费信息信息
     * @param sysCompanys 需要删除的水费缴费
     */
    @Override
    public void delete(List<Charge> sysCompanys) {
        for(Charge charge : sysCompanys ) {
            chargeDao.deleteById(charge.getIndocno());
        }
    }

    /**
     * 更新年度缴费信息
     * @param charge 缴费信息信息
     * @return  更新后缴费信息信息
     */
    @Override
    public List<Charge> updateSum(Charge charge) {
        List<Charge> listCharges=this.chargeDao.findByIyear(charge.getIyear());
        for(Charge charges:listCharges){
            charges.setItype(1);
            chargeDao.save(charges);
        }
        return listCharges;
    }

    /**
     * 查询条件构建
     * @param condition
     * @return 查询条件
     */
    private Specification<Charge> getSpecification(ChargeDto condition){
        // 查询条件构造
        Specification<Charge> spec = new Specification<Charge>(){

            private static final long serialVersionUID = 3652606507255419478L;

            @Override
            public Predicate toPredicate(Root<Charge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicates = new ArrayList<Predicate>();
                if (condition.getSname()!=null && !"".equals(condition.getSname())) {
                    listPredicates.add(cb.like(root.get("sname").as(String.class),
                            "%"+condition.getSname()+"%"));
                }
                if (condition.getIyear()!=null && !"".equals(condition.getIyear())) {
                    listPredicates.add(cb.equal(root.get("iyear").as(Integer.class),
                            condition.getIyear()));
                }
                if (condition.getIuserid()!=null && !"".equals(condition.getIuserid())) {
                    listPredicates.add(cb.equal(root.get("iuserid").as(Integer.class),
                            condition.getIuserid()));
                }
                Predicate[] arrayPredicates = new Predicate[listPredicates.size()];
                Predicate p = cb.and(listPredicates.toArray(arrayPredicates));
                return p;
            }
        };
        return spec;
    }
    @Override
    public Page<ChargeVo> findUserByDyCondition(ChargeVoDto chargeVoDto
            , Pageable pageable){
        SysUser sysUser=sysUserService.getUser();
        chargeVoDto.setIuserid(sysUser.getIndocno());
        return chargeDaoImpl.findUserByDyCondition(chargeVoDto, pageable);
    }
    /**
     * 根据作物名称统计金额 输出饼状图
     */
    @Override
    public Map<String, List> findSumBySname(){
        List<ChargeSumVo> charges=this.chargeVoDao.findAll();
        Map<String,List> map =new HashMap<String,List>();
        List<String> snames=new ArrayList<String>();
        List<String> moneys=new ArrayList<String>();
        for(ChargeSumVo charge:charges){
            snames.add(charge.getName());
            moneys.add(String.valueOf(charge.getValue()));
        }
        map.put("snames",snames);
        map.put("charges",charges);
        return map;
    }

    /**
     * 政区金额统计
     * @return
     */
    @Override
    public Map findChargeCity(){
        Map entity=new HashMap();
        Calendar date = Calendar.getInstance();
        Integer year = Integer.valueOf(date.get(Calendar.YEAR));
        List seriesList=new ArrayList();
        List<String> name=new ArrayList<String>();
        for(Integer i=0;i<=1;i++) {
            List<ChargeCity> chargeCitys = chargeCityDao.findQuery(year-i);
            Map seriesMap = new HashMap();
            seriesMap.put("name", year-i+"年");
            seriesMap.put("type", "bar");
            List data = new ArrayList();
            for(ChargeCity chargeCity:chargeCitys){
                data.add(chargeCity.getWatermoney());
                if(i==0) {
                    name.add(chargeCity.getScity());
                }
            }
            seriesMap.put("data",data);
            seriesList.add(seriesMap);
        }
        entity.put("name",name);
        entity.put("series",seriesList);
        return entity;
    }

}
