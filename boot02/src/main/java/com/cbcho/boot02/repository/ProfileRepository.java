package com.cbcho.boot02.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbcho.boot02.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
