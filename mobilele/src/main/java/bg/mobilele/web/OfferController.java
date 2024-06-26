package bg.mobilele.web;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Offer;
import bg.mobilele.model.enums.Engine;
import bg.mobilele.model.enums.Transmission;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.service.BrandService;
import bg.mobilele.service.ModelService;
import bg.mobilele.service.OfferService;
import bg.mobilele.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final CurrentUser currentUser;

    public OfferController(OfferService offerService, BrandService brandService, ModelService modelService, CurrentUser currentUser) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.currentUser = currentUser;
    }

    @GetMapping("offers/add")
    public String addOffer(Model model) {

        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", new AddOfferDTO());
        }

        if(!currentUser.isLogin()){
            return "redirect:/";
        }

        model.addAttribute("engineType", Engine.values());
        model.addAttribute("transmissionType", Transmission.values());
        model.addAttribute("brands", brandService.allBrands());
        model.addAttribute("models", modelService.allModel());

        return ("offer-add");
    }

    @PostMapping("offers/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }
        addOfferDTO.setCreated(LocalDateTime.now());
        addOfferDTO.setModified(LocalDateTime.now());

        long offerId = offerService.addOffer(addOfferDTO);

        return "redirect:/details/" + offerId;
    }

    @GetMapping("details/{id}")
    public String viewOfferDetail(@PathVariable("id") long id, Model model) {
        AddOfferDTO addOfferDTO = offerService.offerDetails(id);

        model.addAttribute("addOfferDTO", offerService.offerDetails(id));

        return "details";
    }

    @GetMapping("offers/all")
    public String viewAllOffer(Model model) {
        if(!currentUser.isLogin()){
            return "redirect:/";
        }

        List<Offer> offerList = offerService.allOfferInCurrentSeller(currentUser.getCurrentUserId());

        model.addAttribute("offers", offerList);

        return "offers";
    }

    @DeleteMapping("details/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {

        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

}
