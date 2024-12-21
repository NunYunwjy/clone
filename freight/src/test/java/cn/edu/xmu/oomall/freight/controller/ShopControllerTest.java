package cn.edu.xmu.oomall.freight.controller;

import cn.edu.xmu.javaee.core.mapper.RedisUtil;
import cn.edu.xmu.javaee.core.model.InternalReturnObject;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.util.JwtHelper;
import cn.edu.xmu.oomall.freight.FreightApplication;
import cn.edu.xmu.oomall.freight.dao.bo.Contract;
import cn.edu.xmu.oomall.freight.mapper.jpa.ContractPoMapper;
import cn.edu.xmu.oomall.freight.mapper.jpa.WarehousePoMapper;
import cn.edu.xmu.oomall.freight.mapper.po.ContractPo;
import cn.edu.xmu.oomall.freight.mapper.po.RegionPo;
import cn.edu.xmu.oomall.freight.mapper.po.WarehousePo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.is;
import cn.edu.xmu.oomall.freight.mapper.openfeign.RegionMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = FreightApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public RegionMapper regionMapper;
    private static String adminToken;

    @SpyBean
    ContractPoMapper contractPoMapper;

    @SpyBean
    WarehousePoMapper warehousePoMapper;

    @MockBean
    private RedisUtil redisUtil;

    @BeforeAll
    public static void setup() {
        JwtHelper jwtHelper = new JwtHelper();
        adminToken = jwtHelper.createToken(1L, "13088admin", 0L, 1, 3600);
    }

