package cn.starry.freenessy.base.resp;

import lombok.Data;

@Data
public class CommonPage {

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
}
