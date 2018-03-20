package com.xoa.dao.inventory;

import com.xoa.model.inventory.InventoryModel;
import java.util.List;


public interface InventoryModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    int countByExample(InventoryModel inventoryModel);

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
    int insert(InventoryModel inventoryModel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    List<InventoryModel> selectByExample(InventoryModel inventoryModel);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    InventoryModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_unit
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(InventoryModel inventoryModel);

}