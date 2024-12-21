package cn.edu.xmu.oomall.freight.dao;
import cn.edu.xmu.javaee.core.mapper.RedisUtil;
import cn.edu.xmu.oomall.freight.dao.bo.Region;
import cn.edu.xmu.oomall.freight.dao.openfeign.RegionDao;
import cn.edu.xmu.oomall.freight.mapper.jpa.WarehouseRegionPoMapper;
import cn.edu.xmu.oomall.freight.mapper.po.WarehouseRegionPo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 37220222203558
 * 2024-dsg116
 */
@Repository
@Slf4j
@RefreshScope
@RequiredArgsConstructor
public class WarehouseRegionDao {

    private final static String KEY = "W%d";

    private final WarehouseRegionPoMapper warehouseRegionPoMapper;

    private final RegionDao regionDao;

    private final RedisUtil redisUtil;

    @Value("3600")
    private int timeout;

    /**
     * @author 37220222203558
     * 2024-dsg116
     */
    public List<Region> findByWarehouseId(Long warehouseId) throws RuntimeException {
        log.debug("findByWarehouseId: warehouseId = {}", warehouseId);
        String key = String.format(KEY, warehouseId);
        List<Long> regionIds = (List<Long>) redisUtil.get(key);
        if (Objects.isNull(regionIds)) {
            List<WarehouseRegionPo> poList = warehouseRegionPoMapper.findAllByWarehouseIdOrderById(warehouseId);
            List<Region> regionList = new ArrayList<>();
            List<Long> boRegionIds = poList.stream().map(WarehouseRegionPo::getRegionId).collect(Collectors.toList());
            Optional.of(key).ifPresent(buildKey -> redisUtil.set(buildKey, (Serializable) boRegionIds, timeout));
            for (WarehouseRegionPo po : poList) {
                regionList.add(regionDao.findById(po.getRegionId()));
            }
            return regionList;
        }
        else {
            return regionIds.stream()
                    .map(regionDao::findById)
                    .collect(Collectors.toList());
        }
    }

    /**
     * @author 37220222203558
     * 2024-dsg116
     */
    public String delete(Long warehouseId, Long regionId){
        log.debug("delete: warehouseId = {}, regionId = {}", warehouseId, regionId);
        return String.format(KEY, warehouseId);
    }
}
