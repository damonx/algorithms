/*
 * Copyright (c) Fisher and Paykel Appliances.
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
package com.example.movieapi.secure;

//@EnableWebFluxSecurity
public class SecurityConfig {
//	@Value("${spring.security.oauth2.resourceserver.jwk.issuer-uri}")
//	private String issuerUri;
//
//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
//		http
//				.authorizeExchange()
//				.pathMatchers(HttpMethod.GET, "/movies/**").permitAll()
//				.anyExchange().authenticated()
//				.and()
//				.oauth2ResourceServer()
//				.jwt();
//		return http.build();
//	}
//
//	@Bean
//	public ReactiveJwtDecoder jwtDecoder() {
//		return ReactiveJwtDecoders.fromOidcIssuerLocation(this.issuerUri);
//	}
}
