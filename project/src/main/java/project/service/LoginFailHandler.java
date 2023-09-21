package project.service;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("loginFailHandler")
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {


        if(exception instanceof AuthenticationServiceException) {
            request.setAttribute("msg", "죄송합니다. 시스템에 오류가 발생했습니다.");
        }
        else if(exception instanceof DisabledException) {
            request.setAttribute("msg", "현재 사용할 수 없는 계정입니다.");
        }
        else if(exception instanceof LockedException) {
            request.setAttribute("msg", "현재 잠긴 계정입니다.");
        }
        else if(exception instanceof AccountExpiredException) {
            request.setAttribute("msg", "이미 만료된 계정입니다.");
        }
        else if(exception instanceof CredentialsExpiredException) {
            request.setAttribute("msg", "비밀번호가 만료된 계정입니다.");
        }
        else if(exception instanceof BadCredentialsException) {
            request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        else request.setAttribute("msg", "계정을 찾을 수 없습니다.");

        System.out.println("LoginFailHandler //  request = " + request);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login");
        System.out.println("dispatcher = " + dispatcher);
        dispatcher.forward(request, response);
    }
}