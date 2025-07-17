package xyz.ddlnt.serviceproduct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ddlnt.model.dto.PageDTO;
import xyz.ddlnt.model.dto.ProductDTO;
import xyz.ddlnt.model.entity.Product;
import xyz.ddlnt.model.vo.ProductVO;

import java.util.List;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 12:08
 */
public interface ProductService extends IService<Product> {
    /**
     * 添加产品
     * @param productDTO
     * @return
     */
    Boolean addProduct(ProductDTO productDTO);

    /**
     * 更新产品
     * @param productDTO
     * @return
     */
    Boolean updateProduct(ProductDTO productDTO);

    /**
     * 查询产品列表
     * @param pageDTO
     * @return
     */
    List<ProductVO> listProduct(PageDTO pageDTO);
}
