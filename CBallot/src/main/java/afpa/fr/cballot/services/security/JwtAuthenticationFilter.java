package afpa.fr.cballot.services.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminUserDetailsServiceImpl adminUserDetailsServiceImpl;
    private final TeacherUserDetailsServiceImpl teacherUserDetailsServiceImpl;

    public JwtAuthenticationFilter(JwtService jwtService, 
        AdminUserDetailsServiceImpl adminUserDetailsServiceImpl, 
        TeacherUserDetailsServiceImpl teacherUserDetailsServiceImpl) {
        this.jwtService = jwtService;
        this.adminUserDetailsServiceImpl = adminUserDetailsServiceImpl;
        this.teacherUserDetailsServiceImpl = teacherUserDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt;
        final String username;

        jwt = authHeader.substring(7); // supprime "Bearer  "
        username = jwtService.extractUsername(jwt);

        // Vérifie que l'utilisateur n'est pas déjà authentifié
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = null;

        // Tentative 1 : Admin
        try {
            userDetails = adminUserDetailsServiceImpl.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                setAuthentication(userDetails, request);
                filterChain.doFilter(request, response);
                return;
            }
        } catch (UsernameNotFoundException e) {
            // Ignorer, passer à Teacher
        }

        // Tentative 2 : Teacher
        try {
            userDetails = teacherUserDetailsServiceImpl.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                setAuthentication(userDetails, request);
            }
        } catch (UsernameNotFoundException e) {
            // Utilisateur inconnu
        }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
