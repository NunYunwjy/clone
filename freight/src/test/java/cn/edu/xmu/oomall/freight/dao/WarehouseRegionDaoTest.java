package cn.edu.xmu.oomall.freight.dao;

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
public class WarehouseRegionDaoTest {

    @Autowired
    private WarehouseRegionDao warehouseRegionDao;
    private WarehouseDao warehouseDao;

    @Test
    public void testDeleteWarehouseRedis() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("13088admin");
        warehouseRegionDao.findByWarehouseId(1L);
        warehouseRegionDao.findByWarehouseId(1L);
    }
}
