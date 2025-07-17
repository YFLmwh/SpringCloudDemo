package xyz.ddlnt.model.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/8 20:36
 */
@Data
public class Permission {
    private Integer id;
    private String permissionName;
    private LocalDateTime createTime;
}
