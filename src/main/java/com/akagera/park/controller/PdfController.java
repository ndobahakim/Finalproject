package com.akagera.park.controller;
import com.akagera.park.model.Form;
import com.akagera.park.repository.FormRepo;
import com.akagera.park.services.PdfService;
import com.itextpdf.text.DocumentException;
//import com.sf.stylefusion.model.Portfolio;
//import com.sf.stylefusion.repository.PortfolioRepository;
//import com.sf.stylefusion.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PdfController {
    private final FormRepo portfolioRepository;
    private final PdfService pdfService;


    public PdfController(FormRepo portfolioRepository, PdfService pdfService) {
        this.portfolioRepository = portfolioRepository;
        this.pdfService = pdfService;
    }


    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> downloadPdf() throws DocumentException {
        List<Form> portfolios = portfolioRepository.findAll();

        StringBuilder dataBuilder = new StringBuilder();
        for (Form portfolio : portfolios) {
            dataBuilder.append("ID: ").append(portfolio.getId()).append("\n")
                    .append("Owner ID: ").append(portfolio.getFirstname()).append("\n")
                    .append("StudentName: ").append(portfolio.getEmail()).append("\n")
                    .append("Course: ").append(portfolio.getPhonenumber()).append("\n")
                    .append("Result: ").append(portfolio.getAddress()).append("\n");
//                    .append("Name: ").append(portfolio.getName()).append("\n\n");
        }

        String data = dataBuilder.toString();

        byte[] pdfBytes = pdfService.generatePdf(data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "portfolios.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
