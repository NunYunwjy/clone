package cn.edu.xmu.oomall.jtexpress.controller;

import cn.edu.xmu.oomall.jtexpress.controller.dto.OrderDto;
import cn.edu.xmu.oomall.jtexpress.controller.dto.PersonInfoDto;
import cn.edu.xmu.oomall.jtexpress.exception.ReturnError;
import cn.edu.xmu.oomall.jtexpress.jtexpressApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(classes = jtexpressApplication.class)
@AutoConfigureMockMvc
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class JTExpressAddOrderV2ControllerTest {

    private final Logger logger = LoggerFactory.getLogger(JTExpressAddOrderV2ControllerTest.class);

    @Autowired
    private MockMvc mockMvc;
    private final String urlHeader = "/webopenplatformapi/api";
    private final String APIACCOUNT = "178337126125932605";
    private final String DIGEST = "JWI464gyo0JzuiAX9Zizdw==";
    private final String Add_Order_V2 = urlHeader + "/order/v2/addOrder";

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testAddOrderV2GivenNoExistOrderAndUsedBillCode() throws Exception {
        OrderDto orderDtoV2 = createOrderVoV2Demo();
        orderDtoV2.setTxLogisticId("Test3721944712401130");
        orderDtoV2.setBillCode("JT108912787043904043991");
        String bizContent = objectMapper.writeValueAsString(orderDtoV2);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenNoExistOrderAndUsedBillCode", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.INVALID_WAYBILL_NO.getCode())));
    }

    @Test
    void testAddOrderV2GivenNoExistOrderAndNoUsedBillCode() throws Exception {
        OrderDto orderDtoV2 = createOrderVoV2Demo();
        orderDtoV2.setTxLogisticId("Test3721944712401130");
        orderDtoV2.setBillCode("JT108912787043904043432");
        String bizContent = objectMapper.writeValueAsString(orderDtoV2);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenNoExistOrderAndNoUsedBillCode", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.SUCCESS.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.billCode", is("JT108912787043904043432")));
    }


    @Test
    void testAddOrderV2GivenExistOrder() throws Exception {
        OrderDto orderDtoV2 = createOrderVoV2Demo();
        String bizContent = objectMapper.writeValueAsString(orderDtoV2);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenExistOrder", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.SUCCESS.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.billCode", is("JT108912787043904043991")));
    }

    @Test
    void testAddOrderV2GivenErrorSendStartTime() throws Exception {
        String erroTime = "2022-07-04 25:00:00";
        String bizContent = String.format("{\"customerCode\":\"J0086474299\",\"digest\":\"qonqb4O1eNr6VCWS07Ieeg==\",\"network\":null,\"txlogisticId\":\"TEST20220704210006\",\"expressType\":\"EZ\",\"orderType\":\"1\",\"serviceType\":\"01\",\"deliveryType\":\"06\",\"payType\":\"CC_CASH\",\"sender\":{\"name\":\"小九\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"15546168286\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"青浦区\",\"town\":null,\"street\":null,\"address\":\"庆丰三路28号\"},\"receiver\":{\"name\":\"田丽\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"13766245825\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"嘉定区\",\"town\":null,\"street\":null,\"address\":\"站前西路永利酒店斜对面童装店\"},\"sendStartTime\":\"%s\",\"sendEndTime\":\"2022-07-04 18:00:00\",\"goodsType\":\"bm000006\",\"length\":0,\"width\":0,\"height\":0,\"weight\":\"0.02\",\"totalQuantity\":0,\"itemsValue\":null,\"priceCurrency\":null,\"offerFee\":null,\"remark\":null,\"items\":[{\"itemType\":null,\"itemName\":\"衣帽鞋服\",\"chineseName\":null,\"englishName\":null,\"number\":1,\"itemValue\":null,\"priceCurrency\":\"RMB\",\"desc\":null,\"itemUrl\":null}],\"customsInfo\":null,\"postSiteCode\":null,\"postSiteName\":null,\"postSiteAddress\":null,\"realName\":null}", erroTime);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenErrorSendStartTime", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.DOOR_SERVICE_TIME_INCOMPLETE.getCode())));
    }

    @Test
    void testAddOrderV2GivenErrorSendStartTimeAndSendEndTime() throws Exception {
        String errorStartTime = "2022-07-04 23:00:00";
        String errorEndTime = "2022-07-04 12:00:00";
        String bizContent = String.format("{\"customerCode\":\"J0086474299\",\"digest\":\"qonqb4O1eNr6VCWS07Ieeg==\",\"network\":null,\"txlogisticId\":\"TEST20220704210006\",\"expressType\":\"EZ\",\"orderType\":\"1\",\"serviceType\":\"01\",\"deliveryType\":\"06\",\"payType\":\"CC_CASH\",\"sender\":{\"name\":\"小九\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"15546168286\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"青浦区\",\"town\":null,\"street\":null,\"address\":\"庆丰三路28号\"},\"receiver\":{\"name\":\"田丽\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"13766245825\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"嘉定区\",\"town\":null,\"street\":null,\"address\":\"站前西路永利酒店斜对面童装店\"},\"sendStartTime\":\"%s\",\"sendEndTime\":\"%s\",\"goodsType\":\"bm000006\",\"length\":0,\"width\":0,\"height\":0,\"weight\":\"0.02\",\"totalQuantity\":0,\"itemsValue\":null,\"priceCurrency\":null,\"offerFee\":null,\"remark\":null,\"items\":[{\"itemType\":null,\"itemName\":\"衣帽鞋服\",\"chineseName\":null,\"englishName\":null,\"number\":1,\"itemValue\":null,\"priceCurrency\":\"RMB\",\"desc\":null,\"itemUrl\":null}],\"customsInfo\":null,\"postSiteCode\":null,\"postSiteName\":null,\"postSiteAddress\":null,\"realName\":null}", errorStartTime,errorEndTime);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenErrorSendStartTimeAndSendEndTime", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.DOOR_SERVICE_TIME_INCOMPLETE.getCode())));
    }

    @Test
    void testAddOrderV2GivenNullSendStartTimeAndSendEndTime() throws Exception {
        String bizContent = "{\"customerCode\":\"J0086474299\",\"digest\":\"qonqb4O1eNr6VCWS07Ieeg==\",\"network\":null,\"txlogisticId\":\"TEST20220704210006\",\"expressType\":\"EZ\",\"orderType\":\"1\",\"serviceType\":\"01\",\"deliveryType\":\"06\",\"payType\":\"CC_CASH\",\"sender\":{\"name\":\"小九\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"15546168286\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"青浦区\",\"town\":null,\"street\":null,\"address\":\"庆丰三路28号\"},\"receiver\":{\"name\":\"田丽\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"13766245825\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"嘉定区\",\"town\":null,\"street\":null,\"address\":\"站前西路永利酒店斜对面童装店\"},\"sendStartTime\":null,\"sendEndTime\":null,\"goodsType\":\"bm000006\",\"length\":0,\"width\":0,\"height\":0,\"weight\":\"0.02\",\"totalQuantity\":0,\"itemsValue\":null,\"priceCurrency\":null,\"offerFee\":null,\"remark\":null,\"items\":[{\"itemType\":null,\"itemName\":\"衣帽鞋服\",\"chineseName\":null,\"englishName\":null,\"number\":1,\"itemValue\":null,\"priceCurrency\":\"RMB\",\"desc\":null,\"itemUrl\":null}],\"customsInfo\":null,\"postSiteCode\":null,\"postSiteName\":null,\"postSiteAddress\":null,\"realName\":null}";
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenErrorSendStartTimeAndSendEndTime", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.SUCCESS.getCode())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.txlogisticId", is("TEST20220704210006")));
    }

    @Test
    void testAddOrderV2GivenErrorCustomerCode() throws Exception {
        OrderDto orderDtoV2 = createOrderVoV2Demo();
        String errorCustomerCode = "errorTest";
        orderDtoV2.setCustomerCode(errorCustomerCode);
        String bizContent = objectMapper.writeValueAsString(orderDtoV2);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenErrorCustomerCode", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.ILLEGAL_PARAMETER.getCode())));
    }

    @Test
    void testAddOrderV2GivenErrorBillCode() throws Exception {
        OrderDto orderDtoV2 = createOrderVoV2Demo();
        String errorBillCode = "errorTest";
        orderDtoV2.setBillCode(errorBillCode);
        String bizContent = objectMapper.writeValueAsString(orderDtoV2);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenErrorBillCode", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.INVALID_WAYBILL_NO.getCode())));
    }
    @Test
    void testAddOrderV2GivenIllegalParameter() throws Exception {
        String errorWeight = "errorTest";
        String bizContent = String.format("{\"customerCode\":\"J0086474299\",\"digest\":\"qonqb4O1eNr6VCWS07Ieeg==\",\"network\":null,\"txlogisticId\":\"TEST20220704210006\",\"expressType\":\"EZ\",\"orderType\":\"1\",\"serviceType\":\"01\",\"deliveryType\":\"06\",\"payType\":\"CC_CASH\",\"sender\":{\"name\":\"小九\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"15546168286\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"青浦区\",\"town\":null,\"street\":null,\"address\":\"庆丰三路28号\"},\"receiver\":{\"name\":\"田丽\",\"company\":null,\"postCode\":null,\"mailBox\":null,\"mobile\":\"13766245825\",\"phone\":\"\",\"countryCode\":\"CHN\",\"prov\":\"上海\",\"city\":\"上海市\",\"area\":\"嘉定区\",\"town\":null,\"street\":null,\"address\":\"站前西路永利酒店斜对面童装店\"},\"sendStartTime\":\"2022-07-04 14:00:00\",\"sendEndTime\":\"2022-07-04 18:00:00\",\"goodsType\":\"bm000006\",\"length\":0,\"width\":0,\"height\":0,\"weight\":\"%s\",\"totalQuantity\":0,\"itemsValue\":null,\"priceCurrency\":null,\"offerFee\":null,\"remark\":null,\"items\":[{\"itemType\":null,\"itemName\":\"衣帽鞋服\",\"chineseName\":null,\"englishName\":null,\"number\":1,\"itemValue\":null,\"priceCurrency\":\"RMB\",\"desc\":null,\"itemUrl\":null}],\"customsInfo\":null,\"postSiteCode\":null,\"postSiteName\":null,\"postSiteAddress\":null,\"realName\":null}", errorWeight);
        logger.debug("{}测试 bizContent：{}", "testAddOrderV2GivenIllegalParameter", bizContent);
        long timestamp = System.currentTimeMillis();
        this.mockMvc.perform(MockMvcRequestBuilders.post(Add_Order_V2)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .header("apiAccount", APIACCOUNT)
                        .header("digest", DIGEST)
                        .header("timestamp", timestamp)
                        .param("bizContent", bizContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(ReturnError.INVALID_WEIGHT.getCode())));
    }

    private JTExpressAddOrderV2ControllerTest() {
        // 配置日期时间序列化格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 注册JavaTimeModule，以支持Java 8中的新日期时间类（如LocalDateTime）
        objectMapper.registerModule(new JavaTimeModule());
    }

    private OrderDto createOrderVoV2Demo() {
        OrderDto orderDtoV2 = new OrderDto();

        orderDtoV2.setCustomerCode("J0086474299");
        orderDtoV2.setDigest("qonqb4O1eNr6VCWS07Ieeg==");
        orderDtoV2.setNetwork(null);
        orderDtoV2.setTxLogisticId("TEST0000000000");
        orderDtoV2.setExpressType("EZ");
        orderDtoV2.setOrderType("1");
        orderDtoV2.setServiceType("01");
        orderDtoV2.setDeliveryType("06");
        orderDtoV2.setPayType("CC_CASH");

        PersonInfoDto sender = new PersonInfoDto();
        sender.setName("小九");
        sender.setCompany(null);
        sender.setPostCode(null);
        sender.setMailBox(null);
        sender.setMobile("15546168286");
        sender.setPhone("");
        sender.setCountryCode("CHN");
        sender.setProv("上海");
        sender.setCity("上海市");
        sender.setArea("青浦区");
        sender.setTown(null);
        sender.setStreet(null);
        sender.setAddress("庆丰三路28号");
        orderDtoV2.setSender(sender);

        PersonInfoDto receiver = new PersonInfoDto();
        receiver.setName("田丽");
        receiver.setCompany(null);
        receiver.setPostCode(null);
        receiver.setMailBox(null);
        receiver.setMobile("13766245825");
        receiver.setPhone("");
        receiver.setCountryCode("CHN");
        receiver.setProv("上海");
        receiver.setCity("上海市");
        receiver.setArea("嘉定区");
        receiver.setTown(null);
        receiver.setStreet(null);
        receiver.setAddress("站前西路永利酒店斜对面童装店");
        orderDtoV2.setReceiver(receiver);

        orderDtoV2.setSendStartTime("2022-07-04 22:00:00");
        orderDtoV2.setSendEndTime("2022-07-04 23:00:00");
        orderDtoV2.setGoodsType("bm000006");
        orderDtoV2.setLength(0);
        orderDtoV2.setWidth(0);
        orderDtoV2.setHeight(0);
        orderDtoV2.setWeight("0.02");
        orderDtoV2.setTotalQuantity(0);
        orderDtoV2.setItemsValue(null);
        orderDtoV2.setPriceCurrency(null);
        orderDtoV2.setOfferFee(null);
        orderDtoV2.setRemark(null);

        orderDtoV2.setPostSiteCode(null);
        orderDtoV2.setPostSiteName(null);
        orderDtoV2.setPostSiteAddress(null);
        orderDtoV2.setRealName(null);
        orderDtoV2.setBillCode("JT108912787043904043991");

        return orderDtoV2;

    }

}
