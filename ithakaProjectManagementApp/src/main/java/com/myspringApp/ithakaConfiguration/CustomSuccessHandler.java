package com.myspringApp.ithakaConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/*
	 * Notice how we are extending Spring SimpleUrlAuthenticationSuccessHandler
	 * class and overriding handle() method which simply invokes a redirect using
	 * configured RedirectStrategy [default in this case] with the URL returned by
	 * the user defined determineTargetUrl method. This method extracts the Roles of
	 * currently logged in user from Authentication object and then construct
	 * appropriate URL based on there roles. Finally RedirectStrategy , which is
	 * responsible for all redirections within Spring Security framework , redirects
	 * the request to specified URL.
	 */


	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
	/*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
 
	protected String determineTargetUrl(Authentication authentication) {
        String url = "";
 
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
 
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
 
        if (isAdmin(roles)) {
            url = "/admin/dashboard";
        } else if (isUser(roles)) {
            url = "/user/dashboard";
        } else {
            url = "/login?error=true";
        }
 
        return url;
    }
 
    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
 
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
	
}
