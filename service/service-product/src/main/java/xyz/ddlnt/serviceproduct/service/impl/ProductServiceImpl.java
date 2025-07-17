package xyz.ddlnt.serviceproduct.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ddlnt.commonutil.enumeration.ResultCode;
import xyz.ddlnt.commonutil.exception.GlobalException;
import xyz.ddlnt.model.dto.PageDTO;
import xyz.ddlnt.model.dto.ProductDTO;
import xyz.ddlnt.model.entity.Product;
import xyz.ddlnt.model.vo.ProductVO;
import xyz.ddlnt.serviceproduct.mapper.ProductMapper;
import xyz.ddlnt.serviceproduct.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 12:08
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Boolean addProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .intro(productDTO.getIntro())
                .build();
        int insert = productMapper.insert(product);
        if (insert == 0) {
            log.error("添加产品失败");
            return false;
        }

        return true;
    }

    @Override
    public Boolean updateProduct(ProductDTO productDTO) {
        if (productDTO.getId() == null || productDTO.getName() == null) {
            throw new GlobalException(ResultCode.PARAM_ERROR);
        }
        LambdaUpdateWrapper<Product> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Product::getId, productDTO.getId())
                .set(Product::getName, productDTO.getName())
                .set(Product::getIntro, productDTO.getIntro())
                .set(Product::getUpdateTime, LocalDateTime.now());
        int update = productMapper.update(updateWrapper);
        if (update == 0) {
            log.error("修改产品失败");
            return false;
        }
        return true;
    }

    @Override
    public List<ProductVO> listProduct(PageDTO pageDTO) {
        IPage<ProductVO> page = new Page<>(pageDTO.getPageNum(), pageDTO.getPageSize());
        return productMapper.listProduct(page);
    }
}
