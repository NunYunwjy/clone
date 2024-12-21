package cn.edu.xmu.oomall.payment.mapper.generator.po;

import cn.edu.xmu.javaee.core.aop.CopyFrom;
import cn.edu.xmu.oomall.payment.dao.bo.Ledger;

import java.time.LocalDateTime;


public class LedgerPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.out_no
     *
     * @mbg.generated
     */
    private String outNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.trans_no
     *
     * @mbg.generated
     */
    private String transNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.amount
     *
     * @mbg.generated
     */
    private Long amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.account_id
     *
     * @mbg.generated
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.check_time
     *
     * @mbg.generated
     */
    private LocalDateTime checkTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.creator_id
     *
     * @mbg.generated
     */
    private Long creatorId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.creator_name
     *
     * @mbg.generated
     */
    private String creatorName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.modifier_id
     *
     * @mbg.generated
     */
    private Long modifierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.modifier_name
     *
     * @mbg.generated
     */
    private String modifierName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.gmt_create
     *
     * @mbg.generated
     */
    private LocalDateTime gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column payment_ledger.gmt_modified
     *
     * @mbg.generated
     */
    private LocalDateTime gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.id
     *
     * @return the value of payment_ledger.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.id
     *
     * @param id the value for payment_ledger.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.out_no
     *
     * @return the value of payment_ledger.out_no
     *
     * @mbg.generated
     */
    public String getOutNo() {
        return outNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.out_no
     *
     * @param outNo the value for payment_ledger.out_no
     *
     * @mbg.generated
     */
    public void setOutNo(String outNo) {
        this.outNo = outNo == null ? null : outNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.trans_no
     *
     * @return the value of payment_ledger.trans_no
     *
     * @mbg.generated
     */
    public String getTransNo() {
        return transNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.trans_no
     *
     * @param transNo the value for payment_ledger.trans_no
     *
     * @mbg.generated
     */
    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.amount
     *
     * @return the value of payment_ledger.amount
     *
     * @mbg.generated
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.amount
     *
     * @param amount the value for payment_ledger.amount
     *
     * @mbg.generated
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.account_id
     *
     * @return the value of payment_ledger.account_id
     *
     * @mbg.generated
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.account_id
     *
     * @param accountId the value for payment_ledger.account_id
     *
     * @mbg.generated
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.check_time
     *
     * @return the value of payment_ledger.check_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.check_time
     *
     * @param checkTime the value for payment_ledger.check_time
     *
     * @mbg.generated
     */
    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.creator_id
     *
     * @return the value of payment_ledger.creator_id
     *
     * @mbg.generated
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.creator_id
     *
     * @param creatorId the value for payment_ledger.creator_id
     *
     * @mbg.generated
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.creator_name
     *
     * @return the value of payment_ledger.creator_name
     *
     * @mbg.generated
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.creator_name
     *
     * @param creatorName the value for payment_ledger.creator_name
     *
     * @mbg.generated
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.modifier_id
     *
     * @return the value of payment_ledger.modifier_id
     *
     * @mbg.generated
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.modifier_id
     *
     * @param modifierId the value for payment_ledger.modifier_id
     *
     * @mbg.generated
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.modifier_name
     *
     * @return the value of payment_ledger.modifier_name
     *
     * @mbg.generated
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.modifier_name
     *
     * @param modifierName the value for payment_ledger.modifier_name
     *
     * @mbg.generated
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.gmt_create
     *
     * @return the value of payment_ledger.gmt_create
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.gmt_create
     *
     * @param gmtCreate the value for payment_ledger.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column payment_ledger.gmt_modified
     *
     * @return the value of payment_ledger.gmt_modified
     *
     * @mbg.generated
     */
    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column payment_ledger.gmt_modified
     *
     * @param gmtModified the value for payment_ledger.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}