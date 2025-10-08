package com.ifce.edital360.controller.profile;

import com.ifce.edital360.dto.deletarconta.AccountDeleteDTO;
import com.ifce.edital360.dto.perfil.PasswordChangeDTO;
import com.ifce.edital360.dto.perfil.ProfileResponseDTO;
import com.ifce.edital360.dto.perfil.ProfileUpdateDTO;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.service.user.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class PerfilController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<ProfileResponseDTO> getMyProfile(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        User userFromDb = profileService.getProfileById(currentUser.getId());

        return ResponseEntity.ok(new ProfileResponseDTO(userFromDb));
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponseDTO> updateMyProfile(Authentication authentication, @Valid @RequestBody ProfileUpdateDTO updateDTO) {
        User currentUser = (User) authentication.getPrincipal();
        User updatedUser = profileService.partiallyUpdateProfile(currentUser.getId(), updateDTO);

        return ResponseEntity.ok(new ProfileResponseDTO(updatedUser));
    }

    @PostMapping("/me/change-password")
    public ResponseEntity<String> changeMyPassword(Authentication authentication, @Valid @RequestBody PasswordChangeDTO passwordChangeDTO) {
        try {
            User currentUser = (User) authentication.getPrincipal();
            profileService.changePassword(currentUser.getId(), passwordChangeDTO);
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(Authentication authentication, @Valid @RequestBody AccountDeleteDTO deleteDTO) {
        try {
            User currentUser = (User) authentication.getPrincipal();
            profileService.deleteAccount(currentUser.getId(), deleteDTO);
            return ResponseEntity.ok("Sua conta foi desativada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
