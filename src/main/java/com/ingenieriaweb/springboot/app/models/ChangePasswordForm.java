package com.ingenieriaweb.springboot.app.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ingenieriaweb.springboot.app.validation.CurrentPasswordMatches;
import com.ingenieriaweb.springboot.app.validation.FieldsMatches;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@FieldsMatches(field = "password", matchingField = "matchingPassword")
@CurrentPasswordMatches()
public class ChangePasswordForm {
    
    @NotNull
    private Long id;

    @Size(min = 5, max = 10, message = "Password should have 5-15 letters")
    @NotBlank()
    private String password;

    @Size(min = 5, max = 10, message = "Password should have 5-15 letters")
    @NotBlank()
    private String matchingPassword;

    private String currentPassword;
    
    

    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getMatchingPassword() {
		return matchingPassword;
	}



	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}



	public String getCurrentPassword() {
		return currentPassword;
	}



	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}



	public ChangePasswordForm(Long id) { this.id = id; }

}
