package test.com.jnshu.task5.controller; 

import com.jnshu.task5.beans.Login;
import com.jnshu.task5.service.LoginService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* LoginController Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮһ�� 30, 2018</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LoginControllerTest {
    @Autowired
    LoginService loginService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: textLoginJson(Model model) 
* 
*/ 
@Test
public void testTextLoginJson() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: testRegJson() 
* 
*/ 
@Test
public void testTestRegJson() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toRegistrationPage() 
* 
*/ 
@Test
public void testToRegistrationPage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: registration() 
* 
*/ 
@Test
public void testRegistration() throws Exception {
    Login login = new Login();

    login = loginService.selectLoginById(1);
    System.out.println(login);
} 

/** 
* 
* Method: toLoginPage() 
* 
*/ 
@Test
public void testToLoginPage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: doLogin(HttpServletRequest request, Model model, String loginName, String pwd) 
* 
*/ 
@Test
public void testDoLogin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: loginout(HttpSession session) 
* 
*/ 
@Test
public void testLoginout() throws Exception { 
//TODO: Test goes here... 
} 


} 
