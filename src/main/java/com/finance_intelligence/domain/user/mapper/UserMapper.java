package com.finance_intelligence.domain.user.mapper;

import com.finance_intelligence.domain.user.dto.request.UserCreationRequest;
import com.finance_intelligence.domain.user.dto.response.UserResponse;
import com.finance_intelligence.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser (UserCreationRequest request);

    public UserResponse toUserResponse(User user);
}
