package xyz.ddlnt.serviceuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ddlnt.commonutil.constant.RoleConstants;
import xyz.ddlnt.commonutil.result.Result;
import xyz.ddlnt.model.dto.PageDTO;
import xyz.ddlnt.model.dto.ProductDTO;
import xyz.ddlnt.model.vo.ProductVO;
import xyz.ddlnt.serviceproductclient.client.ProductFeignClient;
import xyz.ddlnt.serviceuser.service.impl.StpInterfaceImpl;

import java.util.List;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 10:32
 */
@Slf4j
@Tag(name = "产品接口测试")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductFeignClient productFeignClient;
    @Operation(summary = "添加产品")
    @PostMapping("add")
    public Result<Boolean> addProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        StpInterfaceImpl.checkRoleOr(request, RoleConstants.EDITOR, RoleConstants.PRODUCT_ADMIN);
        return productFeignClient.addProduct(productDTO);
    }

    @Operation(summary = "更新产品")
    @PutMapping("update")
    public Result<Boolean> updateProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        StpInterfaceImpl.checkRoleOr(request, RoleConstants.EDITOR, RoleConstants.PRODUCT_ADMIN);
        return productFeignClient.updateProduct(productDTO);
    }

    @Operation(summary = "删除产品")
    @DeleteMapping("delete/{id}")
    public Result<Boolean> deleteProduct(@PathVariable Integer id, HttpServletRequest request) {
        StpInterfaceImpl.checkRoleOr(request, RoleConstants.EDITOR, RoleConstants.PRODUCT_ADMIN);
        return productFeignClient.deleteProduct(id);
    }

    @Operation(summary = "查询产品")
    @PostMapping("query")
    public Result<List<ProductVO>> queryProduct(@RequestBody PageDTO pageDTO, HttpServletRequest request) {
        StpInterfaceImpl.checkRoleOr(request, RoleConstants.USER, RoleConstants.EDITOR, RoleConstants.PRODUCT_ADMIN);
        return productFeignClient.listProduct(pageDTO);
    }
}
