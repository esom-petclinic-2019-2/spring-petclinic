package org.springframework.samples.petclinic.hospedagem;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

@Entity
@Table(name="hospedagem")
public class Hospedagem extends BaseEntity{
	
	@Column(name="diarias")
	private Integer diarias;
	@Column(name = "pet_id")
	 private Integer petId;
	 @Column(name = "date_saida")
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private LocalDate date_saida;
	public Integer getDiarias() {
		return diarias;
	}
	public LocalDate getDate_saida() {
		return date_saida;
	}
	public void setDate_saida(LocalDate date_saida) {
		this.date_saida = date_saida;
	}
	public void setDiarias(Integer diarias) {
		this.diarias = diarias;
	}
	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
	}
}