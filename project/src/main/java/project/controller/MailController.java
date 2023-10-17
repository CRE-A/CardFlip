package project.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.DAO.UserDao;

@Controller("mailController")
@RequiredArgsConstructor
@RequestMapping("/email")
public class MailController {

    private final JavaMailSender mailSender;
    private final UserController userController;


    @GetMapping("/mailSending")
    public void mailSending(String id, String email) throws Exception {

        int randomNum = (int)(Math.random()*100)+11;

        String setfrom = "pib102mgr@gmail.com"; // 보내는 사람 이메일
        String tomail = email; // 받는 사람 이메일
        String title = "[PIB] 임시 비밀번호 안내 이메일 입니다"; // 제목
        String TempPassword = "TEMP"+randomNum+"_"+email;
        String content = "안녕하세요. 고객님의 PIB 임시 비밀번호는"+TempPassword+" 입니다"; // 내용
        System.out.println("TempPassword = " + TempPassword);


        // email 발송
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(setfrom); // 보내는사람
        messageHelper.setTo(tomail); // 받는사람 이메일
        messageHelper.setSubject(title); // 메일제목
        messageHelper.setText(content); // 메일 내용
        mailSender.send(message);

        // 임시 비밀번호를 DB에 update
        userController.updateTmpPassword(id, TempPassword);

    }
}
















//package project.controller;
//
//        import javax.mail.internet.MimeMessage;
//        import javax.servlet.http.HttpServletRequest;
//
//        import lombok.RequiredArgsConstructor;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.mail.javamail.JavaMailSender;
//        import org.springframework.mail.javamail.MimeMessageHelper;
//        import org.springframework.stereotype.Controller;
//        import org.springframework.web.bind.annotation.RequestMapping;
//        import org.springframework.web.servlet.ModelAndView;
//
//@Controller("mailController")
//@RequiredArgsConstructor
//@RequestMapping("/mail")
//public class MailController {
//
//    private final JavaMailSender mailSender;
//
//    // mailForm
//    @RequestMapping("/mailForm")
//    public ModelAndView mailForm() {
//        return new ModelAndView("/mail/mailForm");
//    }
//
//    // mailSending 코드
//    @RequestMapping(value = "/mailSending")
//    public String mailSending(HttpServletRequest request) {
//
//        String setfrom = "issac.lee.dev@gmail.com";
//        String tomail = request.getParameter("tomail"); // 받는 사람 이메일
//        String title = request.getParameter("title"); // 제목
//        String content = request.getParameter("content"); // 내용
//
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
//
//            messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
//            messageHelper.setTo(tomail); // 받는사람 이메일
//            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
//            messageHelper.setText(content); // 메일 내용
//
//            mailSender.send(message);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return "redirect:/mail/mailForm";
//    }
//}