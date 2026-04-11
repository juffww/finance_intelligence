package com.user.application.mapper;

import com.user.application.dto.request.UserCreationRequest;
import com.user.application.dto.response.UserResponse;
import com.user.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser (UserCreationRequest request);

    public UserResponse toUserResponse(User user);
}
