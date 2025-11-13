package com.melevanoronha.service;

import com.melevanoronha.dto.response.AuthResponse;
import com.melevanoronha.dto.request.LoginRequest;
import com.melevanoronha.dto.request.RefreshTokenRequest;
import com.melevanoronha.dto.request.RegisterRequest;
import com.melevanoronha.model.RefreshToken;
import com.melevanoronha.model.Usuario;
import com.melevanoronha.repository.RefreshTokenRepository;
import com.melevanoronha.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Serviço responsável por gerenciar autenticação e tokens de acesso.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario register(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username já está em uso");
        }

        if (usuarioRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email já está em uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setUsername(request.username());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.senha()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = generateRefreshTokenString(userDetails);

        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        refreshTokenRepository.revokeAllUserTokens(usuario);

        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setUsuario(usuario);
        refreshTokenEntity.setExpiresAt(LocalDateTime.now().plusSeconds(jwtService.getRefreshExpiration()));
        refreshTokenRepository.save(refreshTokenEntity);

        return new AuthResponse(
                accessToken,
                refreshToken,
                "Bearer",
                jwtService.getExpiration()
        );
    }

    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh token inválido"));

        if (!refreshToken.isValid()) {
            throw new RuntimeException("Refresh token expirado ou revogado");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(
                refreshToken.getUsuario().getUsername()
        );

        String newAccessToken = jwtService.generateToken(userDetails);

        String newRefreshToken = generateRefreshTokenString(userDetails);

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);

        RefreshToken newRefreshTokenEntity = new RefreshToken();
        newRefreshTokenEntity.setToken(newRefreshToken);
        newRefreshTokenEntity.setUsuario(refreshToken.getUsuario());
        newRefreshTokenEntity.setExpiresAt(LocalDateTime.now().plusSeconds(jwtService.getRefreshExpiration()));
        refreshTokenRepository.save(newRefreshTokenEntity);

        return new AuthResponse(
                newAccessToken,
                newRefreshToken,
                "Bearer",
                jwtService.getExpiration()
        );
    }


    private String generateRefreshTokenString(UserDetails userDetails) {
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
    }

    @Transactional
    public void revokeToken(String refreshToken) {
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token não encontrado"));
        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }
}

