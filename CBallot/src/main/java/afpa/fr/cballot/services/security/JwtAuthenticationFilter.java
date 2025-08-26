package afpa.fr.cballot.services.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminUserDetailsServiceImpl adminUserDetailsServiceImpl;
    private final TeacherUserDetailsServiceImpl teacherUserDetailsServiceImpl;

    public JwtAuthenticationFilter(JwtService jwtService, AdminUserDetailsServiceImpl adminUserDetailsServiceImpl,
            TeacherUserDetailsServiceImpl teacherUserDetailsServiceImpl) {
        this.jwtService = jwtService;
        this.adminUserDetailsServiceImpl = adminUserDetailsServiceImpl;
        this.teacherUserDetailsServiceImpl = teacherUserDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authentication");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        jwt = authHeader.substring(7); // supprime "Bearer  "
        username = jwtService.extractUsername(jwt);

        // Vérifie que l'utilisateur n'est pas déjà authentifié
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails adminUserDetails = this.adminUserDetailsServiceImpl.loadUserByUsername(username);
            UserDetails teacherUserDetails = this.teacherUserDetailsServiceImpl.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, adminUserDetails)) {
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(
                                    adminUserDetails,
                                    null,
                                    adminUserDetails.getAuthorities()
                    );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            if (jwtService.isTokenValid(jwt, teacherUserDetails)) {
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(
                                    teacherUserDetails,
                                    null,
                                    teacherUserDetails.getAuthorities()
                    );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
