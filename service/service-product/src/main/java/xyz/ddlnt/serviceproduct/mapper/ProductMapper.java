package xyz.ddlnt.serviceproduct.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.ddlnt.model.entity.Product;
import xyz.ddlnt.model.vo.ProductVO;

import java.util.List;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 12:08
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT id, name, intro, create_time, update_time FROM `product`")
    List<ProductVO> listProduct(IPage<ProductVO> page);
}
