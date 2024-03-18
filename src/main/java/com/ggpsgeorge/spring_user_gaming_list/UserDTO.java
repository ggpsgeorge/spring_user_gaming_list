package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * DTO of the User Object. The password field is removed from the User Model
 * 
 * @author Fried Potato
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    Long id;
    String userName;
    String email;

    /**
     * List of all favorite games of the user
     */
    List<Game> games;
}
