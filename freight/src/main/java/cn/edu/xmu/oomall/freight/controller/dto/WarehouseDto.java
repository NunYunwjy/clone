package cn.edu.xmu.oomall.freight.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 37220222203558
 * 2024-dsg116
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WarehouseDto {
    private String name;

    private String address;

    private Long regionId;

    private String senderName;

    private String senderMobile;
}
