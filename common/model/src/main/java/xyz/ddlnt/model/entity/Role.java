package xyz.ddlnt.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/8 20:35
 */
@Data
public class Role {
    private Integer id;
    private String roleName;
    private LocalDateTime createTime;
}
