package xyz.ddlnt.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 11:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String intro;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
