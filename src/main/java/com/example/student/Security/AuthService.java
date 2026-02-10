package com.example.student.Security;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.student.DTO.LoginRequestDto;
import com.example.student.DTO.LoginResponseDto;
import com.example.student.DTO.SignupResponseDto;
import com.example.student.DTO.UserResponse;
import com.example.student.Entity.User;
import com.example.student.Entity.Type.AuthProviderType;
import com.example.student.Entity.Type.RoleType;
import com.example.student.Repository.UserRespository;
// import org.modelmapper.ModelMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRespository userRepository;
    private final PasswordEncoder passwordEncoder;
    // private final ModelMapper modelMapper;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

       Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(user.getId(), user.getUsername(), token);
    }

    public User signUpInternal(LoginRequestDto signupRequestDto, AuthProviderType authProviderType, String providerId) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User already exists");

        user = User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .roles(Set.of(RoleType.STUDENT))
                .providerType(authProviderType)
                .build();

        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

        }
        return userRepository.save(user);
                     
    }

    public SignupResponseDto signup(LoginRequestDto signUpRequestDto) {

        User user = signUpInternal(signUpRequestDto, AuthProviderType.EMAIL, null);

        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOauth2Request(OAuth2User oAuth2User, String registrationId) {
        // get provider type
        AuthProviderType providerType = authUtil.getAuthProviderTypeFromRegistration(registrationId);
        // provider id
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);

        // get email from oauth2 user
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // check if user already logged in from different provider or by email id 
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if (user == null && emailUser == null) {
            // signup flow
            String userName = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signUpInternal(new LoginRequestDto(userName, null), providerType, providerId);
        } else if (user != null) {
            if (email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException(
                    "This user already exist with provider " + emailUser.getProviderType());
        }

       LoginResponseDto loginResponseDto = new LoginResponseDto(user.getId(), user.getUsername(), authUtil.generateAccessToken(user));
        return ResponseEntity.ok(loginResponseDto);

        // save the providertype and provider id infi with user
        // if the user has account then directly login

        // other wise first login

    }

    public List<UserResponse> getAllUser() {
    List<User> users = userRepository.findAll();

    return users.stream()
            .map(user -> modelMapper.map(user, UserResponse.class))
            .toList();
}



}
