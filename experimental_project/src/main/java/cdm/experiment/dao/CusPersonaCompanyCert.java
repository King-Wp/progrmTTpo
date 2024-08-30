package cdm.experiment.dao;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业证书信息对象 cus_persona_company_cert
 * 
 * @author wucilong
 * @date 2022-09-05
 */

@Data
public class CusPersonaCompanyCert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 证书编号 */
    private String certNo;

    /** 证书类型 */
    private String certificateName;

    /** 发证时间 */
    private Date startDate;

    /** 截止日期 */
    private Date endDate;

    /** 产品名称及单元（主） */
    private String productUnit;

    /** 公司名称 */

    private String companyName;

    /** 企业ID */
    private String companyId;
}
