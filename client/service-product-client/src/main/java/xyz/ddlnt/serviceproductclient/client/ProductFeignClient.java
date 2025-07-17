package xyz.ddlnt.serviceproductclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.ddlnt.commonutil.result.Result;
import xyz.ddlnt.model.dto.PageDTO;
import xyz.ddlnt.model.dto.ProductDTO;
import xyz.ddlnt.model.vo.ProductVO;

import java.util.List;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 10:35
 */
@FeignClient(name = "service-product", path = "/product")
public interface ProductFeignClient {
    /**
     * 添加产品
     * @param productDTO
     * @return
     */
    @PostMapping("add")
    Result<Boolean> addProduct(@RequestBody ProductDTO productDTO);

    /**
     * 更新产品
     * @param productDTO
     * @return
     */
    @PutMapping("update")
    Result<Boolean> updateProduct(@RequestBody ProductDTO productDTO);

    /**
     * 删除产品
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    Result<Boolean> deleteProduct(@RequestParam Integer id);

    /**
     * 查询产品列表
     * @param pageDTO
     * @return
     */
    @PostMapping("list")
    Result<List<ProductVO>> listProduct(@RequestBody PageDTO pageDTO);
}
