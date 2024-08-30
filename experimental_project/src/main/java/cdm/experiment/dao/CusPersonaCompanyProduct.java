package cdm.experiment.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * 企业产品信息对象 cus_persona_company_product
 * 
 * @author wucilong
 * @date 2022-09-05
 */

@Data
public class CusPersonaCompanyProduct implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 领域 */
    private String classes;

    /** 产品简称 */
    private String filterName;

    /** 图标 */
    private String icon;

    /** 产品分类 */
    private String type;

    /** 描述 */
    private String brief;

    /** 公司名称 */
    private String companyName;

    /** 公司ID */
    private String companyId;

    /** 产品名称*/
    private String productName;
}