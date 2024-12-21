package cn.edu.xmu.oomall.wechatpay.mapper.generator.po;

import cn.edu.xmu.javaee.core.aop.CopyFrom;
import cn.edu.xmu.oomall.wechatpay.dao.bo.DivRefundTrans;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
@CopyFrom({DivRefundTrans.class})
public class DivRefundTransPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.sub_mchid
     *
     * @mbg.generated
     */
    private String subMchid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.order_id
     *
     * @mbg.generated
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.out_order_no
     *
     * @mbg.generated
     */
    private String outOrderNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.out_return_no
     *
     * @mbg.generated
     */
    private String outReturnNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.return_mchid
     *
     * @mbg.generated
     */
    private String returnMchid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.amount
     *
     * @mbg.generated
     */
    private Integer amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.return_id
     *
     * @mbg.generated
     */
    private String returnId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.result
     *
     * @mbg.generated
     */
    private String result;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wechatpay_div_refund_trans.finish_time
     *
     * @mbg.generated
     */
    private LocalDateTime finishTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.id
     *
     * @return the value of wechatpay_div_refund_trans.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.id
     *
     * @param id the value for wechatpay_div_refund_trans.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.sub_mchid
     *
     * @return the value of wechatpay_div_refund_trans.sub_mchid
     *
     * @mbg.generated
     */
    public String getSubMchid() {
        return subMchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.sub_mchid
     *
     * @param subMchid the value for wechatpay_div_refund_trans.sub_mchid
     *
     * @mbg.generated
     */
    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid == null ? null : subMchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.order_id
     *
     * @return the value of wechatpay_div_refund_trans.order_id
     *
     * @mbg.generated
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.order_id
     *
     * @param orderId the value for wechatpay_div_refund_trans.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.out_order_no
     *
     * @return the value of wechatpay_div_refund_trans.out_order_no
     *
     * @mbg.generated
     */
    public String getOutOrderNo() {
        return outOrderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.out_order_no
     *
     * @param outOrderNo the value for wechatpay_div_refund_trans.out_order_no
     *
     * @mbg.generated
     */
    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo == null ? null : outOrderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.out_return_no
     *
     * @return the value of wechatpay_div_refund_trans.out_return_no
     *
     * @mbg.generated
     */
    public String getOutReturnNo() {
        return outReturnNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.out_return_no
     *
     * @param outReturnNo the value for wechatpay_div_refund_trans.out_return_no
     *
     * @mbg.generated
     */
    public void setOutReturnNo(String outReturnNo) {
        this.outReturnNo = outReturnNo == null ? null : outReturnNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.return_mchid
     *
     * @return the value of wechatpay_div_refund_trans.return_mchid
     *
     * @mbg.generated
     */
    public String getReturnMchid() {
        return returnMchid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.return_mchid
     *
     * @param returnMchid the value for wechatpay_div_refund_trans.return_mchid
     *
     * @mbg.generated
     */
    public void setReturnMchid(String returnMchid) {
        this.returnMchid = returnMchid == null ? null : returnMchid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.amount
     *
     * @return the value of wechatpay_div_refund_trans.amount
     *
     * @mbg.generated
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.amount
     *
     * @param amount the value for wechatpay_div_refund_trans.amount
     *
     * @mbg.generated
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.description
     *
     * @return the value of wechatpay_div_refund_trans.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.description
     *
     * @param description the value for wechatpay_div_refund_trans.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.return_id
     *
     * @return the value of wechatpay_div_refund_trans.return_id
     *
     * @mbg.generated
     */
    public String getReturnId() {
        return returnId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.return_id
     *
     * @param returnId the value for wechatpay_div_refund_trans.return_id
     *
     * @mbg.generated
     */
    public void setReturnId(String returnId) {
        this.returnId = returnId == null ? null : returnId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.result
     *
     * @return the value of wechatpay_div_refund_trans.result
     *
     * @mbg.generated
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.result
     *
     * @param result the value for wechatpay_div_refund_trans.result
     *
     * @mbg.generated
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.create_time
     *
     * @return the value of wechatpay_div_refund_trans.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.create_time
     *
     * @param createTime the value for wechatpay_div_refund_trans.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wechatpay_div_refund_trans.finish_time
     *
     * @return the value of wechatpay_div_refund_trans.finish_time
     *
     * @mbg.generated
     */
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wechatpay_div_refund_trans.finish_time
     *
     * @param finishTime the value for wechatpay_div_refund_trans.finish_time
     *
     * @mbg.generated
     */
    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }
}