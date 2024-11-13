//package com.insilicogen.healthcareproject;
//import com.insilicogen.healthcareproject.layer.domain.model.User;
//import com.insilicogen.healthcareproject.layer.domain.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class RedisTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void testRedisConnection() {
//
//        // create
//        User user = new User("1", "somang", 25);
//        userRepository.save(user);
//
//        // read
//        User retrievedUser = userRepository.findById(user.getId()).orElse(null);
////        assertThat(retrievedUser).isNotNull();
////        assertThat(retrievedUser.getName()).isEqualTo("somang");
//
//        // update
////        retrievedUser.setAge(26);
////        userRepoasitory.save(retrievedUser);
////        User updatedUser = userRepository.findById(user.getId()).orElse(null);
////        assertThat(updatedUser.getAge()).isEqualTo(26);
//
//        // delete
////        userRepository.delete(retrievedUser);
////        User deleteUser = userRepository.findById(user.getId()).orElse(null);
////        assertThat(deleteUser).isNull();
//    }
//}
