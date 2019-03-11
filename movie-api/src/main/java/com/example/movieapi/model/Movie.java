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
package com.example.movieapi.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {

	@Id
	private String id;
	private String name;
	private String genre;
	private LocalDateTime releaseDate;

	public Movie() {

	}

	public Movie(final String id, final String name, final String genre, final LocalDateTime releaseDate) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

	public LocalDateTime getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(final LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Movie movie = (Movie) o;
		return Objects.equals(this.id, movie.id) &&
				Objects.equals(this.name, movie.name) &&
				Objects.equals(this.genre, movie.genre) &&
				Objects.equals(this.releaseDate, movie.releaseDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.genre, this.releaseDate);
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id='" + this.id + '\'' +
				", name='" + this.name + '\'' +
				", genre='" + this.genre + '\'' +
				", releaseDate=" + this.releaseDate +
				'}';
	}

}