//    @Test
//    public void testAddShopLogistics() throws Exception {
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        String json = "{\"logisticsId\":\"2\", \"secret\":\"secret2\", \"priority\":\"6\"}";
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/shops/{shopId}/shoplogistics", 2L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8")
//                        .content(json))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.CREATED.getErrNo()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.logistics.id").value(2))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.logistics.name").value("中通快递"));
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testAddShopLogisticsGivenWrongLogisticsId() throws Exception {
//        // 新增logistics不存在
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        String json = "{\"logisticsId\":\"99\", \"secret\":\"secret2\", \"priority\":\"6\"}";
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/shops/{shopId}/shoplogistics", 2L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8")
//                        .content(json))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_NOTEXIST.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testAddShopLogisticsGivenExistLogisticsId() throws Exception {
//        // 重复新增logistics
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        String json = "{\"logisticsId\":\"1\", \"secret\":\"secret2\", \"priority\":\"6\"}";
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/shops/{shopId}/shoplogistics", 2L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8")
//                        .content(json))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.FREIGHT_LOGISTIC_EXIST.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testGetShopLogistics() throws Exception {
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/shops/{shopId}/shoplogistics", 2L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].logistics.id",is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].logistics.id",is(3)));
//
//        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testGetShopLogisticsGivenEmptyShop() throws Exception {
//        // 无任何物流信息的商铺
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/shops/{shopId}/shoplogistics", 99L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list").isEmpty());
//
//        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testChangeLogisticsContract() throws Exception {
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        String json = "{\"secret\":\"secret2-modify\", \"priority\":\"7\"}";
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/shoplogistics/{id}", 2L, 6L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8")
//                        .content(json))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//
//    @Test
//    public void testChangeContract() throws Exception {
//        // 无权更改
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        String json = "{\"secret\":\"secret2-modify\", \"priority\":\"7\"}";
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/shoplogistics/{id}", 1L, 6L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8")
//                        .content(json))
//                .andExpect(MockMvcResultMatchers.status().isForbidden())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testSuspendShopLogistics() throws Exception {
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/shoplogistics/{id}/suspend", 2L, 6L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testSuspendShopLogisticsGivenUnauthorizedShop() throws Exception {
//        // 无权更改
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/shoplogistics/{id}/suspend", 9L, 6L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.status().isForbidden())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }
//
//    @Test
//    public void testResumeShopLogistics() throws Exception {
//        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
//        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
//        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/shoplogistics/{id}/resume", 2L, 6L)
//                        .header("authorization", adminToken)
//                        .contentType("application/json;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));
//
//        // display:
////        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
////        resultActions.andDo(print());
//    }

    /**
     * @author 37220222203558
     * 2024-dsg-116
     */
    @Test
    public void TestGetWarehouses() throws Exception {
        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        String json = "{\"page\":\"1\", \"pageSize\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/shops/{id}/warehouses", 1L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestCreateWarehouse() throws Exception {
        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        String json = "{\"name\":\"haha\", \"address\":\"haha\", \"regionId\":\"123\", \"senderName\":\"haha\", \"senderMobile\":\"12345678911\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/shops/{id}/warehouses", 1L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /**
     * @author 37220222203558
     * 2024-dsg116-修改仓库
     */
    @Test
    public void testChangeWareHouse() throws Exception {
        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        String json = "{\"name\":\"haha\", \"address\":\"haha\", \"regionId\":\"123\", \"senderMobile\":\"12345678911\", \"senderName\":\"haha\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/warehouses/{id}", 1L, 1L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * @author 37220222203558
     * 2024-dsg116-删除仓库
     */
    @Test
    public void testDeleteWareHouse() throws Exception {
        Mockito.when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/shops/{shopId}/warehouses/{id}", 1L, 1L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddShopLogistics() throws Exception {

        ContractPo ret = new ContractPo();
        ret.setId(2L);
        doReturn(ret).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        String json = "{\"account\":\"user\", \"secret\":\"secret2\", \"priority\":\"6\",\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\", \"quota\":\"8\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/shops/{shopId}/warehouses/{id}/logistics/{lid}/contracts", 2L,1L,2L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.CREATED.getErrNo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.logistics.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.secret").value("secret2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.quota").value("8"));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }

    @Test
    public void testGetShopLogistics() throws Exception {
        List<ContractPo> mockContracts = new ArrayList<>();
        ContractPo po1 = new ContractPo();
        po1.setId(1L);
        po1.setLogisticsId(1L);
        po1.setShopId(2L);
        po1.setPriority(1);
        mockContracts.add(po1);

        ContractPo po2 = new ContractPo();
        po2.setId(2L);
        po2.setLogisticsId(3L);
        po2.setShopId(2L);
        po2.setPriority(2);
        mockContracts.add(po2);
        doReturn(mockContracts).when(contractPoMapper).findAllByShopIdAndWarehouseIdOrderByPriorityAsc(eq(2L),eq(1L),Mockito.any(Pageable.class));
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        String json = "{\"page\":\"3\", \"pageSize\":\"3\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/shops/{shopId}/warehouses/{id}/contracts", 2L,1L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[0].logistics.id",is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.list[1].logistics.id",is(3)));

        // display:
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(print());
    }



    @Test
    public void testChangeLogisticsContract() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(1L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        doReturn(mockContractPo).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }

    @Test
    public void testChangeLogisticsContractWhenLogisticisNull() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setWarehouseId(1L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        doReturn(mockContractPo).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        doReturn(Optional.of(new WarehousePo())).when(warehousePoMapper).findById((1L));
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(2));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }
    //需要等仓库模块完成后测试
   /* @Test
    public void testChangeLogisticsContractWhenWarehouseisNull() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(2L);
        mockContractPo.setWarehouseId(99L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        doReturn(mockContractPo).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(2));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }*/
    @Test
    public void testChangeLogisticsContractRedis() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        Contract mockContract = new Contract();
        mockContract.setId(6L);
        mockContract.setShopId(2L); // 模拟越权的 shopId
        mockContract.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContract.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContract.setQuota(10);
        mockContract.setWarehouseId(1L);
        mockContract.setLogisticsId(2L);
        when(redisUtil.get(Mockito.anyString())).thenReturn((Serializable)mockContract);
        doReturn(new ContractPo()).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }


    @Test
    public void testChangeLogisticsContractInvalidShopId() throws Exception {
        // Mock Redis 行为
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        when(redisUtil.get(Mockito.anyString())).thenReturn(null);

        // 构造请求体
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";

        ResultActions resultActions = this.mockMvc.perform(
                        MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 2L, -1)
                                .header("authorization", adminToken)
                                .contentType("application/json;charset=UTF-8")
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError()) // 改为期望的状态码
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(2)); // 返回的错误码

        // 打印结果
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(print());
    }
    @Test
    public void testChangeLogisticsContractRedisNo() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        Contract mockContract = new Contract();
        mockContract.setId(6L);
        mockContract.setShopId(2L); // 模拟越权的 shopId
        mockContract.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContract.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContract.setQuota(10);
        mockContract.setLogisticsId(2L);
        mockContract.setWarehouseId(1L);
        when(redisUtil.get(Mockito.anyString())).thenReturn((Serializable)mockContract);
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 1L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }
    @Test
    public void testChangeLogisticsContractRetNull() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        doReturn(Optional.empty()).when(contractPoMapper).findById((6L));
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_NOTEXIST.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }

    @Test
    public void testDeleteLogisticsContract() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(2L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        doNothing().when(contractPoMapper).deleteById(Mockito.anyLong());
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/shops/{shopId}/contracts/{id}", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }
    @Test
    public void testChangeContract() throws Exception {
        // 无权更改
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(2L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        String json = "{\"beginTime\":\"2024-12-05T15:30:00\", \"endTime\":\"2026-12-05T15:30:00\",\"quota\":\"10\"}";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}", 1L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }

    @Test
    public void testSuspendShopLogistics() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(2L);
        mockContractPo.setWarehouseId(1L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        doReturn(mockContractPo).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}/suspend", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }

    @Test
    public void testSuspendShopLogisticsGivenUnauthorizedShop() throws Exception {
        // 无权更改
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(2L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}/suspend", 9L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.RESOURCE_ID_OUTSCOPE.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }

    @Test
    public void testResumeShopLogistics() throws Exception {
        when(redisUtil.hasKey(Mockito.anyString())).thenReturn(false);
        when(redisUtil.set(Mockito.anyString(), Mockito.any(), Mockito.anyLong())).thenReturn(true);
        ContractPo mockContractPo = new ContractPo();
        mockContractPo.setId(6L);
        mockContractPo.setShopId(2L);
        mockContractPo.setBeginTime(LocalDateTime.of(2024, 12, 5, 15, 30));
        mockContractPo.setEndTime(LocalDateTime.of(2026, 12, 5, 15, 30));
        mockContractPo.setQuota(10);
        mockContractPo.setLogisticsId(2L);
        doReturn(Optional.of(mockContractPo)).when(contractPoMapper).findById((6L));
        doReturn(mockContractPo).when(contractPoMapper).save(Mockito.any(ContractPo.class));
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/shops/{shopId}/contracts/{id}/resume", 2L, 6L)
                        .header("authorization", adminToken)
                        .contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errno").value(ReturnNo.OK.getErrNo()));

        // display:
//        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
//        resultActions.andDo(print());
    }
}
