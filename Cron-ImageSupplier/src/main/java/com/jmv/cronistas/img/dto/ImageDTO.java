package com.jmv.cronistas.img.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
	private String key;
	private String name;
	private String url;
	private MultipartFile file;
}
