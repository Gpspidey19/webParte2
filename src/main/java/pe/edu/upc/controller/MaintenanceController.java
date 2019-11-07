package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Maintenance;
import pe.edu.upc.service.IInspectionService;
import pe.edu.upc.service.IMaintenanceService;
import pe.edu.upc.service.ISparePartService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/maintenances")
public class MaintenanceController {
	
	@Autowired
	private IMaintenanceService mService;
	@Autowired
	private ISparePartService sService;
	@Autowired
	private IUserService uService;
	@Autowired
	private IInspectionService iService;

	@GetMapping("/new")
	public String newMaintenance(Model model) {
		model.addAttribute("maintenance", new Maintenance());
		model.addAttribute("listInspections", iService.list());
		model.addAttribute("listUsers", uService.list());
		model.addAttribute("listSpareparts", sService.list());
		return "maintenance/maintenance";
	}

	@PostMapping("/save")
	public String saveMaintenance(@Valid Maintenance maintenance, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listInspections", iService.list());
			model.addAttribute("listUsers", uService.list());
			model.addAttribute("listSpareparts", sService.list());
			return "maintenance/maintenance";
		} else {
			mService.insert(maintenance);
			
		}
		model.addAttribute("listMaintenances",mService.list());

		return "/maintenance/listMaintenance";
	}

	@GetMapping("/list")
	public String listMaintenance(Model model) {
		try {
			model.addAttribute("maintenance", new Maintenance());
			model.addAttribute("listMaintenances", mService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/maintenance/listMaintenance";
	}
	
	
	@RequestMapping("/update/{id}")
	public String updateMaintenance(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Maintenance> maintenance = mService.listId(id);
		if (maintenance == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/maintenance/listMaintenances";
		} else {
			model.addAttribute("maintenance", maintenance);
			model.addAttribute("listSpareparts", sService.list());
			model.addAttribute("listInspections", iService.list());
			model.addAttribute("listUsers", uService.list());
			return "/maintenance/maintenance";
		}
	}
	
	@RequestMapping("/delete")
	public String deleteMaintenance(Map<String, Object> model, @RequestParam(value = "idMaintenance") Integer id) {
		try {
			if (id != null && id > 0) {
				mService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el maintenance");
		}
		model.put("listMaintenances", mService.list());
		return "/maintenance/listMaintenance";
	}
}
	
