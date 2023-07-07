package pe.edu.upc.api_examen_final_20211a712.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.api_examen_final_20211a712.entities.Role;
import pe.edu.upc.api_examen_final_20211a712.services.IRoleService;
import pe.edu.upc.api_examen_final_20211a712.services.IUserService;


@Controller
@SessionAttributes
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private IUserService uService;
	@Autowired
	private IRoleService rService;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("listaUsuarios", uService.list());
		return "role/role";
	}

	@PostMapping("/save")
	public String saveRole(@Validated Role role, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "role/role";
		} else {
			rService.insert(role);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listaRoles", rService.list());

		return "role/role";

	}

	@GetMapping("/list")
	public String listRole(Model model) {
		try {
			model.addAttribute("role", new Role());
			model.addAttribute("listaRoles", rService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "role/listRole";
	}

}
