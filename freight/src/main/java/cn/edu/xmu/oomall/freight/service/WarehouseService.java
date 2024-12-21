package cn.edu.xmu.oomall.freight.service;

import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.oomall.freight.dao.ContractDao;
import cn.edu.xmu.oomall.freight.dao.openfeign.RegionDao;
import cn.edu.xmu.oomall.freight.dao.WarehouseDao;
import cn.edu.xmu.oomall.freight.dao.bo.Contract;
import cn.edu.xmu.oomall.freight.dao.bo.Region;
import cn.edu.xmu.oomall.freight.dao.bo.Warehouse;
import cn.edu.xmu.oomall.freight.dao.WarehouseRegionDao;
import cn.edu.xmu.oomall.freight.mapper.po.WarehousePo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 37220222203558
 * 2024-dsg-116
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseDao warehouseDao;
    private final WarehouseRegionDao warehouseRegionDao;
    private final RegionDao regionDao;
    private final ContractDao contractDao;

    /**
     * @author 37220222203558
     * 2024-dsg116-获取仓库列表
     */
    public List<Warehouse> getWarehouses(Long shopId, Integer page, Integer pageSize) {
        List<Warehouse> warehouseList = warehouseDao.findByShopId(shopId);
        warehouseList = getListByPage(warehouseList, page, pageSize);
        for (Warehouse warehouse:warehouseList){
            Region region = regionDao.findById(warehouse.getRegionId());
            warehouse.setRegionName(region.getName());
        }
        return warehouseList;
    }

    /**
     * @author 37220222203558
     * 2024-dsg116-新增仓库
     */
    public Warehouse createWarehouse(Warehouse warehouse, UserDto user) {
        Region region = regionDao.findById(warehouse.getRegionId());
        warehouse.setRegionName(region.getName());
        warehouse.setInvalid(Warehouse.VALID);
        return warehouseDao.insert(warehouse, user);
    }

    /**
     * @author 37220222203558
     * 2024-dsg116-修改仓库
     */
    public void changeWarehouse(Warehouse warehouse, UserDto user) {
        Warehouse checkWarehouse = warehouseDao.findById(warehouse.getShopId(), warehouse.getId());
        checkWarehouse.setName(warehouse.getName());
        checkWarehouse.setAddress(warehouse.getAddress());
        checkWarehouse.setSenderName(warehouse.getSenderName());
        checkWarehouse.setSenderMobile(warehouse.getSenderMobile());
        warehouseDao.save(checkWarehouse, user);
    }

    /**
     * @author 37220222203558
     * 2024-dsg116-取消仓库
     */
    public void deleteWarehouse(Long ShopId,Long id,UserDto user) {
        Warehouse warehouse = warehouseDao.findById(ShopId,id);
        warehouse.setInvalid(Warehouse.INVALID);
        List<Region> regionList = warehouseRegionDao.findByWarehouseId(id);
        for (Region region:regionList){
            warehouseRegionDao.delete(id, region.getId());
        }
        List<Contract> contractList = contractDao.findByWarehouseId(id);
        for(Contract contract:contractList){
            contract.setInvalid(Contract.INVALID);
            contractDao.save(contract, user);
        }
        warehouseDao.save(warehouse, user);
    }

    private List<Warehouse> getListByPage(List<Warehouse> warehouseList, Integer pageNumber, Integer pageSize) {
        int totalElements = warehouseList.size();

        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalElements);

        List<Warehouse> list = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            list.add(warehouseList.get(i));
        }
        return list;
    }
}
