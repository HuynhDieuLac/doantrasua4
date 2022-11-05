package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.StorageService;

@Controller
public class FileUploadController {

	@Autowired
	private StorageService storageService;

	@GetMapping("/upload")
	public String listUploadedFiles(HttpServletRequest request, Model model) throws IOException {
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		model.addAttribute("files", storageService.loadAll(uploadRootPath));
		return "uploadForm";
	}

	@PostMapping("/upload")
	public String handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);
		storageService.store(file, uploadRootPath);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/upload";
	}
}
