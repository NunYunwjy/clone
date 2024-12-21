package cn.edu.xmu.oomall.freight.service;

import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.oomall.freight.FreightApplication;
import cn.edu.xmu.oomall.freight.dao.bo.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author 37220222203558
 * 2024-dsg116
 */
@SpringBootTest(classes = FreightApplication.class)
@AutoConfigureMockMvc
@Transactional
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void testGetWarehousesRedis() throws Exception {
        warehouseService.getWarehouses(1L, 1, 1);
        warehouseService.getWarehouses(1L, 1, 1);
    }

    @Test
    public void testChangeWarehouseWhenNotExist() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("13088admin");
        Warehouse warehouse = new Warehouse();
        warehouse.setShopId(1L);
        warehouse.setId(30L);
        warehouse.setRegionId(1L);
        warehouse.setName("haha");
        warehouse.setAddress("haha");
        warehouse.setSenderName("haha");
        warehouse.setSenderMobile("12345678911");
        assertThrows(RuntimeException.class, ()->warehouseService.changeWarehouse(warehouse, user));
    }

    @Test
    public void testChangeWarehouseWhenOutScope() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("13088admin");
        Warehouse warehouse = new Warehouse();
        warehouse.setShopId(1L);
        warehouse.setId(2L);
        warehouse.setRegionId(1L);
        warehouse.setName("haha");
        warehouse.setAddress("haha");
        warehouse.setSenderName("haha");
        warehouse.setSenderMobile("12345678911");
        assertThrows(RuntimeException.class, ()->warehouseService.changeWarehouse(warehouse, user));
    }

    @Test
    public void testDeleteWarehouseRedis() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("13088admin");
        warehouseService.getWarehouses(1L, 1, 1);
        warehouseService.deleteWarehouse(1L, 1L, user);
    }

    @Test
    public void testDeleteWarehouseWhenContractNotExist() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("13088admin");
        assertThrows(RuntimeException.class, ()->warehouseService.deleteWarehouse(1L, 25L, user));
    }
}
