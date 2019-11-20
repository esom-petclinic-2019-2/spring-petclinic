package org.springframework.samples.petclinic.owner;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.samples.petclinic.hospedagem.Hospedagem;
import org.springframework.samples.petclinic.hospedagem.HospedagemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HospedagemController {
	 private  final HospedagemRepository hosps;
	 private  final PetRepository pets;


	    public HospedagemController(HospedagemRepository hosps, PetRepository pets) {
	        this.hosps = hosps;
	        this.pets = pets;
	    }

	    @InitBinder
	    public void setAllowedFields(WebDataBinder dataBinder) {
	        dataBinder.setDisallowedFields("id");
	    }

	 
	    @ModelAttribute("hosp")
	    public Hospedagem loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model) {
	        Pet pet = this.pets.findById(petId);
	        pet.setVisitsInternalHosp(this.hosps.findByPetId(petId));
	        model.put("pet", pet);
	        Hospedagem hosp = new Hospedagem();
	        pet.addHosp(hosp);
	        return hosp;
	    }

	   
	    @GetMapping("/owners/*/pets/{petId}/hosps/new")
	    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
	        return "pets/createHosp";
	    }

	
	    @PostMapping("/owners/{ownerId}/pets/{petId}/hosps/new")
	    public String processNewHospForm(@Valid Hospedagem hosp, BindingResult result) {
	        if (result.hasErrors()) {
	            return "pets/createOrUpdateHospForm";
	        } else {
	            this.hosps.save(hosp);;
	            return "redirect:/owners/{ownerId}";
	        }
	    }
}
