package io.auth.auth;

import io.auth.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by k.kezzar on 26/02/2020.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        try {
            System.out.println("***ddddddddddddddd*" );
            final String autorisation = httpServletRequest.getHeader("Authorization");

            String userName = null;
            String jwt = null;

            if (autorisation != null) {

                jwt = autorisation;
                System.out.println("***ddddddddddddddd*" + jwt);
                userName = jwtUtils.extractUserName(jwt);

                System.out.println("****" + userName);
            }
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                MyUserDetail userDetails = this.userDetailsService.loadUserByUsername(userName);
                userDetails.setUserId(15);
                if (jwtUtils.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                }
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }catch (Exception e){

            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }
}
