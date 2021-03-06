package com.krypton.accountservice.feign;

import com.krypton.accountservice.config.FeignConfig;
import com.krypton.common.model.request.FriendRequest;
import com.krypton.common.model.user.Friend;
import com.krypton.common.model.user.User;
import com.krypton.common.model.user.UserStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@FeignClient(contextId = "database-service.user", value = "DATABASE-SERVICE", path = "/user", configuration = FeignConfig.class)
public interface UserFeignClient
{
    @PostMapping("/save")
    Optional<User> save(@RequestBody User user);

    @GetMapping("/get")
    Optional<User> find(@RequestParam UUID id);

    @GetMapping(value = "/get", params = "id")
    Optional<User> findById(@RequestParam("id") String id);

    @GetMapping(value = "/get", params = "nick")
    Optional<User> findByNick(@RequestParam("nick") String nick);

    @GetMapping(value = "/get", params = "email")
    Optional<User> findByEmail(@RequestParam("email") String email);

    @GetMapping("/get/friends")
    Set<Friend> getFriends(@RequestParam String id);

    @GetMapping("/get/friends-as-users")
    Set<User> getFriendsAsUsers(@RequestParam String id);

    @PostMapping("/update/status")
    void setStatus(@RequestParam String id, @RequestParam UserStatus status);

    @PostMapping("/update/profile-image")
    void setProfileImage(@RequestParam String id, @RequestParam String imageId);

    @GetMapping(value = "/search", params = "query")
    Set<User> search(@RequestParam String query);

    @PutMapping(value = "/add/friend-request")
    void addFriendRequest(@RequestBody FriendRequest request);

    @GetMapping(value = "/get/friend-requests", params = "id")
    Set<FriendRequest> getFriendRequests(@RequestParam String id);
}
