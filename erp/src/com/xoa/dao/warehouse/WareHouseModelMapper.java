package com.xoa.dao.warehouse;

import com.xoa.model.warehouse.WareHouseModel;
import java.util.List;

public interface WareHouseModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    int countByExample(WareHouseModel WareHouseModel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    int insert(WareHouseModel WareHouseModel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    List<WareHouseModel> selectByExample(WareHouseModel WareHouseModel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    WareHouseModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(WareHouseModel WareHouseModel);
}