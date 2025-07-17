package xyz.ddlnt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/15 15:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
    private String username;
    private String role;
}
