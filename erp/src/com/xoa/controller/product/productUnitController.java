package com.xoa.controller.product;

import com.xoa.model.product.ProductUnit;
import com.xoa.service.product.ProductUnitService;
import com.xoa.util.ToJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhuzy on 2017/10/28.
 */
@Controller
@RequestMapping("/product")
public class productUnitController {

    @Resource
    private  ProductUnitService productUnitService;

    @ResponseBody
    @RequestMapping(value ="/unit/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductUnit> add(ProductUnit pu, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            Boolean res = productUnitService.insert(pu);
            if(res){
                json.setMsg("ok");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/unit/getOneById", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductUnit> selectByPrimaryKey(Integer pid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            System.out.println(pid);
            ProductUnit res = productUnitService.selectByPrimaryKey(pid);
            json.setObject(res);
            json.setMsg("OK ");
            json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/unit/getAll", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductUnit> selectAll(Integer pagesize,Integer page,ProductUnit pu, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            pu.setPagesize(pagesize);
            pu.setPage(page);
            List<ProductUnit> res = productUnitService.selectAll(pu);
            Integer count = productUnitService.countByExample(pu);
            json.setCount(count);
            json.setObject(res);
            json.setMsg("OK ");
            json.setFlag(0);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/unit/deleteById", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductUnit> deleteById(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            if(productUnitService.deleteByPrimaryKey(id)){
                json.setMsg("OK ");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @ResponseBody
    @RequestMapping(value ="/unit/updateById", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductUnit> updateById(ProductUnit pu, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductUnit> json = new ToJson<ProductUnit>(1, null);
        try {
            if(productUnitService.updateByPrimaryKey(pu)){
                json.setMsg("OK ");
                json.setFlag(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setFlag(1);
        }
        return json;
    }
    @RequestMapping("/unit/index")
    public String Pageindex( HttpServletResponse response) {

        return "app/productunit/index";
    }
}
