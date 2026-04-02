package com.finance_intelligence.domain.mapper;

import com.finance_intelligence.domain.dto.request.UserCreationRequest;
import com.finance_intelligence.domain.dto.response.UserResponse;
import com.finance_intelligence.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser (UserCreationRequest request);

    public UserResponse toUserResponse(User user);
}
