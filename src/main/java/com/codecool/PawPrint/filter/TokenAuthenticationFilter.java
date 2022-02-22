package com.codecool.PawPrint.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class TokenAuthenticationFilter {

    public String tokenVerification(HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        }
        catch (Exception e){

            throw new Exception("Invalid Token");
        }
    }

}
