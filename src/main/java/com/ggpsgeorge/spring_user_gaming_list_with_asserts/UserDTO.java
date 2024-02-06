package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * DTO of the entity User. The password field is removed
 * 
 * @author Fried Potato
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    Long id;
    String userName;
    /**
     * List of all favorite games of the user
     */
    List<Game> games;
}
