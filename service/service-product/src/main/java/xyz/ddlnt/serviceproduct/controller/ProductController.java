package xyz.ddlnt.serviceproduct.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.ddlnt.commonutil.result.Result;
import xyz.ddlnt.model.dto.PageDTO;
import xyz.ddlnt.model.dto.ProductDTO;
import xyz.ddlnt.model.vo.ProductVO;
import xyz.ddlnt.serviceproduct.service.ProductService;

import java.util.List;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 12:03
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 添加产品
     * @param productDTO
     * @return
     */
    @PostMapping("add")
    public Result<Boolean> addProduct(@RequestBody ProductDTO productDTO) {
        Boolean isAdd = productService.addProduct(productDTO);
        log.info("添加产品结果：{}", isAdd);
        return Result.success(isAdd);
    }

    /**
     * 更新产品
     * @param productDTO
     * @return
     */
    @PutMapping("update")
    public Result<Boolean> updateProduct(@RequestBody ProductDTO productDTO) {
        Boolean isUpdate = productService.updateProduct(productDTO);
        log.info("更新产品结果：{}", isUpdate);
        return Result.success(isUpdate);
    }

    /**
     * 删除产品
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    public Result<Boolean> deleteProduct(@RequestParam Integer id) {
        Boolean isDelete = productService.removeById(id);
        log.info("删除产品结果：{}", isDelete);
        return Result.success(isDelete);
    }

    /**
     * 查询产品列表
     * @param pageDTO
     * @return
     */
    @PostMapping("list")
    public Result<List<ProductVO>> listProduct(@RequestBody PageDTO pageDTO) {
        List<ProductVO> list = productService.listProduct(pageDTO);
        log.info("查询产品列表结果：{}", list);
        return Result.success(list);
    }
}
