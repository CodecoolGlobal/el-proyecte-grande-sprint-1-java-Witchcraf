//package com.codecool.PawPrint.controller;
//
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
//import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
//import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LogoutController {
//
//    @GetMapping("/logout")
//    SecurityWebFilterChain http(ServerHttpSecurity http) throws Exception {
//        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
//                new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
//        );
//
//        http
//                .authorizeExchange((exchange) -> exchange.anyExchange().authenticated())
//                .logout((logout) -> logout.logoutHandler(logoutHandler));
//
//        return http.build();
//    }
//}
