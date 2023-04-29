package com.exam.configuration;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.Utils.JwtUtils;
import com.exam.serviceImpl.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	public JwtAuthenticationFilter() {
		super();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		get token from header 
		System.out.println("start method doFilterInternal");
		 // Check if request URI is /generate-token and skip token validation if it is
		logger.info("request.getRequestURI() = " + request.getRequestURI());
		
	    if (request.getRequestURI().equals("/generate-token/")) {
	        filterChain.doFilter(request, response);
	        return;
	    }
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("requestTokenHeader = " + requestTokenHeader);

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {

//			yes
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtUtils.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Token has expired");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error");
			}

		} else {
			System.out.println("Invalid token, not start with bearer string");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		    return;
		}

//		validate token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtUtils.validateToken(jwtToken, userDetails)) {
//				token is valid

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
 
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Token is not valid");
			}
		}

		filterChain.doFilter(request, response);
	}
}
