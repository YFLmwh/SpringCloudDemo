package xyz.ddlnt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/13 12:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private Integer id;
    private String name;
    private String intro;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
