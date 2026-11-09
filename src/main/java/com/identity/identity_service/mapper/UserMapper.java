package com.identity.identity_service.mapper;

import com.identity.identity_service.dto.request.UserCreationRequest;
import com.identity.identity_service.dto.request.UserUpdateRequest;
import com.identity.identity_service.dto.response.UserResponse;
import com.identity.identity_service.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponses(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
