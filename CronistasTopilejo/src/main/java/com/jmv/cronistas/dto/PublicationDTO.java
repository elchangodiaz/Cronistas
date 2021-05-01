package com.jmv.cronistas.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {

	private String idPublication;
	private String title;
	private String postDate;
	private String content;
	private List<String> modifications;
	private String status;
	private String image;

	
}
