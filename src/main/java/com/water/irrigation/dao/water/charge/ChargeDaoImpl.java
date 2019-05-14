package com.water.irrigation.dao.water.charge;

import com.water.irrigation.entity.dto.water.charge.ChargeVoDto;
import com.water.irrigation.entity.vo.charge.ChargeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * 费用查询的自定义结果集
 * @author licl
 *
 */
@Repository
public class ChargeDaoImpl {

    @Autowired
    @Qualifier("entityManager")
    @PersistenceContext
    private EntityManager entityManager;

    public Page<ChargeVo> findUserByDyCondition(ChargeVoDto chargeVoDto
            , Pageable pageable) {
        StringBuilder dataSql = new StringBuilder(
                " select sum,a.iyear,need,itype " +
                        " from (select sum(watermoney) as sum,iyear from water_charge  GROUP BY iyear) a " +
                        " left join  ( select sum(watermoney) as need ,iyear,itype  from water_charge  " +
                        " where itype=0  GROUP BY iyear) b on  a.iyear=b.iyear   ");
        StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
        StringBuilder countSql = new StringBuilder("select count(1) from (");
        if(!StringUtils.isEmpty(chargeVoDto.getIyear())) {
            whereSql.append(" and a.iyear=:iyear ");
        }
        dataSql.append(whereSql);
        countSql.append(dataSql).append(") tab");
        dataSql.append(" ORDER BY iyear desc");

        Query dataQuery = entityManager.createNativeQuery(dataSql.toString(), ChargeVo.class);

        Query countQuery = entityManager.createNativeQuery(countSql.toString());

        if(!StringUtils.isEmpty(chargeVoDto.getIyear())) {
            dataQuery.setParameter("iyear", chargeVoDto.getIyear());
            countQuery.setParameter("iyear", chargeVoDto.getIyear());
        }
        dataQuery.setFirstResult((pageable.getPageNumber())*(pageable.getPageSize()));
        dataQuery.setMaxResults(pageable.getPageSize());
        BigInteger count = (BigInteger) countQuery.getSingleResult();
        Long total = count.longValue();
        try {
            @SuppressWarnings("unchecked")
            List<ChargeVo> content2 = total > pageable.getOffset() ? dataQuery.getResultList() : Collections.<ChargeVo> emptyList();
            return new PageImpl<ChargeVo>(content2, pageable, total);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
