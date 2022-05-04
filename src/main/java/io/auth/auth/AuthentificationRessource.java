package io.auth.auth;

import io.auth.auth.model.AuthentificationRequest;
import io.auth.auth.model.AuthentificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by k.kezzar on 26/02/2020.
 */
@CrossOrigin(origins = "*")
@RestController
public class AuthentificationRessource {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    JwtUtils jwtUtils;

   // @PreAuthorize("isMember(#userName)")
    //@PreAuthorize("#userName == authentication.principal.userId")
   /* @RequestMapping({"/api/{userName}/hello/{test}"})
    public String hello(@PathVariable Integer userName,@PathVariable String test) {
        return "hello page"+userName+test;
    }
*/
    @RequestMapping(value = {"/api/authentication"}, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthentificationRequest authentificationRequest) throws Exception {
        try {

           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationRequest.getUsername() + authentificationRequest.getPassword(),
                    authentificationRequest.getPassword()));

        } catch (BadCredentialsException e) {

           return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByMatriculeAndAnneeBac(authentificationRequest.getUsername(),authentificationRequest.getPassword());
        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthentificationResponse(jwt));
    }



    @RequestMapping(value = {"/api/authentication/test"}, method = RequestMethod.GET)
    public ResponseEntity<?> test() throws Exception {

            return ResponseEntity.ok("test");
    }
    @RequestMapping(value = {"/api/checktoken"}, method = RequestMethod.GET)
    public ResponseEntity<?> checkToken() throws Exception {
 if(SecurityContextHolder.getContext().getAuthentication()!=null )
        return ResponseEntity.ok("token is valid");
 else  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("token not valid");

    }

    @RequestMapping(value = {"/api/refresh"}, method = RequestMethod.POST)
    public ResponseEntity<?> refresh(@RequestBody AuthentificationRequest authentificationRequest) throws Exception {
        try {
            return ResponseEntity.ok("test");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("error");
        }

    }
}
