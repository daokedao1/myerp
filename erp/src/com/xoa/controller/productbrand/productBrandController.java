package com.xoa.controller.productbrand;


import com.xoa.model.product.ProductBrandModel;
import com.xoa.service.product.ProductBrandService;
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
@RequestMapping("/product/brand")
public class productBrandController {

    @Resource
    private ProductBrandService productBrandService;

    @ResponseBody
    @RequestMapping(value ="/add", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductBrandModel> add(ProductBrandModel productbrandModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductBrandModel> json = new ToJson<ProductBrandModel>(1, null);
        try {
            Boolean res = productBrandService.insert(productbrandModel);
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
    @RequestMapping(value ="/getOneById", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductBrandModel> selectByPrimaryKey(Integer pid, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductBrandModel> json = new ToJson<ProductBrandModel>(1, null);
        try {
            System.out.println(pid);
            ProductBrandModel res = productBrandService.selectByPrimaryKey(pid);
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
    @RequestMapping(value ="/getAll", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductBrandModel> selectAll(Integer pagesize,Integer page,ProductBrandModel productBrandModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductBrandModel> json = new ToJson<ProductBrandModel>(1, null);
        try {
            productBrandModel.setPagesize(pagesize);
            productBrandModel.setPage(page);
            List<ProductBrandModel> res = productBrandService.selectAll(productBrandModel);
            Integer count = productBrandService.countByExample(productBrandModel);
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
    @RequestMapping(value ="/deleteById", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductBrandModel> deleteById(Integer id, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductBrandModel> json = new ToJson<ProductBrandModel>(1, null);
        try {
            if(productBrandService.deleteByPrimaryKey(id)){
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
    @RequestMapping(value ="/updateById", produces = {"application/json;charset=UTF-8"})
    public ToJson<ProductBrandModel> updateById(ProductBrandModel productBrandModel, HttpServletRequest request, HttpServletResponse response) {
        ToJson<ProductBrandModel> json = new ToJson<ProductBrandModel>(1, null);
        try {
            if(productBrandService.updateByPrimaryKey(productBrandModel)){
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
    @RequestMapping("/index")
    public String Pageindex( HttpServletResponse response) {

        return "app/productbrand/index";
    }
}
