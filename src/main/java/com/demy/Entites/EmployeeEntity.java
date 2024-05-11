package com.demy.Entites;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;

	@Entity(name = "employees")
	public class EmployeeEntity {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
	    private String password;
	    private String role;
	    private String offer_letter;

		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getOfferLetter() {
			return offer_letter;
		}

		public void setOfferLetter(String offerLetter) {
			this.offer_letter = offerLetter;
		}
		@Override
		public String toString() {
			return "EmployeeEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
					+ role + ", offerLetter=" + offer_letter + "]";
		}

	    
	
}
